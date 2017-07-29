package studenti;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;

/**
 *Provera konekcije i upisivanje log fajlova
 * @author Filip Stojanovic
 */
abstract public class AbstractStrana extends Application implements Runnable{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/studentidb";

    static final String USER = "root";

    static final String PASS = "";
		
    /**
     * Proverava konekciju sa bazom
     * Izbacuje izuzetak u slucaju da je nemoguce uspostavljanje konekcije
     * @throws DBException 
     */
    public Connection Konekcija() throws DBException {
		try {
			return DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			throw new DBException("Nemoguce uspostavljanje konekcije", e);
		}
	}

    final short pristup;

    /**
     *
     * @param pristup koji na osnovu parametra iz baze odredjuje pristup korisniku 
     */
    AbstractStrana(short pristup){
		this.pristup = pristup;
	}
	
    /**
     *
     * @return podatak o pristupu
     */
    public short getPristup() {
		return pristup;
	}
	
	@Override
	public void run() {
		launch("123");
	}

    /**
     * Upisuje nam podatke o logovanju ili izmeni u bazi
     * @param sql String koji sadrzi informacije
     */
    public void UpisiLog(String sql){
		try (FileWriter writer = new FileWriter("log.txt", true)){
			writer.write(new java.util.Date().toString()+"\n");
			writer.write(sql + "\n"+ "\n");
			writer.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
}
