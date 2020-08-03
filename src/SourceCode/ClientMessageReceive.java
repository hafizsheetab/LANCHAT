package SourceCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageReceive extends Thread{
    private Socket socket;
    public ClientMessageReceive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //System.out.println("receiving started" );
            BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                //System.out.println("receiving message....");
                String userName = input.readLine();
                String message = input.readLine();
                System.out.println(userName + " : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
