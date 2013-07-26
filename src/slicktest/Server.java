package slicktest;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket serverSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    BufferedReader reader;

    static boolean state = true;
    
    public Server() {
        try {
            this.serverSocket = new ServerSocket(7000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readMessage() throws IOException {

        connection = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        System.out.println("new ### " + reader.readLine());
        reader.close();
        serverSocket.close();
        connection.close();
    }
    
      public String readSENDMessage() throws IOException {

        connection = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String s = reader.readLine();
        System.out.println(s);
        reader.close();
        return s;
    }
}
