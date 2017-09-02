import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lawrence on 2017/8/4.
 */
public class MyReceive implements Runnable {
    private int port;
    private ServerSocket ss;

    public MyReceive(int port) {
        this.port = port;
        try{
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("catch::创建服务端失败！");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            Socket so =  ss.accept();

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(so.getInputStream()));
            PrintWriter printer = new PrintWriter(so.getOutputStream(),true);

            String line;
            while ((line = bufIn.readLine()) != null){
                printer.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
