package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){

        try {
                ServerSocket serverSocketMessageReceive = new ServerSocket(6000);
                ServerSocket serverSocketMessageSend = new ServerSocket(6001);
                ServerSocket serverSocketAuthenticationCodeReceive = new ServerSocket(6002);
                ServerSocket serverSocketAuthenticationCodeSend = new ServerSocket(6003);
                while (true){
                    Socket socketMessageReceive = serverSocketMessageReceive.accept();
                    Socket socketMessageSend = serverSocketMessageSend.accept();
                    Socket socketAuthenticationReceive = serverSocketAuthenticationCodeReceive.accept();
                    Socket socketAuthenticationSend = serverSocketAuthenticationCodeSend.accept();
                    ServerThread serverThread = new ServerThread(socketMessageReceive,socketMessageSend,socketAuthenticationReceive,socketAuthenticationSend);
                    serverThread.start();

                }


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

}
