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

            } catch (IOException e) {
                e.printStackTrace();
            }

            String result;
            String command = "python bme280.py";
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((result = br.readLine()) != null) {
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
