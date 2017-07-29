package studenti;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Prikaz menija i gornjem delu aplikacije.Nasledjuju je ostale klase.
 *
 * @author Filip Stojanovic
 */
abstract class AbstractGlavnaStrana extends AbstractStrana {

    static boolean upozorenjeGlavno = false;

    Stage glavnaStranaStage;

    Group root;

    GridPane gridPane;

    Label upozorenje;

    /**
     *
     * @param pristup koji na osnovu parametra iz baze odredjuje pristup korisniku 
     */
    AbstractGlavnaStrana(short pristup) {
        super(pristup);
    }

    @Override
    public void start(final Stage glavnaStranaStage) throws Exception {
        this.glavnaStranaStage = glavnaStranaStage;
        glavnaStranaStage.show();
        glavnaStranaStage.setTitle("Students list");
        glavnaStranaStage.setResizable(false);

        root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);

        glavnaStranaStage.setScene(scene);

        Button titleLable;
        titleLable = new Button();
        titleLable.setText("Baza ucenika i njihovi predmeti");
        titleLable.setStyle("-fx-font: bold 12pt Arial;-fx-background-color: white;-fx-border-width: 1px;");
        titleLable.setPrefSize(550, 10);

        Button logOutBtn;
        logOutBtn = new Button();
        logOutBtn.setText("Odjavi se");
        logOutBtn.setStyle("-fx-font: bold 8pt Arial;-fx-background-color: gray; "
                + "-fx-border-width: 0px; -fx-text-fill: blue;");
        logOutBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                upozorenjeGlavno = false;
                glavnaStranaStage.close();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            new LoginStrana().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        Button goToMainPageBtn;
        goToMainPageBtn = new Button();
        goToMainPageBtn.setText("Glavna");
        goToMainPageBtn.setUnderline(true);
        goToMainPageBtn.setStyle("-fx-font: bold 8pt Arial;-fx-background-color: gray; "
                + "-fx-border-width: 0px; -fx-text-fill: blue;");
        goToMainPageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                upozorenjeGlavno = false;
                glavnaStranaStage.close();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new Naslov(pristup).start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        upozorenje = new Label();
        upozorenje.setText("Izaberite opciju");
        upozorenje.setStyle("-fx-font: 10pt Arial;-fx-text-fill:red;");
        upozorenje.setVisible(upozorenjeGlavno);

        gridPane = new GridPane();

        gridPane.getChildren().addAll(titleLable, logOutBtn, goToMainPageBtn, upozorenje);

        gridPane.setConstraints(titleLable, 1, 0);
        gridPane.setConstraints(logOutBtn, 2, 0);
        gridPane.setConstraints(goToMainPageBtn, 0, 0);

        root.getChildren().add(gridPane);

        prikaziGlavnuStranu();
    }

    /**
     *
     * @throws DBException
     */
    abstract void prikaziGlavnuStranu() throws DBException;
}
