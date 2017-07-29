package studenti;


/**

* @author Filip Stojanovic
* 
*/ 

public class DBException extends Exception {

    /**
     *
     * @param msg
     */
    public DBException(String msg) {
		super(msg);
	}
	
    /**
     *
     * @param msg
     * @param cause
     */
    public DBException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
