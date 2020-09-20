package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class StartingWindowController{
    SceneChanger sceneChanger = new SceneChanger();
    String userName;
    @FXML
    AnchorPane pane;
    @FXML
    Button submitButton;
    @FXML
    TextArea textBox;


    public void submitButtonOnAction() throws Exception {
        this.userName = textBox.getText();
        Storage.storeObject(userName,"userName");
        Stage stage = (Stage)pane.getScene().getWindow();
        stage.setTitle(userName);
        sceneChanger.sceneChange("GUI.fxml",pane);
    }

}
