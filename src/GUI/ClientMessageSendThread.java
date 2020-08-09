package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMessageSendThread extends Thread{


    public static void sendUserName(Socket socket,String userName) throws IOException {
        PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
        output.println(userName);
    }
    public static void sendMessage(Socket socket,String toUserName,String message) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

                //System.out.println("to userName:");
                output.println(toUserName);
                //System.out.println("Message :");
                output.println(message);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
