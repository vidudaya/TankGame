/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.*;
import org.newdawn.slick.tests.xml.Entity;
/**
 *
 * @author DELL
 */
public class Coins {
   
    private int x;
    private int y;
    private int value;
    private int time;
    private Animation anime;

    public Coins(int x, int y, int value, int time, Animation anime) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.time = time;
        this.anime = anime;
    }

    public int getTime() {
        return time;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Animation getAnime() {
        return anime;
    }

    public void decayTime(int delta) {
        time-=delta;
    }
}
