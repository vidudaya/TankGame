package slicktest;

/**
 *
 * @author HitMan
 */
public class MakeMap {

    public int[][] markBricks(String s, int cell_Size) {
        String points[] = s.split(";");
        int bricks[][] = new int[points.length][3];

        for (int i = 0; i < bricks.length; i++) {
            String temp[] = points[i].split(",");
            bricks[i][0] = (Integer.parseInt(temp[0]) - 1) * cell_Size;
            bricks[i][1] = (Integer.parseInt(temp[1]) - 1) * cell_Size;
        }
        return bricks;
    }

    public int[][] markStones(String s, int cell_Size) {
        String points[] = s.split(";");
        int stones[][] = new int[points.length][2];

        for (int i = 0; i < stones.length; i++) {
            String temp[] = points[i].split(",");
            stones[i][0] = (Integer.parseInt(temp[0]) - 1) * cell_Size;
            stones[i][1] = (Integer.parseInt(temp[1]) - 1) * cell_Size;
        }
        return stones;
    }

    public int[][] markWater(String s, int cell_Size) {
        String points[] = s.replaceAll("#", "").split(";");
        int water[][] = new int[points.length][2];

        for (int i = 0; i < water.length; i++) {
            String temp[] = points[i].split(",");
            water[i][0] = (Integer.parseInt(temp[0]) - 1) * cell_Size;
            water[i][1] = (Integer.parseInt(temp[1]) - 1) * cell_Size;
        }
        return water;
    }

    public int[][] makeTanksPositions(String s, int cell_size) {
        String sets[] = s.replaceAll("#", "").split(":");
        int n = sets.length - 1;
        int tanks[][] = new int[n][7];
        for (int i = 1; i <= n; i++) {
            String temp[] = sets[i].split(";");
            int tanknumber = Integer.parseInt(temp[0].charAt(1) + "");
            int x = (Integer.parseInt(temp[1].split(",")[0]) - 1) * cell_size;
            int y = (Integer.parseInt(temp[1].split(",")[1]) - 1) * cell_size;
            int v = Integer.parseInt(temp[2]);
            tanks[i - 1][0] = x;
            tanks[i - 1][1] = y;
            tanks[i - 1][2] = v;
        }

        return tanks;
    }

    public void updateBrickStatus(String s, int cell_size, int mapWidth, int mapHeight) {
        String tokens[] = s.replaceAll("#", "").split(":");

        String brick_status = tokens[tokens.length - 1];
        String status[] = brick_status.split(";");
        for (int i = 0; i < status.length; i++) {
            int x = (Integer.parseInt(status[1].split(",")[0]) - 1) * cell_size;
            int y = (Integer.parseInt(status[1].split(",")[1]) - 1) * cell_size;
            int level = (Integer.parseInt(status[1].split(",")[2]));

            for (int j = 0; j < Game.bricks_coordinates.length; j++) {
                if (Game.bricks_coordinates[j][0] == x && Game.bricks_coordinates[j][1] == y) {
                    Game.bricks_coordinates[j][2] = level;
                }
            }
        }
    }
}
