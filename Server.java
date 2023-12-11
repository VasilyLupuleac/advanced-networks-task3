import java.net.*;
import java.io.*;

public class Server {
    public static int port = 13;
    protected static int fileCounter = 1;
    protected static String baseFilename = "file";
    protected static String extension = ".txt";
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket connectionSocket = serverSocket.accept()) {
                    InputStream rawStream = connectionSocket.getInputStream();
                    InputStream bufferedStream = new BufferedInputStream(rawStream);
                    Reader reader = new InputStreamReader(bufferedStream);
                    int c;
                    FileWriter output = new FileWriter(new File(baseFilename + (fileCounter++) + extension));
                    while ((c = reader.read()) != -1) {
                        output.write((char)c);
                    }
                    output.close();
                    connectionSocket.close();
                }
                catch (IOException e) {
                    System.err.println("Failed to get the file from the client");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to open the server socket at port " + port);
        }
    }
}
