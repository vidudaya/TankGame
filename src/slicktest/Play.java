/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

public class Play {

    public void start() {
        Client client = new Client();
        client.sendMessage("JOIN#");
    }

    public void makeMove(String move) {
        Client client = new Client();
        client.sendMessage(move);
    }
    
     
}
