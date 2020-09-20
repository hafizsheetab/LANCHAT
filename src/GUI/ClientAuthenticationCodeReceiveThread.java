package GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientAuthenticationCodeReceiveThread extends Thread{
    private Socket socket;
    public ClientAuthenticationCodeReceiveThread(Socket socket){
        this.socket = socket;
        Storage.storeObject("","messageLog");
    }

    @Override
    public void run() {
        receiveCode();
    }

    private void receiveCode() {
        while (true){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String messageLog = input.readLine();
                Storage.storeObject(messageLog,"messageLog");
                System.out.println(messageLog + " Received");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
