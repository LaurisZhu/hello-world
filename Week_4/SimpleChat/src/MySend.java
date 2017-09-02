import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Lawrence on 2017/8/4.
 */
public class MySend implements Runnable {

    private String id;
    private int port;
    private Socket s;

    public MySend (String id , int port) {
        this.id = id;
        this.port = port;
    }


    @Override
    public void run() {
        try{
            s = new Socket(id,port);
            System.out.println("连接创建成功");
        } catch (IOException e) {
            throw  new RuntimeException("catch::ID或端口错误！");
        }

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufIn = null;
        PrintWriter printer = null;
        try{
            bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            printer = new PrintWriter(s.getOutputStream(),true);
        } catch (Exception e) {
            throw new RuntimeException("流创建失败");
        }
        String in;
        System.out.println("开始聊天！输入“over”结束聊天！");
        try{
            while ((in = bufReader.readLine()) != null) {
                if ("over".equals(in)) {
                    break;
                }
                printer.println(in);
                System.out.println(s.getLocalAddress().toString()+"::"+in);
                System.out.println(id + "::" + bufIn.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufReader.close();
                bufIn.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printer.close();
        }

    }
}
