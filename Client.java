import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Client {
    public static int port = 13;
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify filename");
            return;
        }
        String filename = args[0];
        try (Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), port)) {
            FileInputStream input = new FileInputStream(filename);
            OutputStream output = socket.getOutputStream();
            InputStreamReader reader = new InputStreamReader(input);
            int c;
            while ((c = reader.read()) != -1) {
                output.write((char) c);
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
