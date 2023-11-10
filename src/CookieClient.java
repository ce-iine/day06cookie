import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CookieClient {

    public static final int PORT = 3000;

    public static void main(String[] args) throws Exception {

        // connect to server
        Socket socket = new Socket("127.0.0.1", PORT); // Creates a stream socket and connects it to the specified port number on the named host.
        System.out.println("connected to server");

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        InputStream is = socket.getInputStream();// start reading
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        while (true) {
            Console cons = System.console();
            String line = cons.readLine("Please enter a message: ");
            line = line.trim() + "\n";

            bw.write(line);
            bw.flush();

            while (true) {
                String reply = br.readLine();
                reply =reply.trim();
                if ("end".equals(reply)){
                    break;
                }
                System.out.printf("Cookie fortune:\n %s\n\n", reply);
            }
        }

    }
}
