package com.example.notes.Remote;

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
        //todo написать Сервер заново((

    }
}
