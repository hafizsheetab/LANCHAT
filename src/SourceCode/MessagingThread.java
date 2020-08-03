package SourceCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MessagingThread extends Thread {
    final static HashMap<String,Socket> users = new HashMap<>();
    private Socket socket;
    private ServerSocket serverSocket;
    public MessagingThread(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.socket = serverSocket.accept();
    }
    @Override
    public synchronized void  run() {
        receiveMessage();

    }
    private void receiveMessage(){
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String userName = input.readLine();
            users.put(userName,socket);
            while(true){
                String toUserName = input.readLine();
                String message = input.readLine();
                sendMessage(userName,toUserName,message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(String fromUserName,String toUserName,String message) {
        try {
            Socket socket = users.get(toUserName);
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            output.println(fromUserName);
            output.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
