import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        ServerSocket serverSocket = null;
        while(true) {
            try {
                serverSocket = new ServerSocket(1111);
                socket = serverSocket.accept();
                out = new PrintWriter(socket.getOutputStream());
                System.out.println("Connection with client established");

            } catch (IOException e) {
                e.printStackTrace();
            }

            String result;
            String command = "python bme280.py";
            System.out.println("Getting data from the sensor");
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            System.out.println("Sending data:");
            while ((result = br.readLine()) != null) {
                System.out.println(result);
                out.println(result);
            }

            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Connection closed");
        }
        //listen for request on port
        //get the information from sensor
        //send information to client
    }
}
