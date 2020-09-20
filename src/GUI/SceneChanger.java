package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class SceneChanger {
    public void sceneChange(String fxmlFileName, Pane pane)throws Exception{


        Stage stage = (Stage) pane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = fxmlLoader.load();
        MainWindowController mainWindowController = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnHidden(e -> {
            try {
                mainWindowController.shutdown();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        stage.show();

    }
}
