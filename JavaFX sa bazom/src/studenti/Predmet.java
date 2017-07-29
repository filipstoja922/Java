package studenti;

/**
 *Klasa za kreiranje objekta tipa Predmet
 * @author Filip Stojanovic
 */
public class Predmet {

 
    private String ime;
    private int trajanje;
    private String profesor;

    /**
     * Konstruktor za kreiranje objekta tipa Predmet
     * @param ime ime predmeta
     * @param trajanje trajanje predmeta u toku godine
     * @param profesor ime profesora
     */
    public Predmet(String ime, int trajanje, String profesor) {
        this.ime = ime;
        this.trajanje = trajanje;
        this.profesor = profesor;
    }

    /**
     *
     * @return vraca ime
     */
    public String getIme() {
        return ime;
    }

    /**
     *
     * @param ime postavlja vrednost ime
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     *
     * @return vraca trajanje predmeta
     */
    public int getTrajanje() {
        return trajanje;
    }

    /**
     *
     * @return vraca ime profesora
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     *
     * @param trajanje setuje vrednost trajanja predmeta
     */
    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    /**
     *
     * @param profesor setuje ime profesora
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
	
	

	
}
