import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    static int port = 2500;
    static DataOutputStream out;
    static DataInputStream in;
    static ServerSocket server;
    static Socket socket;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(port);
            socket = server.accept();

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true){

            try {
                String modtagetDomain = in.readUTF();
                System.out.println("domain modtaget: " + modtagetDomain);
                InetAddress adress = InetAddress.getByName(modtagetDomain);
                System.out.println("Host navn: " + adress.getHostName());
                System.out.println("IP adresse: " + adress.getHostAddress());
                out.writeUTF(adress.getHostAddress());
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}