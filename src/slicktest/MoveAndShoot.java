package slicktest;

/**
 *
 * @author HitMan
 */

public class MoveAndShoot {

    public String getTheNextMove() {
        String next_move;
        next_move = "SHOOT#";
        return next_move;
    }

    public void mapRandom() {
        int myTank = Game.myTank;
        int x = Game.tank_positions[myTank][0];
        int y = Game.tank_positions[myTank][1];
        int d = Game.tank_positions[myTank][2];
        String move;
        int xx,yy;
        
        if(checkBoaders(x+1, y, Game.mapWidth, Game.mapLenght)){
            if( Game.map[x][y]==50 || Game.map[x][y]==60){
         
            }
        }
    }

    public boolean checkBoaders(int x, int y, int mapWidth, int mapLength) {
        boolean status = true;
        if (x > mapWidth || y > mapLength) {
            status = false;
        }

        if (x < 0 || y < 0) {
            status = false;
        }

        return status;
    }
}
