package GUI;

import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try {
            Socket socketMessageSend = new Socket("localhost",6000);
            Socket socketMessageReceive = new Socket("localhost",6001);
            Socket socketAuthenticationCodeSend = new Socket("localhost",6002);
            Socket socketAuthenticationReceive = new Socket("localhost",6003);



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
