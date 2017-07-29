package studenti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *Klasa za unos podataka o novom studentu
 * @author Filip Stojanovic
 */
public class StudentNovi extends AbstractRadnaStrana{


    public TextField prezimeTextField;
    public TextField imeTextField;
    public TextField studentGroupTextField;
    public TextField upisTextField;
    public Button kreirajStudentBtn;
		
   
    StudentNovi(short pristup) {
		super(pristup);
	}
	
   
    @Override
	void prikazStrane() {
		
		povratakBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				warningLableVisible = false;
				radnaStranaStage.close();
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
				
			}
		});
		
		Label surnameLbl;
		surnameLbl = new Label();
		surnameLbl.setText("Prezime");
		surnameLbl.setStyle("-fx-font:10pt Arial;");
		
		Label nameLbl;
		nameLbl = new Label();
		nameLbl.setText("Ime");
		nameLbl.setStyle("-fx-font: 10pt Arial;");
		
		Label studentGroupLbl;
		studentGroupLbl = new Label();
		studentGroupLbl.setText("Grupa");
		studentGroupLbl.setStyle("-fx-font: 10pt Arial;");
		
		Label entryDateLbl;
		entryDateLbl = new Label();
		entryDateLbl.setText("Datum prijema");
		entryDateLbl.setStyle("-fx-font: 10pt Arial;");
		
		prezimeTextField = new TextField();
		prezimeTextField.setPrefSize(20, 10);
              
		imeTextField = new TextField();
		imeTextField.setPrefSize(20, 15);
		
		studentGroupTextField = new TextField();
		studentGroupTextField.setPrefSize(200, 15);
		
		upisTextField = new TextField();
		upisTextField.setPrefSize(200, 15);
		
		underTitleLbl = new Label();
		underTitleLbl.setText("Da bi ste uneli unesite vrednosti u polja");
		underTitleLbl.setStyle("-fx-font: bold 12pt Arial;");
		
		kreirajStudentBtn = new Button();
		kreirajStudentBtn.setText("Napravi");
		kreirajStudentBtn.setStyle("-fx-font: bold 10pt Arial;" );
		kreirajStudentBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					boolean emptyFieldsIsNotExist=true;
					if (prezimeTextField.getText().equals("") || imeTextField.getText().equals("") || 
							upisTextField.getText().equals("") || studentGroupTextField.getText().equals("")) {
						emptyFieldsIsNotExist = false;
					}
					
					if (!emptyFieldsIsNotExist) {
						radnaStranaStage.close();
						Platform.runLater(new Runnable() {
						
							@Override
							public void run() {
								try {
									warningLableVisible = true;
									new StudentNovi(pristup).start(new Stage());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								};
							}
						});						
					}else{
						warningLableVisible = false;
						updateStudentTabelu();
						radnaStranaStage.close();
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
					}
				} catch (DBException e) {
					e.printStackTrace();
				}				
			}
		});
		
		popuniPromenjenaPolja();
		gridPane.getChildren().addAll(kreirajStudentBtn,prezimeTextField,imeTextField,
					studentGroupTextField,upisTextField, 
						surnameLbl, nameLbl, studentGroupLbl, entryDateLbl);
		
              gridPane.setHgap(20);
              gridPane.setVgap(20);
		gridPane.setConstraints(surnameLbl, 0,3);
		gridPane.setConstraints(nameLbl, 0,4);
		gridPane.setConstraints(studentGroupLbl, 0,5);
		gridPane.setConstraints(entryDateLbl, 0,6);

		gridPane.setConstraints(prezimeTextField, 1,3);
		gridPane.setConstraints(imeTextField, 1,4);
		gridPane.setConstraints(studentGroupTextField, 1,5);
		gridPane.setConstraints(upisTextField, 1,6);
		gridPane.setConstraints(kreirajStudentBtn, 1,7);
		gridPane.setConstraints(warningLbl, 1,9);
		
		}

    /**
     *
     * @throws DBException
     */
    protected void updateStudentTabelu() throws DBException {
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			
			sql = "insert into studenti(ime,prezime,odeljenje,upis) values ('" +
					imeTextField.getText() + "' , '"+ prezimeTextField.getText()+"' , '" + 
						studentGroupTextField.getText() + "' , '" + upisTextField.getText() +"');"; 
			stmt.execute(sql);
			UpisiLog(sql);
			
			sql = "select max(id) from studenti; ";
			ResultSet rs =stmt.executeQuery(sql);
			
		
						
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
     * Popunjava polja o studentu koja treba da budu izmenjena
     */
    public void popuniPromenjenaPolja(){
		
	}

}
