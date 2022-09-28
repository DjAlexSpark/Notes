package com.example.notes.Remote;

import com.example.notes.Param;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;


public class Server extends Thread {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private ObjectOutputStream objOut;
    private int Port = 4004;
    private String IP = "";
    private String hostAddress = "";

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int port) {
        this.Port = port;
    }

    public String getIP() {
        return String.valueOf(IP);
    }

    public void setIP(String IP) {
        this.IP = String.valueOf(IP);
    }
    @Override
    public void run() {
        try {
            try {
                server = new ServerSocket(Port); // серверсокет прослушивает порт 4004
                InetAddress inetAddress=InetAddress.getLocalHost();
                String urlString = "http://checkip.amazonaws.com/";
                URL url = new URL(urlString);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    setIP(br.readLine());
                    System.out.println(getIP());
                }
                setHostAddress(inetAddress.getHostAddress());
//                System.out.println(server.getInetAddress()+" "+server.getLocalPort()+""+server.getLocalSocketAddress());
                System.out.println("Сервер запущен!"); // хорошо бы серверу
                //   объявить о своем запуске
                clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться
                try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к созданию потоков ввода/вывода.
                    // теперь мы можем принимать сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    objOut = new ObjectOutputStream(clientSocket.getOutputStream());
                    //String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    //System.out.println(word);

                    // не долго думая отвечает клиенту
                    //out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    //out.flush(); // выталкиваем все из буфера
                    objOut.writeObject(Param.notes);
                } finally { // в любом случае сокет будет закрыт
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}