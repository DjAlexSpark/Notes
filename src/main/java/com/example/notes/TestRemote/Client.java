package com.example.notes.TestRemote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String ServerIP;
    private int Port;

    public void setServerIP(String serverIP) {
        ServerIP = serverIP;
    }

    public void setPort(int port) {
        Port = port;
    }

    public void run() {
        try (Socket socket = new Socket(ServerIP, Port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            NotMyObject obj = new NotMyObject("test", 123);
            out.writeObject(obj);

            while (!socket.isClosed()) {
                NotMyObject receivedObject = (NotMyObject) in.readObject();
                System.out.println("Received object: " + receivedObject.toString());
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
