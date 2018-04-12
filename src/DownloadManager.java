import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DownloadManager {
    public static void saveFile(String filename, String url) throws IOException {
        BufferedInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new BufferedInputStream(new URL(url).openStream());
            out = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Input http address to file: ");
        Scanner scanner = new Scanner(System.in);

        String address = scanner.nextLine();
        String fileName = address.substring(address.lastIndexOf('/') + 1);

        try {
            saveFile(fileName, address);
            System.out.println("File download successfully.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
