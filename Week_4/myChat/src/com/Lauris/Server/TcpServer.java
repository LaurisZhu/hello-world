package com.Lauris.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lawrence on 2017/8/4.
 */
public class TcpServer {
    private ServerSocket ss;
    //使用线程池处理服务器读取客户端发来的信息
    private ExecutorService exes;

    //存储客户端与对应的打印流信息
    public Map<String,PrintWriter> info;

    //设置使用的端口
    private int setPort() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你需要的端口号");
        int port = input.nextInt();
        return port;
    }

    private TcpServer() {
        try {
            ss = new ServerSocket(setPort());
            exes = Executors.newCachedThreadPool();
            info = new HashMap<String,PrintWriter>();

        } catch (IOException e) {
            e.printStackTrace();
        }

        InetAddress ip = ss.getInetAddress();
        int port = ss.getLocalPort();
        if (port != -1)
            System.out.println("服务端建立成功！IP:"+ip+" 端口："+port);
        else System.out.println("服务端端口绑定失败");
    }
    //单例模式
    private static TcpServer tcpServer = new TcpServer();
    public static TcpServer getTcpServer() {
        return tcpServer;
    }


    public synchronized void setPeople(String name,PrintWriter printWriter) {
        info.put(name,printWriter);
    }

    public synchronized void removePeople(String name) {
        info.remove(name);
    }

    //将信息从客户端发送给所有在线的客户端
    public synchronized void sendToAll(String line) {
        for (PrintWriter pWriter:info.values()
             ) {
            pWriter.println(line);
        }
    }

    //返回当前在线人数
    public synchronized String showPeopleNumble() {
         return Integer.toString(info.keySet().size());
    }

    private void start() {
        try {
            while (true){
                System.out.println("等待连接.......");
                Socket socket = ss.accept();
                System.out.println(socket.getLocalAddress()+"连接建立成功");

                /**
                 * 每一个客户端都交由线程池来管理，
                 */
                exes.execute(new ListenOneClient(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TcpServer.getTcpServer().start();
    }
}
