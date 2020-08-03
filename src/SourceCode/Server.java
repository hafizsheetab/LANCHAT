package SourceCode;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        try {
                ServerSocket serverSocket = new ServerSocket(6000);
                while (true){

                    ServerThread serverThread = new ServerThread(serverSocket);
                    serverThread.start();
                }


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void connect(String username) {

    }


}
