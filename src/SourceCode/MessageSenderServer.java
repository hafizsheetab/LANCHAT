package SourceCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSenderServer extends Thread {
    private Socket socket;
    public MessageSenderServer(Socket socket){
        this.socket = socket;

    }

    @Override
    public void run() {
        super.run();
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output  = new PrintWriter(socket.getOutputStream(),true);
            


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
