/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author HitMan
 */
public class AI {

    public static boolean[][] edges = Game.edges;
    //public static boolean seen[] = new boolean[10];
    public static int color[];
    public static int n = Game.mapLenght * Game.mapWidth, num = 0;
    public static int parent[];
    public static int distance[];

    public void bfs(int s) {

        Queue<Integer> q = new LinkedList<Integer>();
        color[s] = 1;
        distance[s] = 0;
        parent[s] = -1;
        q.add(s);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v = 0; v < n; ++v) {// v<= n ??
                if (color[v] == 0 && edges[u][v]) {
                    color[v] = 1;
                    distance[v] = distance[u] + 1;
                    parent[v] = u;
                    q.add(v);
                }
            }
            color[u] = 2;

        }

    }

    public String getPath() {
        color = new int[n + 1];
        parent = new int[n + 1];
        distance = new int[n + 1];
        num = 1;

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        int myPlace = (Game.tank_positions[Game.myTank][0] / Game.SIZE) * Game.mapLenght + Game.tank_positions[Game.myTank][1] / Game.SIZE;

        System.out.println("myplace = " + myPlace + "  " + Game.tank_positions[Game.myTank][0] / Game.SIZE + "   " + Game.tank_positions[Game.myTank][1] / Game.SIZE);
        new AI().bfs(myPlace);

//        System.out.println(Arrays.toString(color));
//        System.out.println(Arrays.toString(distance));
//        System.out.println(Arrays.toString(parent));

        int minDis = Integer.MAX_VALUE;
        int destination = 10;
        ABC:
        for (int i = 0; i < Game.coins.size(); i++) {
            int place = (Game.coins.get(i).getX() / Game.SIZE) * Game.mapLenght + (Game.coins.get(i).getY() / Game.SIZE);
            int remaining_life = (Game.coins.get(i).getTime());

//            if (remaining_life > distance[place] + 3) {  // min dis walin if daanna
//                if (minDis > remaining_life) {
//                    minDis = remaining_life; // aula ? life ekak wadi ekak hambunoth eka chase karanawa laga eka athaarala
//                    destination = place;
//                }
//                //break ABC;
//            }

//            if (remaining_life > distance[place] + 3) {  // min dis walin if daanna
//                if (minDis > remaining_life) {
//                    minDis = distance[place]; 
//                    destination = place;
//                }
//                //break ABC;
//            }
//            
            if (distance[place] < minDis) {
                minDis = distance[place];
                destination = place;
            }


        }

        String move = "SHOOT#";
        System.out.println("Destination = " + destination);
        int target_cell = destination;
        while (parent[destination] != myPlace) {
            destination = parent[destination];
            if (destination <= -1) {
                return move;
            }
            if (parent[destination] == myPlace) {
                target_cell = destination;
            }
        }
        System.out.println("Target cell == " + target_cell);
        System.out.println("go from " + myPlace + "  to  " + target_cell);


        int myView = Game.tank_positions[Game.myTank][2];

        if (target_cell == myPlace + 10) {
            move = "RIGHT#";
        } else if (target_cell == myPlace - 10) {
            move = "LEFT#";
        } else if (target_cell == myPlace + 1) {
            move = "DOWN#";
        } else if (target_cell == myPlace - 1) {
            move = "UP#";
        }
        return move;
    }
}
