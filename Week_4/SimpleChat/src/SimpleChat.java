import java.util.Scanner;

/**
 * Created by Lawrence on 2017/8/4.
 */
public class SimpleChat {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入IP地址和端口号，格式如下：IP地址,端口号");
        String[] ask = in.nextLine().split(",");
        String ip = ask[0];
        int port = new Integer(ask[1]);

        new Thread(new MySend(ip,port)).start();
        new Thread(new MyReceive(port)).start();
    }
}
