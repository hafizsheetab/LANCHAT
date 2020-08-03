package SourceCode;

import java.net.ServerSocket;

public class Server {
    public static void main(String[] args){
        try {
                ServerSocket serverSocketMessaging = new ServerSocket(6000);
                ServerSocket serverSocketBlockChaining = new ServerSocket(5000);
                while (true){

                    MessagingThread messagingThread = new MessagingThread(serverSocketMessaging);
                    messagingThread.start();
                }


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void connect(String username) {

    }


}
