package com.Lauris.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Lawrence on 2017/8/4.
 * 本可作为内部类，但是为了看上去清楚，将内部类取出
 */
public class ListenOneClient implements Runnable {
    private Socket socket;
    private String name;

    public ListenOneClient(Socket socket) {
        this.socket = socket;
    }

    //从客户端获取当前昵称并做后台重复性判断。
    private String  getName() {

        try {
            BufferedReader buf =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true) {

                name = buf.readLine();
                if (TcpServer.getTcpServer().info.containsKey(name)) {
                    pWriter.println("Error:输入昵称重复");
                    return "FAIL";
                }
                else {
                    TcpServer.getTcpServer().setPeople(name,pWriter);
                    pWriter.println("成功");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SUCCEED";
    }

    @Override
    public void run() {
        String temp = getName();
        try {
            //PrintWriter out = new PrintWriter(socket.getOutputStream()，true);
            System.out.println("获取名称:"+name+":"+temp);

            temp = "[系统信息]"+name+"加入聊天室";
            TcpServer.getTcpServer().sendToAll(temp);
            temp = "聊天室当前人数：" + TcpServer.getTcpServer().showPeopleNumble();
            TcpServer.getTcpServer().sendToAll(temp);
            //获取输入
            BufferedReader bReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //该线程一直从客户端读取信息并转发给其他客户端
            while ((temp = bReader.readLine())!=null) {
                temp = name+":"+temp;
                System.out.println(temp);
                TcpServer.getTcpServer().sendToAll(temp);
                if (socket.isClosed()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            TcpServer.getTcpServer().removePeople(name);
            System.out.println(name + "断开连接");
            temp = "[系统消息]" + name + "下线";
            TcpServer.getTcpServer().sendToAll(temp);
            if (socket != null) {
                try {
                    socket.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
