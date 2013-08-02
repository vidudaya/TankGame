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

    public boolean checkBound(int x) {
        boolean good = true;
        if (x < 0 || x >= Game.mapLenght) {
            good = false;
        }
        return good;
    }

    public boolean checkLineOfSite() {
        int myX = Game.tank_positions[Game.myTank][0] / Game.SIZE;
        int myY = Game.tank_positions[Game.myTank][1] / Game.SIZE;
        int myFace = Game.tank_positions[Game.myTank][2];
        boolean hasSite = false;

        for (int i = 0; i < Game.tank_positions.length; i++) {
            if (i != Game.myTank) {
                int uX = Game.tank_positions[Game.myTank][0] / Game.SIZE;
                int uY = Game.tank_positions[Game.myTank][1] / Game.SIZE;
                int uFace = Game.tank_positions[Game.myTank][2];



                if (myX == uX) {
                    System.out.println("myX = " + myX + "  uX = " + uX);
                    int max;
                    int min;
                    if (myY > uY) {
                        max = myY;
                        min = uY;
                    } else {
                        max = uY;
                        min = myY;
                    }
                    for (int j = min + 1; j < max; j++) {
                        if (Game.map[myX][min] != 0 || Game.map[myX][min] != 4) {
                            hasSite = true;
                            return hasSite;
                        }
                    }
                }
                if (myY == uY) {
                    System.out.println("myY = " + myY + "  uY = " + uY);
                    int max;
                    int min;
                    if (myX > uX) {
                        max = myX;
                        min = uX;
                    } else {
                        max = uX;
                        min = myX;
                    }
                    for (int j = min + 1; j < max; j++) {
                        if (Game.map[myY][min] != 0 || Game.map[myY][min] != 4) {
                            hasSite = true;
                            return hasSite;
                        }
                    }
                }
            }
        }
        return hasSite;
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

        int mode = 2;// 1 = normal , 2 = attacking ,  3 = life seeking
        int method = 1;// 1 = normal go for coins, and lives
        int minDis = Integer.MAX_VALUE;
        int destination = 10;

        if (mode == 1) {
            minDis = Integer.MAX_VALUE;
            destination = 10;

            ABC:
            for (int i = 0; i < Game.coins.size(); i++) {
                int place = (Game.coins.get(i).getX() / Game.SIZE) * Game.mapLenght + (Game.coins.get(i).getY() / Game.SIZE);
                int remaining_life = (Game.coins.get(i).getTime());


                if (distance[place] < minDis) {
                    minDis = distance[place];
                    destination = place;
                }
            }
        } else if (mode == 3) {
            minDis = Integer.MAX_VALUE;
            destination = 10;

            ABC:
            for (int i = 0; i < Game.lives.size(); i++) {
                int place = (Game.lives.get(i).getX() / Game.SIZE) * Game.mapLenght + (Game.lives.get(i).getY() / Game.SIZE);
                int remaining_life = (Game.lives.get(i).getTime());

                if (distance[place] < minDis) {
                    minDis = distance[place];
                    destination = place;
                }
            }
        } else if (mode == 2) {
            minDis = Integer.MAX_VALUE;
            destination = 10;

            ABC:
            for (int i = 0; i < Game.tank_positions.length; i++) {
                if (i != Game.myTank) {
                    int place = (Game.tank_positions[i][0] / Game.SIZE) * Game.mapLenght + (Game.tank_positions[i][1] / Game.SIZE);
                    //int remaining_life = (Game.tank_positions[i][4]);

                    if (distance[place] < minDis) {
                        minDis = distance[place];
                        destination = place;
                    }
                }
            }
        }
        /////////////////////////////////////////////////////////////


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




        if (method == 1) {
            if (target_cell == myPlace + Game.mapLenght) {
                move = "RIGHT#";
            } else if (target_cell == myPlace - Game.mapLenght) {
                move = "LEFT#";
            } else if (target_cell == myPlace + 1) {
                move = "DOWN#";
            } else if (target_cell == myPlace - 1) {
                move = "UP#";
            }
            if (checkLineOfSite()) {
                move = "SHOOT#";
            }
        } else if (method == 2) {
            if (target_cell == myPlace + Game.mapLenght) {
                move = "RIGHT#";
                if (Game.map[(myPlace + 10) / Game.mapLenght][(myPlace + 10) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            } else if (target_cell == myPlace - Game.mapLenght) {
                move = "LEFT#";
                if (Game.map[(myPlace - 10) / Game.mapLenght][(myPlace - 10) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            } else if (target_cell == myPlace + 1) {
                move = "DOWN#";
                if (Game.map[(myPlace + 1) / Game.mapLenght][(myPlace + 1) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            } else if (target_cell == myPlace - 1) {
                move = "UP#";
                if (Game.map[(myPlace - 1) / Game.mapLenght][(myPlace - 1) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            }
        } else if (method == 3) {
            if (target_cell == myPlace + Game.mapLenght) {
                move = "RIGHT#";
                if (Game.map[(myPlace + 10) / Game.mapLenght][(myPlace + 10) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            } else if (target_cell == myPlace - Game.mapLenght) {
                move = "LEFT#";
                if (Game.map[(myPlace - 10) / Game.mapLenght][(myPlace - 10) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            } else if (target_cell == myPlace + 1) {
                move = "DOWN#";
                if (Game.map[(myPlace + 1) / Game.mapLenght][(myPlace + 1) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            } else if (target_cell == myPlace - 1) {
                move = "UP#";
                if (Game.map[(myPlace - 1) / Game.mapLenght][(myPlace - 1) % Game.mapLenght] == 4) {
                    move = "SHOOT#";
                }
            }
        } else if (method == 4) {
        }
        return move;
    }
}
