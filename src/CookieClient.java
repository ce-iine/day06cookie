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

            bw.write(line + "\n");
            // bw.newLine(); // can bw.append() also - .newLine() Writes a line separator.
            // is not necessarily a single newline ('\n') character.
            bw.flush();

            while (true) {
                String reply = br.readLine();
                System.out.printf("Cookie fortunes:\n %s\n", reply);
            }
        }

    }
}
