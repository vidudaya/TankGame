package slicktest;

/**
 *
 * @author HitMan
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {

    private TiledMap grassMap;
    private Animation sprite, up, down, left, right;
    private Animation stones[];
    private Animation bricks[];
    private Animation waters[];
    private Animation tanks[][];
    private float x = 34f * 4, y = 34f * 4;
    // public static final int SIZE = 50;
    public static final int SIZE = 30;
    private boolean[][] blocked;
    private static Server server;
    public static int[][] bricks_coordinates;
    private int[][] newBrickSystem;// with damage level
    private int[][] stone_coordinates;
    private int[][] water_coordinates;
    public static int myTank;
    public static int[][] tank_positions;
    public static ArrayList<Coins> coins = new ArrayList<>();
    public static ArrayList<Coins> killCoins = new ArrayList<>();
    public static ArrayList<Life> lives = new ArrayList<>();
//    public static final int mapLenght = 10;
//    public static final int mapWidth = 10;
    public static final int mapLenght = 20;
    public static final int mapWidth = 20;
    public static int[][] map;// bricks =1,stones = 2, water = 3 , tank =4 , coin =5, life = 6;
    public static boolean edges[][];

    public Game() {
        super("Tank game");
    }

    public static void main(String[] arguments) {
        try {
            new Play().start();
            server = new Server();
            AppGameContainer app = new AppGameContainer(new Game());
            //18 is the extra cells for scoreboard 
            app.setDisplayMode(SIZE * (18 + mapLenght), SIZE * mapWidth, false);
            map = new int[mapLenght][mapWidth];
            //app.setTargetFrameRate(15);
            app.setAlwaysRender(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {

        //grassMap = new TiledMap("Images/map50_10x10.tmx");//can change the size
        //grassMap = new TiledMap("Images/map30_20x20.tmx");//can change the size
        grassMap = new TiledMap("Images/map30_20x20_scoreBoard.tmx");//can change the size
        Image[] movementUp = {new Image("Images/plane.png"), new Image("Images/plane.png")};
        Image[] brick = {new Image("Images/brick.jpg"), new Image("Images/brick.jpg")};
        Image[] stone = {new Image("Images/rock.jpg"), new Image("Images/rock.jpg")};
        Image[] water = {new Image("Images/water.jpg"), new Image("Images/water.jpg")};
        Image[] coin = {new Image("Images/coin.png"), new Image("Images/coin.png")};
        Image[] health = {new Image("Images/health.png"), new Image("Images/health.png")};
        //Image fff = new Image("Images/water.jpg");

        Image[][][] tanks_aims = new Image[5][4][2];
        //0=up , 1=right , 2=down , 3=left
        Image[] tank1_up = {new Image("Images/tanks/1U.png"), new Image("Images/tanks/1U.png")};//dark green
        Image[] tank1_right = {new Image("Images/tanks/1R.png"), new Image("Images/tanks/1R.png")};
        Image[] tank1_down = {new Image("Images/tanks/1D.png"), new Image("Images/tanks/1D.png")};
        Image[] tank1_left = {new Image("Images/tanks/1L.png"), new Image("Images/tanks/1L.png")};
        tanks_aims[0][0] = tank1_up;
        tanks_aims[0][1] = tank1_right;
        tanks_aims[0][2] = tank1_down;
        tanks_aims[0][3] = tank1_left;
        Image[] tank2_up = {new Image("Images/tanks/2U.png"), new Image("Images/tanks/2U.png")};//purple
        Image[] tank2_right = {new Image("Images/tanks/2R.png"), new Image("Images/tanks/2R.png")};
        Image[] tank2_down = {new Image("Images/tanks/2D.png"), new Image("Images/tanks/2D.png")};
        Image[] tank2_left = {new Image("Images/tanks/2L.png"), new Image("Images/tanks/2L.png")};
        tanks_aims[1][0] = tank2_up;
        tanks_aims[1][1] = tank2_right;
        tanks_aims[1][2] = tank2_down;
        tanks_aims[1][3] = tank2_left;
        Image[] tank3_up = {new Image("Images/tanks/3U.png"), new Image("Images/tanks/3U.png")};//orange
        Image[] tank3_right = {new Image("Images/tanks/3R.png"), new Image("Images/tanks/3R.png")};
        Image[] tank3_down = {new Image("Images/tanks/3D.png"), new Image("Images/tanks/3D.png")};
        Image[] tank3_left = {new Image("Images/tanks/3L.png"), new Image("Images/tanks/3L.png")};
        tanks_aims[2][0] = tank3_up;
        tanks_aims[2][1] = tank3_right;
        tanks_aims[2][2] = tank3_down;
        tanks_aims[2][3] = tank3_left;
        Image[] tank4_up = {new Image("Images/tanks/4U.png"), new Image("Images/tanks/4U.png")};//green
        Image[] tank4_right = {new Image("Images/tanks/4R.png"), new Image("Images/tanks/4R.png")};
        Image[] tank4_down = {new Image("Images/tanks/4D.png"), new Image("Images/tanks/4D.png")};
        Image[] tank4_left = {new Image("Images/tanks/4L.png"), new Image("Images/tanks/4L.png")};
        tanks_aims[3][0] = tank4_up;
        tanks_aims[3][1] = tank4_right;
        tanks_aims[3][2] = tank4_down;
        tanks_aims[3][3] = tank4_left;
        Image[] tank5_up = {new Image("Images/tanks/5U.png"), new Image("Images/tanks/5U.png")};//blue
        Image[] tank5_right = {new Image("Images/tanks/5R.png"), new Image("Images/tanks/5R.png")};
        Image[] tank5_down = {new Image("Images/tanks/5D.png"), new Image("Images/tanks/5D.png")};
        Image[] tank5_left = {new Image("Images/tanks/5L.png"), new Image("Images/tanks/5L.png")};
        tanks_aims[4][0] = tank5_up;
        tanks_aims[4][1] = tank5_right;
        tanks_aims[4][2] = tank5_down;
        tanks_aims[4][3] = tank5_left;

//
//
//        tanks_aims[0][0] = new Image("Images/tanks/1U.jpg");
//        tanks_aims[0][1] = new Image("Images/tanks/1R.jpg");
//        tanks_aims[0][2] = new Image("Images/tanks/1D.jpg");
//        tanks_aims[0][3] = new Image("Images/tanks/1L.jpg");
//
//        tanks_aims[1][0] = new Image("Images/tanks/2U.jpg");
//        tanks_aims[1][1] = new Image("Images/tanks/2R.jpg");
//        tanks_aims[1][2] = new Image("Images/tanks/2D.jpg");
//        tanks_aims[1][3] = new Image("Images/tanks/2L.jpg");
//
//        tanks_aims[2][0] = new Image("Images/tanks/3U.jpg");
//        tanks_aims[2][1] = new Image("Images/tanks/3R.jpg");
//        tanks_aims[2][2] = new Image("Images/tanks/3D.jpg");
//        tanks_aims[2][3] = new Image("Images/tanks/3L.jpg");
//
//        tanks_aims[3][0] = new Image("Images/tanks/4U.jpg");
//        tanks_aims[3][1] = new Image("Images/tanks/4R.jpg");
//        tanks_aims[3][2] = new Image("Images/tanks/4D.jpg");
//        tanks_aims[3][3] = new Image("Images/tanks/4L.jpg");
//
//        tanks_aims[4][0] = new Image("Images/tanks/5U.jpg");
//        tanks_aims[4][1] = new Image("Images/tanks/5R.jpg");
//        tanks_aims[4][2] = new Image("Images/tanks/5D.jpg");
//        tanks_aims[4][3] = new Image("Images/tanks/5L.jpg");



        int[] duration = {30, 30};

        try {
            String f = server.readSENDMessage();
            System.out.println("msg = " + f);
            String Terrain[] = f.split(":");
            MakeMap makemap = new MakeMap();

            myTank = Integer.parseInt(Terrain[1].charAt(1) + "");

            bricks_coordinates = makemap.markBricks(Terrain[2], SIZE);
            for (int i = 0; i < bricks_coordinates.length; i++) {
                System.out.println(bricks_coordinates[i][0] + " " + bricks_coordinates[i][1]);
            }
            bricks = new Animation[bricks_coordinates.length];
            for (int i = 0; i < bricks_coordinates.length; i++) {
                bricks[i] = new Animation(brick, duration, false);
            }
            stone_coordinates = makemap.markStones(Terrain[3], SIZE);
            for (int i = 0; i < stone_coordinates.length; i++) {
                System.out.println(stone_coordinates[i][0] + " " + stone_coordinates[i][1]);
            }
            stones = new Animation[stone_coordinates.length];
            for (int i = 0; i < stone_coordinates.length; i++) {
                stones[i] = new Animation(stone, duration, false);
            }
            water_coordinates = makemap.markWater(Terrain[4], SIZE);
            for (int i = 0; i < water_coordinates.length; i++) {
                System.out.println(water_coordinates[i][0] + " " + water_coordinates[i][1]);
            }
            waters = new Animation[water_coordinates.length];
            for (int i = 0; i < water_coordinates.length; i++) {
                waters[i] = new Animation(water, duration, true);
            }

            //0=up , 1=right , 2=down , 3=left
            f = server.readSENDMessage();
            tanks = new Animation[(f.split(":").length - 1)][4];
            for (int i = 0; i < tanks.length; i++) {
                for (int j = 0; j < 4; j++) {
                    tanks[i][j] = new Animation(tanks_aims[i][j], duration, false);
                }
            }
            tank_positions = makemap.makeTanksPositions(f, SIZE);



        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("init error : " + ex);
        }

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        HandleTanks htanks = new HandleTanks();
        HandleCoinsAndHealth hcoinshealth = new HandleCoinsAndHealth();
        MakeMap makeMap = new MakeMap();
        MoveAndShoot moveAndShoot = new MoveAndShoot();
        RunWithNoGUI mapPrint = new RunWithNoGUI();

        Play play = new Play();

        hcoinshealth.updateCoin(delta);
        // hcoinshealth.updateKillCoin(delta);
        hcoinshealth.detectCoinCollisions();
        hcoinshealth.updateLife(delta);
        hcoinshealth.detectLifeCollisions();
        hcoinshealth.detectKillCoinCollisions();
        try {
            String message = server.readSENDMessage();
            System.out.println("msg : " + message);
            if (message.length() > 6 && message.charAt(0) == 'G') {
//                new Play().makeMove("UP#");
                htanks.setNewTankPositions(message, SIZE);
                makeMap.updateBrickStatus(message, SIZE, mapWidth, mapLenght);
                play.makeMove(moveAndShoot.getTheNextMove());
                mapPrint.printMap(map);
            } else if (message.charAt(0) == 'C' && message.charAt(1) == ':') {
                hcoinshealth.handleCoins(message, SIZE);
                // play.makeMove(moveAndShoot.getTheNextMove());
                //hcoinshealth.updateCoin();
            } else if (message.charAt(0) == 'L' && message.charAt(1) == ':') {
                hcoinshealth.handleLives(message, SIZE);
                // play.makeMove(moveAndShoot.getTheNextMove());
            } else if (message.charAt(0) == 'C') {
                play.makeMove("SHOOT#");
            }

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("readSendMessage error : "+ex);
        }
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        grassMap.render(0, 0);
        for (int i = 0; i < bricks.length; i++) {
            if (bricks_coordinates[i][2] != 4) {
                bricks[i].draw(bricks_coordinates[i][0], bricks_coordinates[i][1], SIZE, SIZE);
            }
        }
        for (int i = 0; i < stones.length; i++) {
            stones[i].draw(stone_coordinates[i][0], stone_coordinates[i][1], SIZE, SIZE);
        }
        for (int i = 0; i < waters.length; i++) {
            waters[i].draw(water_coordinates[i][0], water_coordinates[i][1], SIZE, SIZE);
        }
        for (int i = 0; i < lives.size(); i++) {
            lives.get(i).getAnime().draw(lives.get(i).getX(), lives.get(i).getY(), SIZE, SIZE);
        }

        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).getAnime().draw(coins.get(i).getX(), coins.get(i).getY(), SIZE, SIZE);
        }
        for (int i = 0; i < killCoins.size(); i++) {
            killCoins.get(i).getAnime().draw(killCoins.get(i).getX(), killCoins.get(i).getY(), SIZE, SIZE);
        }
        for (int i = 0; i < tanks.length; i++) {
            if (Game.tank_positions[i][4] > 0) {
                tanks[i][tank_positions[i][2]].draw(tank_positions[i][0], tank_positions[i][1], SIZE, SIZE);
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////
        g.drawLine((mapLenght - 0.5f + 2) * SIZE, 2 * SIZE, (mapLenght + .5f + 15) * SIZE, 2 * SIZE);
        g.drawLine((mapLenght - 0.5f + 2) * SIZE, 3 * SIZE, (mapLenght + .5f + 15) * SIZE, 3 * SIZE);
        g.drawLine((mapLenght - .5f + 2) * SIZE, 2 * SIZE, (mapLenght - .5f + 2) * SIZE, (8 + .5f) * SIZE);
        g.drawLine((mapLenght + .5f + 15) * SIZE, 2 * SIZE, (mapLenght + .5f + 15) * SIZE, (8 + .5f) * SIZE);
        g.drawLine((mapLenght - 0.5f + 2) * SIZE, (8 + .5f) * SIZE, (mapLenght + .5f + 15) * SIZE, (8 + .5f) * SIZE);

        g.drawLine((mapLenght - 0.5f + 7) * SIZE, 2 * SIZE, (mapLenght - 0.5f + 7) * SIZE, (8 + .5f) * SIZE);
        g.drawLine((mapLenght - 0.5f + 10) * SIZE, 2 * SIZE, (mapLenght - 0.5f + 10) * SIZE, (8 + .5f) * SIZE);
        g.drawLine((mapLenght - 0.5f + 13) * SIZE, 2 * SIZE, (mapLenght - 0.5f + 13) * SIZE, (8 + .5f) * SIZE);


        g.drawString("Player", (mapLenght + 2) * SIZE, 2 * SIZE);
        g.drawString("Coins", (mapLenght + 7) * SIZE, 2 * SIZE);
        g.drawString("Points", (mapLenght + 10) * SIZE, 2 * SIZE);
        g.drawString("Health", (mapLenght + 13) * SIZE, 2 * SIZE);

        Color darkGreen = new Color(47, 79, 47);
        Color lightGreen = new Color(Color.green);
        Color purple = new Color(Color.pink);
        Color orange = new Color(Color.orange);
        Color blue = new Color(Color.blue);
        Color colors[] = new Color[5];
        colors[0] = darkGreen;
        colors[1] = purple;
        colors[2] = orange;
        colors[3] = lightGreen;
        colors[4] = blue;

        for (int i = 0; i < tank_positions.length; i++) {
            g.setColor(colors[i]);
            if (myTank != i) {
                g.drawString("Player " + i, (mapLenght + 2) * SIZE, (4 + i) * SIZE);
            } else {
                g.drawString("BloodSpot (" + i + ")", (mapLenght + 2) * SIZE, (4 + i) * SIZE);

            }
            g.drawString("" + tank_positions[i][5], (mapLenght + 7) * SIZE, (4 + i) * SIZE);
            g.drawString("" + tank_positions[i][6], (mapLenght + 10) * SIZE, (4 + i) * SIZE);
            g.drawString("" + tank_positions[i][4], (mapLenght + 13) * SIZE, (4 + i) * SIZE);
        }
        g.setColor(Color.white);

    }
}
