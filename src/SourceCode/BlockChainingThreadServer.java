package SourceCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockChainingThreadServer extends Thread {
    private Socket socket;
    private ServerSocket serverSocket;
    public BlockChainingThreadServer(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.socket = serverSocket.accept();
    }
}
