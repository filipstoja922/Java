package studenti;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *Klasa koja prikazuje naslov na glavnoj strani
 * @author Filip Stojanovic
 */
public class Naslov extends AbstractStrana {

 
    Naslov(short pristup) {
        super(pristup);
    }

    Label lbl;
    Button btn;

    @Override
    public void start(final Stage titleWindowStage) throws Exception {

        titleWindowStage.show();
        titleWindowStage.setTitle("Ucenici");
        titleWindowStage.setResizable(false);
        Image image = new Image("file:images.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(700);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        Button titleLable;
        titleLable = new Button();
        titleLable.setText("Baza ucenika i njihovi predmeti");
        titleLable.setStyle("-fx-font: bold 12pt Arial;-fx-background-color: white;-fx-border-width: 1px;");
        titleLable.setPrefSize(550, 10);

        Button logOutBtn;
        logOutBtn = new Button();
        logOutBtn.setText("Odjavi se");
        logOutBtn.setStyle("-fx-font: bold 8pt Arial;-fx-background-color: gray;"
                + " -fx-border-width: 0px; -fx-text-fill: blue;");
        logOutBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                titleWindowStage.close();
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

        final Button studentsBtn = new Button("Studenti");
        studentsBtn.setStyle("-fx-base: coral;");
        studentsBtn.setMinWidth(100.0);
        studentsBtn.setPrefWidth(100.0);
        studentsBtn.setMaxWidth(100.0);

        studentsBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                titleWindowStage.close();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new StudentGlavna(pristup).start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        Button subjectsBtn;
        subjectsBtn = new Button();
        subjectsBtn.setText("Predmeti");
        subjectsBtn.setStyle("-fx-base: coral;");
        subjectsBtn.setMinWidth(100.0);
        subjectsBtn.setPrefWidth(100.0);
        subjectsBtn.setMaxWidth(100.0);
        subjectsBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                titleWindowStage.close();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new PredmetGlavna(pristup).start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        Button internetBtn;
        internetBtn = new Button();
        internetBtn.setText("O skoli");
        internetBtn.setStyle("-fx-base: coral;");
        internetBtn.setMinWidth(100.0);
        internetBtn.setPrefWidth(100.0);
        internetBtn.setMaxWidth(100.0);
        internetBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                titleWindowStage.close();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new OSkoli(pristup).start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        Button izlazBtn;
        izlazBtn = new Button();
        izlazBtn.setText("Izlaz");
        izlazBtn.setStyle("-fx-font: bold 10pt Arial;-fx-background-color: red; "
                + "-fx-border-width: 0px; -fx-text-fill: blue;");
        izlazBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                titleWindowStage.close();
            }
        });

        HBox hb = new HBox(20);
        HBox hb2 = new HBox(50);
        HBox hb3 = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        BorderPane border = new BorderPane();
        hb3.getChildren().add(iv1);
        hb2.getChildren().addAll(studentsBtn, subjectsBtn, internetBtn, izlazBtn);

        hb.getChildren().addAll(titleLable, logOutBtn);

        border.setCenter(hb2);
        border.setTop(hb);
        border.setBottom(hb3);

        Scene scene = new Scene(border, 700, 500);
        titleWindowStage.setScene(scene);
    }

    @Override
    public void run() {
        launch("123");
    }
}
