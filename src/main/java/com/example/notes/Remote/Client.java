package com.example.notes.Remote;
import com.example.notes.MyObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread {

    private ArrayList<MyObject> arrayList;
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void setArrayList(ArrayList<MyObject> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<MyObject> getArrayList() {
        return arrayList;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void run() {
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(arrayList);

            String response = (String) in.readObject();
            System.out.println(response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
