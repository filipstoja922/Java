package studenti;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *Pocetna strana za logovanje admina ili korisnika
 * @author Filip Stojanovic
 */
public class LoginStrana extends Application implements Runnable{
    
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentidb";
    static final String USER = "root";
    static final String PASS = "";
		
    /**
     *Proverava konekciju
     * @throws DBException
     */
    private Connection getConnection() throws DBException {
			try {
				return DriverManager.getConnection(DB_URL,USER,PASS);
			} catch (SQLException e) {
				throw new DBException("Nemoguce uspostavljanje konekcije", e);
			}
		}
    final Text actiontarget = new Text();

    public static void main(String[] args) {
		launch("123");
	}
		
	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.show();
		primaryStage.setTitle("Login");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
        grid.add(actiontarget, 1, 6);

		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);
		
		Text scenetitle = new Text("Dobro dosli");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Ime");
		grid.add(userName, 0, 1);

		final TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Lozinka");
		grid.add(pw, 0, 2);

		final PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		Button btn = new Button("Ulaz");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		        actiontarget.setFill(Color.FIREBRICK);
		        actiontarget.setText("Nemoguca konekcija sa bazom");
		        try {
					proveriUserPass(userTextField.getText(),pwBox.getText(),primaryStage);
				} catch (DBException e1) {
					e1.printStackTrace();
				}
		    }
		});
	}

    /**
     *Proverava username i password 
     * @param korisnicko uneto korisnicko u login strani
     * @param password uneti password u login strani
     * @param primaryStage glavna strana
     * @throws DBException
     */
    protected void proveriUserPass(String korisnicko, String password, Stage primaryStage) throws DBException{
		
		Connection conn= getConnection();
		
		Statement stmt=null;
		
		try {
			
			String passFromDB = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
			byte accessLevelFromDB=-1;
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT password,pristup FROM korisnici where korisnicko ="+"'"+korisnicko+"'";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
		    	accessLevelFromDB = rs.getByte("pristup");
		       	passFromDB = rs.getString("password");
		    }
		    
		    conn.commit();
		    rs.close();
		    stmt.close();
		    conn.close();
		    
		    if (password.equals(passFromDB)) {
		    	primaryStage.close();
				runMainWindow(accessLevelFromDB);
			}else{
				actiontarget.setText("Unesite korisnicko ime i lozinku");
			}
		    
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
     *Pokrece glavni prozor
     * @param pristup upisuje u log fajl na osnovu pristupa
     */
    private void runMainWindow(final byte pristup) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					upisiiUFajl(pristup);
					new Naslov(pristup).start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void run() {
		launch("123");		
	}
	
    /**
     *upisuje u log fajl
     * @param pristup 
     */
    public void upisiiUFajl(int pristup){
		try (FileWriter writer = new FileWriter("log.txt", true)){
			writer.write(new java.util.Date().toString()+"\n");
			writer.write(pristup+" login" + "\n"+ "\n");
			writer.flush();
		} catch (IOException e) {
		
			e.printStackTrace();
		} 
	}
}
