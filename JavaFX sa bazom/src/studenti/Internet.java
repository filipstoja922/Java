package studenti;


import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *Klasa koja skida podatke sa sajta i prikazuje informacije o skoli
 * @author Filip Stojanovic
 */
public class Internet {
  
    static String tekstd = "";

    /**
     *Skida informacije o skoli
     * @return String sa tekstom o skoli
     * @throws IOException
     */
    public static String download() throws IOException {

         Document doc = Jsoup.connect("http://www.metropolitan.edu.rs/o-univerzitetu/").get();

        Elements elementi = doc.select("p");

        for (int i = 0; i < elementi.size(); i++) {

            tekstd += elementi.get(i).text() + '\n';

        }

        return tekstd;
    }


}
