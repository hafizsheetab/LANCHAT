package GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainWindowController {

    @FXML
    public TextArea activeUsersTextArea;
    @FXML
    public TextArea receivedMessagesTextArea;
    @FXML
    public TextArea toUserNameTextArea;
    @FXML
    public TextArea messageTextArea;
    @FXML
    Button sendButton;
    @FXML
    Button activeUsersButton;
    @FXML
    AnchorPane anchorPane;
    Socket socketMessageSend;
    Socket socketMessageReceive;
    Socket socketAuthenticationCodeSend;
    Socket socketAuthenticationReceive;

    public void shutdown() throws InterruptedException {
        System.out.println("Shesh");
        sendExitSignal();
        Thread.sleep(100);
        Platform.exit();
        System.exit(0);
    }

    private void sendExitSignal() {
        ClientMessageSendThread.sendMessage(socketMessageSend,"Server","exit()");
    }

    public void initialize() throws Exception{
        socketMessageSend = new Socket("localhost",6000);
        socketMessageReceive = new Socket("localhost",6001);
        socketAuthenticationCodeSend = new Socket("localhost",6002);
        socketAuthenticationReceive = new Socket("localhost",6003);
        ClientAuthenticationCodeSendThread clientAuthenticationCodeSendThread = new ClientAuthenticationCodeSendThread(socketAuthenticationCodeSend);
        ClientAuthenticationCodeReceiveThread clientAuthenticationCodeReceiveThread = new ClientAuthenticationCodeReceiveThread(socketAuthenticationReceive);
        clientAuthenticationCodeSendThread.start();
        clientAuthenticationCodeReceiveThread.start();
        ClientMessageSendThread.sendUserName(socketMessageSend, (String) Storage.getObject("userName"));
        Runnable messageReceiveThread = () -> messageReceive();
        new Thread(messageReceiveThread).start();

    }

    private void messageReceive() {
        while (true){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socketMessageReceive.getInputStream()));
                String signal = input.readLine();
                String message = input.readLine();
                showMessage(signal,message);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void showMessage(String signal, String message) {
        if(signal.equals("activeList")){
            activeUsersTextArea.setText(message);
        }
        else{
            receivedMessagesTextArea.setText(receivedMessagesTextArea.getText() +"\n"+ message);
        }
    }
    public void sendButtonOnAction() {
        ClientMessageSendThread.sendMessage(socketMessageSend,toUserNameTextArea.getText(),messageTextArea.getText());
    }
    public void activeUsersButtonOnAction(){
        ClientMessageSendThread.sendMessage(socketMessageSend,"Server","activeList");
    }

}
