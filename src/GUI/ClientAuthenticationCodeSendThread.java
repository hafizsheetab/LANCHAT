package GUI;

import Server.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientAuthenticationCodeSendThread extends Thread{
    private Socket socket;
    public ClientAuthenticationCodeSendThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        sendCode();
    }

    private void sendCode() {
        while (true){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
                String signal = input.readLine();
                String messageLog =(String) Storage.getObject("messageLog");
                output.println(messageLog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
