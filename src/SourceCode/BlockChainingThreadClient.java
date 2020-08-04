package SourceCode;

import java.net.Socket;

public class BlockChainingThreadClient extends Thread {
    private Socket socket;
    public BlockChainingThreadClient(Socket socket){
        this.socket = socket;
    }
}
