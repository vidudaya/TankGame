package slicktest;

import org.newdawn.slick.Animation;

/**
 *
 * @author HitMan
 */
public class Life {

    private int x;
    private int y;
    private int time;
    private Animation anime;

    public Life(int x, int y, int time, Animation anime) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.anime = anime;
    }

    public int getTime() {
        return time;
    }

    public Animation getAnime() {
        return anime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void decayTime(int delta) {
        time -= delta;
    }
}
