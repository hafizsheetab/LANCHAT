package Server;

import jdk.net.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerThread extends Thread{
    private Socket socketMessageReceive;
    private Socket socketMessageSend;
    private Socket socketAuthenticationReceive;
    private Socket socketAuthenticationSend;
    private String userName;
    //final static HashMap<String, Socket> socketsMessageReceive = new HashMap<>();
    final static HashMap<String, Socket> socketsMessageSend = new HashMap<>();
    //final static HashMap<String, Socket> socketsAuthenticationReceive = new HashMap<>();
    final static HashMap<String, Socket> socketsAuthenticationSend = new HashMap<>();

    public ServerThread(Socket socketMessageReceive, Socket socketMessageSend, Socket socketAuthenticationReceive, Socket socketAuthenticationSend) {
        this.socketMessageReceive = socketMessageReceive;
        this.socketMessageSend = socketMessageSend;
        this.socketAuthenticationReceive = socketAuthenticationReceive;
        this.socketAuthenticationSend = socketAuthenticationSend;
    }

    @Override
    public synchronized void run() {
        receiveMessage();
    }

    private void receiveMessage() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socketMessageReceive.getInputStream()));
            String userName = input.readLine();
            this.userName = userName;
            System.out.println(userName + " connected");
            Database.putUserName(userName);
            socketsMessageSend.put(userName,socketMessageSend);
            socketsAuthenticationSend.put(userName,socketAuthenticationSend);
            System.out.println(isAuthentic());
            while(true){
                String toUserName = input.readLine();
                String message = input.readLine();
                if(toUserName.equals("Server") && message.equals("activeList")){
                    System.out.println(userName);
                    sendMessage("Server",userName,Database.getUserNames());
                }
                else {
                    if(isAuthentic()){
                        System.out.println("paisi");
                        Runnable sendMessage = () -> sendMessage(userName,toUserName,message);
                        new Thread(sendMessage).start();
                    }
                    else {
                        break;
                    }
                }

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isAuthentic() throws Exception {

            PrintWriter output = new PrintWriter(socketAuthenticationReceive.getOutputStream(),true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socketAuthenticationReceive.getInputStream()));
            output.println("go");
            String messageLogClient = input.readLine();
            String messageLogServer = Database.getMessageLog();
            System.out.println(messageLogClient);
            System.out.println(messageLogServer);
            if(messageLogClient.equals(messageLogServer)){
                //System.out.println("baal");
                return true;
            }
            else {
                System.out.println(userName + " got disconnected");
            }
            return false;

    }

    private void sendMessage(String fromUserName, String toUserName, String message) {
        try{
            Database.putIntoMessageLog(userName,toUserName,message);
            sendAuthentication();
            System.out.println(toUserName);
            PrintWriter output = new PrintWriter(socketsMessageSend.get(toUserName).getOutputStream(),true);
            if(fromUserName.equals("Server")){
                output.println("activeList");
            }
            else{
                output.println("message");
            }
            output.println(fromUserName + " : " + message);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void sendAuthentication() throws IOException {
        String messageLog = Database.getMessageLog();
        sendAuthenticationToAllSockets(messageLog);
    }

    private void sendAuthenticationToAllSockets(String messageLog) throws IOException {
        Iterator iterator = socketsAuthenticationSend.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry element = (Map.Entry)iterator.next();
            Socket socket = (Socket)element.getValue();
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            output.println(messageLog);
            System.out.println("Authentication sent to " + (String)element.getKey());
        }
    }



}
