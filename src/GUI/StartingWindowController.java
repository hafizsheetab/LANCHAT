package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


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
        sceneChanger.sceneChange("GUI.fxml",pane,userName);
    }

}
