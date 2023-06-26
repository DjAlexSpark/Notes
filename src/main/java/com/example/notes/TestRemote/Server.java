package com.example.notes.TestRemote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int Port;

    public int getPort() {
        return Port;
    }

    public void run() {
        System.out.println("Server started");

        try (ServerSocket serverSocket = new ServerSocket(Port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                Thread clientThread = new Thread(() -> {
                    try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                        while (!socket.isClosed()) {
                            NotMyObject receivedObject = (NotMyObject) in.readObject();
                            System.out.println("Received object: " + receivedObject.toString());
                            // process the object or generate a response

                            // send a response back to the client
                            NotMyObject response = new NotMyObject("response", 456);
                            out.writeObject(response);

                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                clientThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
