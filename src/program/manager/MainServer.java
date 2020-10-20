package program.manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer implements Runnable {
    private final ServerSocket serverSocket;

    public MainServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public MainServer() throws IOException{
        this(5000);
    }

    @Override
    public void run() {
        try {
            listenForClientConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenForClientConnection() throws Exception {
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            Client client = new Client(socket);
            System.out.println(Client.getClients().size());
        }
    }

}
