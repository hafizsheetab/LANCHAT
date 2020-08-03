package SourceCode;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static void main(String[] args){
        try {
                ServerSocket serverSocket = new ServerSocket(6000);
                while (true){
                    Socket socket = serverSocket.accept();
                    //System.out.println("connected");
                    MessageReceivingThread receivingThread = new MessageReceivingThread(socket);
                    receivingThread.start();
                }


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void connect(String username) {

    }


}
