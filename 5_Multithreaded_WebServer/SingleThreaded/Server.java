import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void run() throws IOException {
        int port= 8080;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true) {
            try {
                System.out.println("Server is running on port: " + port);
                Socket client = socket.accept();  //  → waits for client connection
                System.out.println("Client connected1: " + client.getInetAddress()); 
                System.out.println("Client connected2: " + client.getRemoteSocketAddress()); 

                PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); 

                toClient.println("Hello, from the server!");

                String clientMessage = fromClient.readLine();
                System.out.println("Message from client: " + clientMessage);

                toClient.close();
                fromClient.close();
                client.close(); 

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main (String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
} 