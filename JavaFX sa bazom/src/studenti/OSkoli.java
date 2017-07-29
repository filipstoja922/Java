package studenti;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *Prikazuje nam prozor sa informacijama o skoli
 * @author Filip Stojanovic
 */
public class OSkoli extends AbstractGlavnaStrana {

  
    private Label label = new Label();
    Button btn = new Button();
    Button btn2 = new Button();

  
    OSkoli(short accessLevel) {
        super(accessLevel);
    }

    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(btn, btn2);
        hbox.setAlignment(Pos.CENTER);
        btn.setText("Povratak");
        btn2.setText("Video");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    primaryStage.close();
                    new Naslov(pristup).start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(OSkoli.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {

                    new Video().start(new Stage());
                } catch (Exception ex) {
                    Logger.getLogger(OSkoli.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        BorderPane pane = new BorderPane();
        label.setWrapText(true);
        label.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 15; -fx-text-fill: darkred;");
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: mistyrose; -fx-padding: 10;");
        layout.getChildren().setAll(label);
        ScrollPane scroller = new ScrollPane(layout);
        scroller.setFitToWidth(true);
        pane.setCenter(scroller);
        pane.setBottom(hbox);

        Internet el = null;

        label.setText(el.download());

        primaryStage.setTitle("O skoli");
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    /**
     *
     * @throws DBException
     */
    @Override
    void prikaziGlavnuStranu() throws DBException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
