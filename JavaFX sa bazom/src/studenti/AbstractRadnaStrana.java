package studenti;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Filip Stojanovic
 */
abstract class AbstractRadnaStrana extends AbstractGlavnaStrana{

 
    static boolean warningLableVisible = false;

    public Label underTitleLbl;

    Stage radnaStranaStage;

    Button povratakBtn;

    Label warningLbl;

    /**
     *
     * @param pristup
     */
    AbstractRadnaStrana(short pristup) {
		super(pristup);
	}

    /**
     *
     * @throws DBException
     */
    void prikaziGlavnuStranu() throws DBException {
		radnaStranaStage = glavnaStranaStage;
		
		povratakBtn = new Button();
		povratakBtn.setText("Povratak");
		povratakBtn.setStyle("-fx-font: bold 8pt Arial;-fx-background-color: gray; "
				+ "-fx-border-width: 0px; -fx-text-fill: blue;" );
				
	
		
		warningLbl = new Label();
		warningLbl.setText("Morate popuniti sva polja");
		warningLbl.setStyle("-fx-font: 10pt Arial;-fx-text-fill:red;");
		warningLbl.setVisible(warningLableVisible);
		
		gridPane.getChildren().addAll(povratakBtn,warningLbl);
		
		gridPane.setHgap(20);
                gridPane.setVgap(20);
		gridPane.setConstraints(povratakBtn, 2,0);
            
		
		prikazStrane();
	}

    /**
     *
     * @throws DBException
     */
    abstract void prikazStrane() throws DBException;
}
