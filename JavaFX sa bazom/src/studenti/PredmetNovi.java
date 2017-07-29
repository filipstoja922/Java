package studenti;

import java.sql.Connection;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dodavanje novog predmeta
 * @author Filip Stojanovic
 */
public class PredmetNovi extends AbstractRadnaStrana{
	
  
    public TextField predmetTextField;
    public TextField trajanjeTextField;
    public TextField profesorTextField;
    public Button kreirajtBtn;

    
    PredmetNovi(short pristup) {
		super(pristup);
	}
	
    /**
     *Prikazuje prozor za dodavanje novog predmeta
     * @throws DBException
     */
    @Override
	void prikazStrane() throws DBException {
					
			povratakBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					warningLableVisible = false;
					radnaStranaStage.close();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								new PredmetGlavna(pristup).start(new Stage());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
				}
			});
			
			Label disciplineLbl;
			disciplineLbl = new Label();
			disciplineLbl.setText("Naziv predmeta");
			disciplineLbl.setStyle("-fx-font: 10pt Arial;");
                        
                        Label trajanjeLbl;
			trajanjeLbl = new Label();
			trajanjeLbl.setText("Trajanje");
			trajanjeLbl.setStyle("-fx-font: 10pt Arial;");
                        
                        Label profesorLbl;
			profesorLbl = new Label();
			profesorLbl.setText("Profesor");
			profesorLbl.setStyle("-fx-font: 10pt Arial;");
                        
                        predmetTextField = new TextField();
			predmetTextField.setPrefSize(20, 15);
                        
                        trajanjeTextField = new TextField();
			trajanjeTextField.setPrefSize(20, 15);
                        
                        profesorTextField = new TextField();
			profesorTextField.setPrefSize(20, 15);
			
						
			kreirajtBtn = new Button();
			kreirajtBtn.setText("Napravi");
			kreirajtBtn.setStyle("-fx-font: bold 10pt Arial;" );
			kreirajtBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						boolean emptyFieldsIsNotExist=true;
						if (predmetTextField.getText().equals("")) {
							emptyFieldsIsNotExist = false;
						}
						
						if (!emptyFieldsIsNotExist) {
							radnaStranaStage.close();
							Platform.runLater(new Runnable() {
							
								@Override
								public void run() {
									try {
										warningLableVisible = true;
										new PredmetNovi(pristup).start(new Stage());
									} catch (Exception e) {
										e.printStackTrace();
									};
								}
							});						
						}else{
							warningLableVisible = false;
							dodajPredmetStrana();
							radnaStranaStage.close();
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									try {
										new PredmetGlavna(pristup).start(new Stage());
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
			
			
			
			
			
		
				gridPane.getChildren().addAll(trajanjeLbl,profesorLbl,profesorTextField,kreirajtBtn,trajanjeTextField,predmetTextField, disciplineLbl );			
			
                                
                                
                         gridPane.setConstraints(disciplineLbl, 0,2);
                    
                         gridPane.setConstraints(trajanjeLbl, 0,3);
                         gridPane.setConstraints(profesorLbl, 0,4);
                        gridPane.setConstraints(predmetTextField, 1,2);
                        gridPane.setConstraints(trajanjeTextField, 1,3);
                      gridPane.setConstraints(profesorTextField, 1,4);
			gridPane.setConstraints(kreirajtBtn, 1,5);
			gridPane.setConstraints(warningLbl, 1,6);
		
	}
		
   
   

    /**
     *Metoda za dodavanje novog predmeta
     * @throws DBException
     */
    protected void dodajPredmetStrana() throws DBException{
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			
			sql = "insert into predmet(ime,trajanje,profesor) values ('" +predmetTextField.getText() +"' , '" +trajanjeTextField.getText() +"' "
                                + ",'" +profesorTextField.getText() +"');"; 
                        
                     
                        
			stmt.execute(sql);
			UpisiLog(sql);
			
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


}
