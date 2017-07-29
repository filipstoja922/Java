package studenti;

/**
 *Klasa za kreiranje objekta tipa Student
 * @author Filip Stojanovic
 */
public class Student {


    private int id;
    private String ime;
    private String prezime;
    private String odeljenje;
    private String upis;

    /**
     *Konstruktor za kreiranje Student objekta
     * @param id id u bazi
     * @param ime ime studenta
     * @param prezime prezime studenta
     * @param odeljenje odeljenje studenta
     * @param upis datum upisa studenta
     */
    public Student(int id, String ime, String prezime, String odeljenje, String upis) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.odeljenje = odeljenje;
        this.upis = upis;
    }

    /**
     *
     * @return id u tabeli
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return ime vraca imestudenta
     */
    public String getIme() {
        return ime;
    }

    /**
     *
     * @return prezime vraca prezime studenta
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     *
     * @return odeljenje vraca odeljenje studenta
     */
    public String getOdeljenje() {
        return odeljenje;
    }

    /**
     *
     * @return datum upisa vraca datum upisa studenta
     */
    public String getUpis() {
        return upis;
    }

    /**
     *
     * @param id setuje id u bazi
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param ime setuje ime studenta
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     *
     * @param prezime setuje preziem
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     *
     * @param odeljenje setuje odeljenje
     */
    public void setOdeljenje(String odeljenje) {
        this.odeljenje = odeljenje;
    }

    /**
     *
     * @param upis setuje datum upisa
     */
    public void setUpis(String upis) {
        this.upis = upis;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", odeljenje=" + odeljenje + ", upis=" + upis + '}';
    }
	
	
}
