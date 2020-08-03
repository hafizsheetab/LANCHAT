package SourceCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMessageSend extends Thread {
    Scanner scan = new Scanner(System.in);
    private Socket socket;
    public ClientMessageSend(Socket socket){
        this.socket = socket;
    }

    @Override
    public synchronized void run() {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Username:");
            output.println(scan.next());
            while (true){
                System.out.println("toUsername: ");
                output.println(scan.next());
                System.out.println("Message: ");
                output.println(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
