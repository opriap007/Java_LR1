import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PortScanner {
    public static void main(String[] args) {
        System.out.print("Input host or IP-address for scanning ports: ");

        Scanner scanner = new Scanner(System.in);
        String host = scanner.next();
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("Host error: " + e.toString());
            return;
        }

        System.out.println("Host for scanning: " + host);

        System.out.println("Input range of ports");
        System.out.print("From: ");
        String start = scanner.next();
        System.out.print("To: ");
        String end = scanner.next();

        int minPort, maxPort;
        try {
            minPort = Integer.parseInt(start);
            maxPort = Integer.parseInt(end);
        } catch (NumberFormatException e) {
            System.out.println("Please, input valid values: " + e.toString());
            return;
        }

        System.out.println("Scanning ports...");
        for (int port = minPort; port <= maxPort; port++) {
            System.out.print("Port: " + port);
            try {
                InetSocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
                Socket socket = new Socket();
                socket.connect(socketAddress, 100);

                System.out.println(" OPENED" );
                socket.close();
            } catch (IOException ioe) {
                System.out.println(" closed");
            }
        }
    }
}
