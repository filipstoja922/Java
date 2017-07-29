package studenti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Glavna strana o studentima sa opcijama
 * @author Filip Stojanovic
 */
public class StudentGlavna extends AbstractGlavnaStrana{
		
    StudentGlavna(short pristup) {
		super(pristup);
	}
			
   
    @Override
	void prikaziGlavnuStranu() {
		
		final ObservableList<Student> studenti = FXCollections.observableArrayList();
		final TableView<Student> studentsTable = new TableView<>();
		try {
			popuniTabelu(studenti, root);
		} catch (DBException e1) {
			
			e1.printStackTrace();
		}
		

		
		Button createStudentBtn;
		createStudentBtn = new Button();
		createStudentBtn.setPrefSize(150, 20);
		createStudentBtn.setText("Novi student");
		createStudentBtn.setStyle("-fx-font: bold 8pt Arial;" );
		createStudentBtn.setOnAction(new EventHandler<ActionEvent>() {

			
			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						upozorenjeGlavno = false;
						try {
							glavnaStranaStage.close();
							new StudentNovi(pristup).start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		Button studentModifyingBtn;
		studentModifyingBtn = new Button();
		studentModifyingBtn.setPrefSize(250, 20);
		studentModifyingBtn.setText("Izmenite izabranog studenta");
		studentModifyingBtn.setStyle("-fx-font: bold 8pt Arial;" );
		studentModifyingBtn.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				if(studentsTable.getSelectionModel().getSelectedItem()!=null){
					upozorenjeGlavno = false;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								glavnaStranaStage.close();
								new StudentIzmena(pristup, studentsTable.getSelectionModel().getSelectedItem()).start(new Stage());
							} catch (Exception e) {
								
								e.printStackTrace();
							}								
						}
					});
				}else {
					glavnaStranaStage.close();
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							upozorenjeGlavno = true;						
							try {
								new StudentGlavna(pristup).start(new Stage());
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
				
		
		Button deleteStudentBtn;
		deleteStudentBtn = new Button();
		deleteStudentBtn.setPrefSize(150, 20);
		deleteStudentBtn.setText("Obrisi studenta");
		deleteStudentBtn.setStyle("-fx-font: bold 8pt Arial;" );
		deleteStudentBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
								
				if (!studentsTable.getSelectionModel().getSelectedItems().isEmpty()) {
					try {
						upozorenjeGlavno = false;
						obrisiStudenta(studentsTable.getSelectionModel().getSelectedItems());
						glavnaStranaStage.close();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								try {
									new StudentGlavna(pristup).start(new Stage());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					} catch (DBException e) {
						e.printStackTrace();
					}
				}else {
					glavnaStranaStage.close();
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							upozorenjeGlavno = true;						
							try {
								new StudentGlavna(pristup).start(new Stage());
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}
					});
				}
			}
		});	
		
		if (pristup==1) {
			gridPane.getChildren().addAll(
					 createStudentBtn, deleteStudentBtn, studentModifyingBtn);
		}else if (pristup==0) {
			gridPane.getChildren().addAll(
					);

		}
                gridPane.setHgap(20);
                gridPane.setVgap(20);
		
		
		gridPane.setConstraints(studentModifyingBtn, 1,2);
		gridPane.setConstraints(createStudentBtn, 1,3);
		gridPane.setConstraints(deleteStudentBtn, 1,4);
		gridPane.setConstraints(upozorenje, 1,3);
		
		studentsTable.setLayoutX(230);
		studentsTable.setLayoutY(120);
		studentsTable.setTableMenuButtonVisible(true);
		studentsTable.setStyle("-fx-font: 12pt Arial;");
		studentsTable.setPrefWidth(500);
		studentsTable.setPrefHeight(400);
		studentsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		TableColumn<Student,String> ime = new TableColumn<>("Ime");
		ime.setCellValueFactory(
		new PropertyValueFactory<Student,String>("ime"));
		ime.setSortable(false);
                ime.prefWidthProperty().bind(studentsTable.widthProperty().divide(4));
		
		TableColumn<Student,String> prezime = new TableColumn<>("Prezime");
		prezime.setCellValueFactory(
		new PropertyValueFactory<>("prezime"));
                prezime.prefWidthProperty().bind(studentsTable.widthProperty().divide(4));
		
		TableColumn<Student,String> odeljenje = new TableColumn<>("Grupa");
		odeljenje.setCellValueFactory(
		new PropertyValueFactory<Student,String>("odeljenje"));
                odeljenje.prefWidthProperty().bind(studentsTable.widthProperty().divide(4));
		
		TableColumn<Student,String> upis = new TableColumn<>("Upis");
		upis.setPrefWidth(180);
		
		upis.setCellValueFactory(
		new PropertyValueFactory<Student,String>("upis"));
                upis.prefWidthProperty().bind(studentsTable.widthProperty().divide(4));
				
		studentsTable.setItems(studenti);
		studentsTable.getColumns().addAll(ime, prezime, odeljenje, upis);
		
		root.getChildren().add(studentsTable);
	}

    /**
     *Popunjava tabelu o studentima
     * @param students Lista studenta
     * @param root
     * @throws DBException
     */
    private void popuniTabelu(ObservableList<Student> students, Group root) throws DBException {
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM studenti";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
		    	students.add(new Student(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), 
		    			rs.getString("odeljenje"), rs.getString("upis")));
			}
		    
		    conn.commit();
		    rs.close();
		    stmt.close();
		    conn.close();
		    		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				if (stmt!=null) {
					stmt.close();
				}
			}catch(Exception e){
				
			}
			
			try{
				if (conn!=null) {
					conn.close();
				}
			}catch(Exception e){
			
			}
		}
	
	}	
			
    /**
     *Metoda za brisanje studenta iz baze
     * @param brisanjeList Lista obrisanog studenta
     * @throws DBException
     */
    private void obrisiStudenta(List<Student> brisanjeList) throws DBException {
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			
			for (Student studentBrisanje : brisanjeList) {
				sql = "DELETE FROM studenti WHERE id = "+studentBrisanje.getId()+"; ";
				stmt.executeUpdate(sql);
				UpisiLog(sql);
				conn.commit();
			}
		    
		    stmt.close();
		    conn.close();
		    			    
		    		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				if (stmt!=null) {
					stmt.close();
				}
			}catch(Exception e){
				
			}
			
			try{
				if (conn!=null) {
					conn.close();
				}
			}catch(Exception e){
				
			}
		}
	}	
	

}
