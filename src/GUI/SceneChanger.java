package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class SceneChanger {
    public void sceneChange(String fxmlFileName, Pane pane,String userName)throws Exception{

        Callback<Class<?>, Object> controllerFactory = type -> {
            if (type == MainWindowController.class) {
                return new MainWindowController(userName);
            }
             else {
                try {
                    return type.newInstance() ; // default behavior - invoke no-arg construtor
                } catch (Exception exc) {
                    System.err.println("Could not create controller for "+type.getName());
                    throw new RuntimeException(exc);
                }
            }
        };
        Stage stage = (Stage) pane.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        fxmlLoader.setControllerFactory(controllerFactory);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
