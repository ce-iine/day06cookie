import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CookieServer {

    public static final int PORT = 3000;
    // static String lineFromFile = "";

    public static void main(String[] args) throws Exception {

        int port = PORT; // port = 3000
        String fortunesfile = "";
        switch (args.length) {
            case 1:
                fortunesfile = args[0]; //
                break;
            case 2:
                fortunesfile = args[0];
                port = Integer.parseInt(args[1]); // what does this do
                break;
            default:
                System.err.println("Argument error");
                System.exit(1);
        }

        // when start server, take 2 parameters (port number, cookie file)
        System.out.printf("Loading fortunes file %s\n", fortunesfile);
        Fortunes fortunes = new Fortunes(fortunesfile);
        System.out.printf("listening on port: %s\n", PORT);
        ServerSocket server = new ServerSocket(PORT);

        // wait for client connection to arrive, we are now blocked
        System.out.println("Waiting for client connection");

        while (true) {
            Socket client = server.accept();
            // client connection arrive
            System.out.println("New client connection");

            InputStream is = client.getInputStream();// to start talking
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            OutputStream os = client.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            boolean stop = false;

            while (!stop) {
                String line = br.readLine();
                System.out.printf("CLIENT REQUEST: %s\n", line);

                String[] getWords = line.split(" "); // stores the string the client input into a list
                Integer lineAmt = Integer.parseInt(getWords[1]); // convert the cookie 'number' into an int
                System.out.printf("%d fortunes requested:\n", lineAmt);

                fortunes.get(lineAmt).stream()
                    .map(l -> "%s\n".formatted(l))
                    .forEach(l -> {
                        try {
                            System.out.println(">>> line = " + l);
                            bw.write(l);
                            bw.flush();
                        } catch (Exception ex) {}
                    });
                }
                is.close();
                os.close();
            } 
        }
    }

// File fileToRead = new File(args[0]);
// FileReader fr = new FileReader(fileToRead);
// BufferedReader filebr = new BufferedReader(fr);

// OutputStream os = client.getOutputStream();
// OutputStreamWriter osw = new OutputStreamWriter(os);
// BufferedWriter bw = new BufferedWriter(osw);

// for (int i = 1; i <= lineAmt; i++) {
// lineFromFile = filebr.readLine();
// System.out.println(lineFromFile);
// bw.write(lineFromFile);
// }