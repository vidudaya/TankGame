package slicktest;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    BufferedWriter write;

    // this method is used to send the message through the Socket  
    void sendMessage(String msg) {
        try {
            clientSocket = new Socket("127.0.0.1", 6000);
            write = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            write.write(msg);
            write.flush();
            write.close();
            clientSocket.close();
            System.out.println("client>" + msg);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    String readMessage() throws ClassNotFoundException {
        String s = "";
        try {
            s = (String) in.readObject();
//            Server se = new Server();
//            se.readMessage();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return s;
    }
}
