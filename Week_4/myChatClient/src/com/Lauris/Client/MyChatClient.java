package com.Lauris.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lawrence on 2017/8/5.
 */
public class MyChatClient {
    private Socket socket;

    //为每一个登陆的客户端设置昵称
    private void setName(Scanner scanner) {
        String name;
        String temp;
        PrintWriter pWriter;
        BufferedReader bReader;
        try {
            pWriter = new PrintWriter(socket.getOutputStream(),true);
            bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                System.out.println("请输入您的用户名：");
                name = scanner.nextLine();
                //该判断有问题，自动进入
                if (name.trim().equals("")) {
                    System.out.println("ERROR:输入昵称不能为空");
                    continue;
                }
                pWriter.println(name);
                temp = bReader.readLine();
                if (temp.equals("成功")) {
                    System.out.println("登录成功！");
                    System.out.println("输入“over”结束聊天");
                    break;
                } else System.out.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过输入ip 地址和 端口号来连接服务器， 输入还没有做简单处理，比如取出前后空格等，
    private void setServer(Scanner scanner) {
        System.out.println("请输入服务器IP：");
        String ip = scanner.next();
        System.out.println("请输入目标端口：");
        int port = scanner.nextInt();
        try {
            socket = new Socket(ip,port);
            System.out.println("目标服务器连接成功！");
        } catch (IOException e) {
            System.out.println("Error: 连接失败！");
            e.printStackTrace();
        }
    }

    private void start() {
        String temp;
        Scanner scanner = new Scanner(System.in);
        setServer(scanner);
        setName(scanner);

        //用多线程来读取服务器可能连续的发送信息，使用线程池来管理线程
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ClientListener());


        try {

            PrintWriter pWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                temp = scanner.nextLine();
                //当输入over时结束跳出循环，并关闭socket连接
                if (temp.equals("over")) {
                    break;
                }
                pWriter.println(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String[] args) {
        MyChatClient client = new MyChatClient();
        client.start();
    }

    /**
     * 内部类，用来读取服务器发过来的信息
     */
    private class ClientListener implements Runnable {

        @Override
        public void run() {
            BufferedReader bReader;
            String message;
            try {
                bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((message = bReader.readLine()) != null) {
                    System.out.println(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
