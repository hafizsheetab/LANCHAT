package SourceCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread {
    final static HashMap<String,Socket> users = new HashMap<>();
    private Socket socket;
    private ServerSocket serverSocket;
    public ServerThread(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.socket = serverSocket.accept();
    }
    @Override
    public synchronized void  run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String userName = input.readLine();
            System.out.println(userName + " connected");
            users.put(userName,socket);
            while(true){
                //System.out.println(userName +" Typing To userName");
                String toUserName = input.readLine();
                //System.out.println(userName +" Typing Message");
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
