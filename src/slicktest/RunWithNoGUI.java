/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

/**
 *
 * @author DELL
 */
public class RunWithNoGUI {

    public static void main(String[] args) {
        Play play = new Play();
        play.start();
    }

    public void printMap(int a[][]) {
        System.out.println("###############################################");
        for (int i = 0; i < Game.mapWidth; i++) {
            for (int j = 0; j < Game.mapLenght; j++) {
                System.out.print(" " + Game.map[j][i]);
            }
            System.out.println("");
        }
        System.out.println("###############################################");
    }
}
