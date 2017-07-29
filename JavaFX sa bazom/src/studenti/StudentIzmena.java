package studenti;

import java.sql.Connection;
import java.sql.Statement;

/**
 *Menjanje podataka o studentu
 * @author Filip Stojanovic
 */
public class StudentIzmena extends StudentNovi{

   
    Student izmenaStudenta;

    /**
     *
     * @param pristup na osnovu parametra iz baze odredjuje pristup korisniku
     * @param izmenaStudenta Student sa izmenjenim podacima
     * @throws DBException
     */
    StudentIzmena(short pristup, Student izmenaStudenta) throws DBException {
		super(pristup);
		this.izmenaStudenta = izmenaStudenta;
	}
	
    /**
     *Menja polja o studentu koja su izmenjena
     */
        @Override
    public void popuniPromenjenaPolja() {
		super.prezimeTextField.setText(izmenaStudenta.getPrezime());
		super.imeTextField.setText(izmenaStudenta.getIme());
		super.studentGroupTextField.setText(izmenaStudenta.getOdeljenje());
		super.upisTextField.setText(izmenaStudenta.getUpis());
		super.kreirajStudentBtn.setText("Primeni");
	}

    /**
     *Menja tabelu 
     * @throws DBException
     */
    @Override
	protected void updateStudentTabelu() throws DBException {
		Connection conn= Konekcija();
		
		Statement stmt=null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql;
			
			sql = "update studenti set ime= '"+imeTextField.getText()+
						"', prezime ='"+prezimeTextField.getText()+"', "
							+ "odeljenje= '"+studentGroupTextField.getText() +"', upis = '"+
								upisTextField.getText()+"' where id ="+izmenaStudenta.getId(); 
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
