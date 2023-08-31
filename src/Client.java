import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static int port = 2500;
    static String host = "localhost";
    static DataInputStream in;
    static DataOutputStream out;
    static Socket socket;

    public static void main(String[] args) {

        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("Indtast domæne: ");
            String domain = input.next();

            try {
                out.writeUTF(domain);
                out.flush();
                System.out.println("IP adressen for " + domain + " er " + in.readUTF());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}