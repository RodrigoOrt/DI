package refugio.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RefugioMain extends Application{
    
    private double xOffset = 0.0;
    private double yOffset = 0.0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RefugioFXML.fxml"));
 
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getSceneX()-xOffset);
            stage.setY(event.getSceneY()-yOffset);
        });
        
        Scene scene = new Scene(root);
 
        stage.setTitle("Refugio");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}