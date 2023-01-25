package com.example.notes.Remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private int Port;
    private String IP;
    private String HostAddress;

    public String getHostAddress() {
        return HostAddress;
    }

    public void setHostAddress(String hostAddress) {
        HostAddress = hostAddress;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int port) {
        Port = port;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }


    @Override
    public void run() {
        System.out.println("Стартовал");

        try(ServerSocket serverSocket = new ServerSocket(Port)){
            Socket socket = serverSocket.accept();
            System.out.println("Connection acepted");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while(!socket.isClosed()){
                System.out.println("Server reading from chanel");
                String entry = in.readUTF();
                System.out.println("Read from client message - "+entry);
                System.out.println("server try writing to channel");
                if(entry.equalsIgnoreCase("quit")){
                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - "+entry+" - OK");
                    out.flush();
                    Thread.sleep(3000);
                    break;
                }
                out.writeUTF("Server reply - "+entry + " - OK");
                System.out.println("Server Wrote message to client.");

// освобождаем буфер сетевых сообщений (по умолчанию сообщение не сразу отправляется в сеть, а сначала накапливается в специальном буфере сообщений, размер которого определяется конкретными настройками в системе, а метод  - flush() отправляет сообщение не дожидаясь наполнения буфера согласно настройкам системы
                out.flush();

            }

// если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сам сокет общения на стороне сервера!
            socket.close();

            // потом закрываем сокет сервера который создаёт сокеты общения
            // хотя при многопоточном применении его закрывать не нужно
            // для возможности поставить этот серверный сокет обратно в ожидание нового подключения

            System.out.println("Closing connections & channels - DONE.");
            } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            }finally{
            System.out.println("server ends");
        }

    }
}
