package SourceCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            Socket messagingSocket = new Socket("localhost",6000);
            //Socket blockChainingSocket = new Socket("localhost",5000);
            ClientMessageReceive messageReceive = new ClientMessageReceive(messagingSocket);
            messageReceive.start();
            ClientMessageSend messageSend = new ClientMessageSend(messagingSocket);
            messageSend.start();
            //BlockChainingThreadClient blockChainingThreadClient = new BlockChainingThreadClient(blockChainingSocket);

            //System.out.println(messageReceive.isAlive());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
