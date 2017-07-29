package studenti;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *Prikaz video klipa o skoli
 * @author Filip Stojanovic
 */
public class Video extends Application {

    @Override
  public void start(Stage stage) throws Exception {
    WebView webview = new WebView();
    webview.getEngine().load(
      "https://www.youtube.com/watch?v=VKj30uDLVFg"
    );
    webview.setPrefSize(640, 390);

    stage.setScene(new Scene(webview));
    stage.show();
  }    
    
}
