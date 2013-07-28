package slicktest;

/**
 *
 * @author HitMan
 */
public class MoveAndShoot {

    public String getTheNextMove() {
        makeEdges();
        String next_move;
        next_move = "SHOOT#";
        return next_move;
    }

    public void makeEdges() {
        boolean edges[][] = new boolean[Game.mapLenght * Game.mapWidth][Game.mapLenght * Game.mapWidth];

        for (int i = 0; i < Game.mapLenght; i++) {
            for (int j = 0; j < Game.mapWidth; j++) {
                if (notBlocked(i, j)) {
                    int now_cell = getTheMapNumber(i, j);

                    System.out.println("now cell " + now_cell);

                    int neib_1 = now_cell + Game.mapWidth;// height or width ?????????????????
                    int neib_2 = now_cell - Game.mapWidth;
                    int neib_3 = now_cell + 1;
                    int neib_4 = now_cell - 1;
                    System.out.println(neib_1 + "  " + neib_2 + "  " + neib_3 + "  " + neib_4+ "   @@@ "+Game.mapLenght+"  "+Game.mapWidth);
                   
                    System.out.println(neib_1 / Game.mapWidth+"  "+neib_1 % Game.mapWidth);
                    if (isValidMapNumber(neib_1) && notBlocked(neib_1 / Game.mapWidth, neib_1 % Game.mapWidth)) {// check the side
                        edges[now_cell][neib_1] = true;
                        edges[neib_1][now_cell] = true;
                    }
                    System.out.println(neib_2 / Game.mapWidth+"  "+neib_2 % Game.mapWidth);
                    if (isValidMapNumber(neib_2) && notBlocked(neib_2 / Game.mapWidth, neib_2 % Game.mapWidth)) {// check the side
                        edges[now_cell][neib_2] = true;
                        edges[neib_2][now_cell] = true;
                    }
                    System.out.println(neib_3 / Game.mapWidth+"  "+neib_3 % Game.mapWidth);
                    if (isValidMapNumber(neib_3) && notBlocked(neib_3 / Game.mapWidth, neib_3 % Game.mapWidth)) {// check the side
                        edges[now_cell][neib_3] = true;
                        edges[neib_3][now_cell] = true;
                    }
                    System.out.println(neib_4 / Game.mapWidth+"  "+neib_4 % Game.mapWidth);
                    if (isValidMapNumber(neib_4) && notBlocked(neib_4 / Game.mapWidth, neib_4 % Game.mapWidth)) {// check the side
                        edges[now_cell][neib_4] = true;
                        edges[neib_4][now_cell] = true;
                    }
                }
            }

        }

        for (int i = 0; i < Game.mapLenght * Game.mapWidth; i++) {
            for (int j = 0; j < Game.mapLenght * Game.mapWidth; j++) {
                System.out.println(i + " to " + j + "   " + edges[i][j]);
            }
            System.out.println("");
        }
    }

    public boolean isValidMapNumber(int num) {
        boolean status = true;
        if (num < 0 || num >= Game.mapLenght * Game.mapWidth) {
            status = false;
        }
        return status;
    }

    public int getTheMapNumber(int i, int j) {
        return i * Game.mapLenght + j;
    }

    public boolean notBlocked(int i, int j) {
        boolean status = false;
        if (Game.map[i][j] != 1 && Game.map[i][j] != 2 && Game.map[i][j] != 3) {
            status = true;
        }
//        if (Game.map[j][i] != 1 && Game.map[j][i] != 2 && Game.map[j][i] != 3) {
//            status = true;
//        }
        if(!status){
        System.out.println("############################################  i = "+i+"  j = "+j);
        }//System.out.println("i = "+i+" j = "+j);
         
        return status;

    }

    public void mapRandom() {
        int myTank = Game.myTank;
        int x = Game.tank_positions[myTank][0];
        int y = Game.tank_positions[myTank][1];
        int d = Game.tank_positions[myTank][2];
        String move;
        int xx, yy;

        if (isValidCell(x + 1, y, Game.mapWidth, Game.mapLenght)) {
            if (Game.map[x][y] == 5 || Game.map[x][y] == 6) {
            }
        }
    }

    public boolean isValidCell(int x, int y, int mapWidth, int mapLength) {
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
