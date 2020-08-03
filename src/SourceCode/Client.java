package SourceCode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost",6000);
            ClientMessageReceive messageReceive = new ClientMessageReceive(socket);
            messageReceive.start();
            ClientMessageSend messageSend = new ClientMessageSend(socket);
            messageSend.start();
            System.out.println(messageReceive.isAlive());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
