package slicktest;

/**
 *
 * @author HitMan
 */
public class HandleTanks {

    public void setNewTankPositions(String global_update, int cell_size) {
        String tokens[] = global_update.split(":");

        for (int i = 1; i < tokens.length - 1; i++) {
            String set[] = tokens[i].split(";");
            int x = (Integer.parseInt(set[1].split(",")[0])) * cell_size;
            int y = (Integer.parseInt(set[1].split(",")[1])) * cell_size;
            Game.map[Game.tank_positions[i - 1][0] / cell_size][Game.tank_positions[i - 1][1] / cell_size] = 0;
            Game.tank_positions[i - 1][0] = x;
            Game.tank_positions[i - 1][1] = y;
            Game.tank_positions[i - 1][2] = Integer.parseInt(set[2]);//position
            Game.tank_positions[i - 1][3] = Integer.parseInt(set[3]);//whether shot
            Game.tank_positions[i - 1][4] = Integer.parseInt(set[4]);//health
            Game.tank_positions[i - 1][5] = Integer.parseInt(set[5]);//coins
            Game.tank_positions[i - 1][6] = Integer.parseInt(set[6]);//points
            if (Game.tank_positions[i - 1][4] > 0) {
                Game.map[x / cell_size][y / cell_size] = 4;
            }else{
                System.out.println("#########  Tank Destroyed = "+(i-1));
            }
        }
    }
}
