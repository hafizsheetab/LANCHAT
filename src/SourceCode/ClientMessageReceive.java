package SourceCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageReceive extends Thread{
    private String hashValue;
    private Socket socket;
    public ClientMessageReceive(Socket socket){
        this.socket = socket;
    }

    @Override
    public synchronized void run() {
        try {
            BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                String userName = input.readLine();
                String message = input.readLine();
                System.out.println(userName + " : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
