package com.example.notes;

import com.example.notes.Remote.Client;
import com.example.notes.Remote.Server;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        ArrayList<MyObject> arrayList = new ArrayList();
        Server server = new Server(arrayList, 5000);

        server.start();
        System.out.println("Port is "+server.getPort());
//        System.out.println(server.getLocalPort());
        Client client = new Client("127.0.0.1",5000);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Port is "+server.getPort());

    }
}
