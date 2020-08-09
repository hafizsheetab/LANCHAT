package GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageReceiveThread extends Thread{
    private Socket socket;
    public ClientMessageReceiveThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        receiveMessage();
    }

    private void receiveMessage() {
        while (true){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = input.readLine();
                System.out.println(message);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
