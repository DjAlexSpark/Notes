package com.example.notes.Remote;

import com.example.notes.MyObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private ArrayList<MyObject> arrayList;

    private int port;

    public int getPort() {
        return port;
    }

    public Server(ArrayList<MyObject>arrayList, int port) {
        this.arrayList = arrayList;
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Получаем список MyObject от клиента
            arrayList = (ArrayList<MyObject>) in.readObject();

            out.writeObject("Список успешно получен на сервере.");

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}