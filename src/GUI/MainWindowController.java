package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


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
    Socket socketMessageSend;
    Socket socketMessageReceive;
    Socket socketAuthenticationCodeSend;
    Socket socketAuthenticationReceive;
    String userName;
    public MainWindowController(String userName) {
        this.userName = userName;
        System.out.println(userName);
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
        ClientMessageSendThread.sendUserName(socketMessageSend,userName);
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
            receivedMessagesTextArea.setText(message);
        }
    }
    public void sendButtonOnAction() {
        ClientMessageSendThread.sendMessage(socketMessageSend,toUserNameTextArea.getText(),messageTextArea.getText());
    }
    public void activeUsersButtonOnAction(){
        ClientMessageSendThread.sendMessage(socketMessageSend,"Server","activeList");
    }
}
