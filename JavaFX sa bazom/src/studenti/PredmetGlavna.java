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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *Klasa za prikaz tabele sa predmetima i opcijama
 * @author Filip Stojanovic
 */
public class PredmetGlavna extends AbstractGlavnaStrana{

    
    PredmetGlavna(short pristup) {
		super(pristup);
	}

    /**
     * Prikaz strane o predmetu
     * @throws DBException
     */
    void prikaziGlavnuStranu() throws DBException {
		final ObservableList<Predmet> predmeti = FXCollections.observableArrayList();
                final TableView<Predmet> tabelaPredmeta = new TableView<>();
		popuniTabelu(predmeti, root);
		
	
		Button kreirajPredmetBtn;
                
		kreirajPredmetBtn = new Button();
                 kreirajPredmetBtn.setPadding(new Insets(10, 10, 10, 10));
                
		kreirajPredmetBtn.setPrefSize(200, 20);
		kreirajPredmetBtn.setText("Napravi predmet");
		kreirajPredmetBtn.setStyle("-fx-font: bold 8pt Arial;" );
		kreirajPredmetBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				glavnaStranaStage.close();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						upozorenjeGlavno = false;
						try {
							new PredmetNovi(pristup).start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		
		
		Button obrisiPredmetBtn;
		obrisiPredmetBtn = new Button();
                obrisiPredmetBtn.setPadding(new Insets(10, 10, 10, 10));
		obrisiPredmetBtn.setPrefSize(200, 20);
		obrisiPredmetBtn.setText("Obrisi predmet");
		obrisiPredmetBtn.setStyle("-fx-font: bold 8pt Arial;" );
		obrisiPredmetBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (!tabelaPredmeta.getSelectionModel().getSelectedItems().isEmpty()) {
					try {
						obrisiPredmet(tabelaPredmeta.getSelectionModel().getSelectedItems());
						glavnaStranaStage.close();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								upozorenjeGlavno = false;
								try {
									new PredmetGlavna(pristup).start(new Stage());
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
								new PredmetGlavna(pristup).start(new Stage());
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
            
		
		


		if (pristup==1) {
			gridPane.getChildren().addAll( kreirajPredmetBtn, obrisiPredmetBtn);
		}
		gridPane.setHgap(20);
                gridPane.setHgap(20);
                gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setConstraints(kreirajPredmetBtn, 1,4);
		gridPane.setConstraints(obrisiPredmetBtn, 1,5);
		gridPane.setConstraints(upozorenje, 1,8);
		
		tabelaPredmeta.setLayoutX(350);
		tabelaPredmeta.setLayoutY(100);
		tabelaPredmeta.setTableMenuButtonVisible(true);
		tabelaPredmeta.setStyle("-fx-font: 12pt Arial;");
		tabelaPredmeta.setPrefWidth(400);
		tabelaPredmeta.setPrefHeight(400);
		tabelaPredmeta.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		TableColumn<Predmet,String> predmetiCol = new TableColumn<>("Predmet");
		predmetiCol.setPrefWidth(140);
		predmetiCol.setCellValueFactory(
				new PropertyValueFactory<>("ime"));
		predmetiCol.setSortable(false);
                predmetiCol.prefWidthProperty().bind(tabelaPredmeta.widthProperty().divide(3));
                
                TableColumn<Predmet,String> trajanjeCol = new TableColumn<>("Trajanje");
		trajanjeCol.setCellValueFactory(
		new PropertyValueFactory<Predmet,String>("trajanje"));
		trajanjeCol.setSortable(false);
                trajanjeCol.prefWidthProperty().bind(tabelaPredmeta.widthProperty().divide(3));
                
                TableColumn<Predmet,String> profesorCol = new TableColumn<>("Profesor");
		profesorCol.setCellValueFactory(
		new PropertyValueFactory<>("profesor"));
                profesorCol.prefWidthProperty().bind(tabelaPredmeta.widthProperty().divide(3));
		
		tabelaPredmeta.setItems(predmeti);
		tabelaPredmeta.getColumns().addAll(predmetiCol,trajanjeCol,profesorCol);
		
		root.getChildren().add(tabelaPredmeta);
		
	}
	
    /**
     *Metoda za brisanje predmeta
     * @param brisanjeLista Lista sa obrisanim predmetom
     * @throws DBException
     */
    protected void obrisiPredmet (List<Predmet> brisanjeLista) throws DBException{
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			
			for (Predmet obrisiPredmet : brisanjeLista) {
				sql = "DELETE FROM predmet WHERE ime = '"+obrisiPredmet.getIme()+"'; ";
				stmt.executeUpdate(sql);
				UpisiLog(sql);
			}
		    
			conn.commit();
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
     *Metoda za popunjavanje tabele sa predmetima
     * @param predmeti Lista predmeta
     * @param root
     * @throws DBException
     */
    private void popuniTabelu(ObservableList<Predmet> predmeti, Group root) throws DBException{
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM predmet";
		    ResultSet rs = stmt.executeQuery(sql);
		    int counter = 0; 
		    while(rs.next()){
		    	predmeti.add(new Predmet(rs.getString("ime"), rs.getInt("trajanje"),rs.getString("profesor")));
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


}
