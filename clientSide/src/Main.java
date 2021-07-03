import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args)
    {
        String server = "192.168.0.227";
        try
        {
            Socket socket = new Socket(server, 1111);
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            getData(in);

            in.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getData(BufferedReader in) throws IOException
    {
        String line = in.readLine();
        while( line != null )
        {
            System.out.println( line );
            line = in.readLine();
        }
    }
}
