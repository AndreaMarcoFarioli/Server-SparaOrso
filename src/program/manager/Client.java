package program.manager;

import packetManager.ContentTypes;
import program.game.Match;
import socketManager.EventSocketOriented;
import socketManager.SocketManager;
import socketManager.extensions.MiddlewareExtensionBase64;

import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static int clientsID;
    private static final ArrayList<Client> clients = new ArrayList<>();

    private final EventSocketOriented eventSocketOriented;
    private final int id;
    private final Match match;

    public Client(Socket socket) throws Exception {
        SocketManager socketManager = new SocketManager(socket, new MiddlewareExtensionBase64());
        socketManager.on((request, response) -> {
            System.out.println(request);
        });
        eventSocketOriented = new EventSocketOriented(socketManager);
        eventSocketOriented.setContentType(ContentTypes.BASE64);

        match = new Match(eventSocketOriented);
        id = clientsID;
        clientsID++;
        clients.add(this);
        generateEvents();
        eventSocketOriented.start();
        System.out.println(clients);
    }

    public void generateEvents() throws Exception {
        eventSocketOriented.on("connection", (request, response) -> {
            try {
                eventSocketOriented.emit("connected");

            } catch (Exception e) {
                System.out.println("connected");
                e.printStackTrace();
            }
        });

        eventSocketOriented.on("closing", (request, response) -> {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static int getClientID() {
        return clientsID;
    }

    public void close() throws Exception {
        eventSocketOriented.close();
        clients.remove(this);
}

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "eventSocketOriented=" + eventSocketOriented +
                ", id=" + id +
                ", match=" + match +
                '}';
    }
}
