package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;


    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    public static void tileinit (TETile[][] world) {
        //TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** adds a hexagon of side length s to a given position in the world*/
    public static void addHexagon(int Xpos, int Ypos, int s, TETile[][] world) {
        tileprint(world, Hexagon(Xpos,Ypos,s));
    }
    /** return a hexagon of ture position in a 2d map*/
    public static int[][] Hexagon(int Xpos, int Ypos, int s) {
        int[][] map = new int[WIDTH][HEIGHT];
        //map[Xpos][Ypos] = 1;
        int Yptr = Ypos;
        int count = 0;

        for (int j = Yptr; j < Ypos + s; j += 1) {
            for (int i = Xpos - count; i < Xpos + s + count; i += 1) {
                if (i < 0 || i >= WIDTH || j < 0 || j >= HEIGHT) {
                    continue;
                }
                map[i][j] = 1;
            }
            count += 1;
        }
        Yptr += count;
        count -= 1;
        for (int j = Yptr; j < Ypos + 2 * s; j += 1) {
            for (int i = Xpos - count; i < Xpos + s + count; i += 1) {
                if (i < 0 || i >= WIDTH || j < 0 || j >= HEIGHT) {
                    continue;
                }
                map[i][j] = 1;
            }
            count += -1;
        }
        return map;
    }

    public static void addBunchOfHexagon(int cXpos, int cYpos, int s, int loop, TETile[][] world) {
        //int[][][] loopstuff = new int[WIDTH][HEIGHT][loop];
        //for (int i = 0; i < loop; i += 1){}
        if (loop >= 2){
            loop -= 1;
            addBunchOfHexagon(cXpos - 2 * s + 1, cYpos - s, s, loop, world);
            addBunchOfHexagon(cXpos - 2 * s + 1, cYpos + s, s, loop, world);
            addBunchOfHexagon(cXpos + 2 * s - 1, cYpos - s, s, loop, world);
            addBunchOfHexagon(cXpos + 2 * s - 1, cYpos + s, s, loop, world);
            addBunchOfHexagon(cXpos, cYpos - 2 * s, s, loop, world);
            addBunchOfHexagon(cXpos, cYpos + 2 * s, s, loop, world);
        }
        if(loop == 1) {
            addHexagon(cXpos, cYpos, s, world);
        }

    }

    public static void tileprint (TETile[][] world, int[][] map) {
        //TETile[][] world = new TETile[WIDTH][HEIGHT];
        TETile randomtile = Tile();
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                if (map[x][y] == 1) {
                    //world[x][y] = Tile();
                    world[x][y] = randomtile;
                }
            }
        }
    }


    /**
     * Fills the given 2D array of tiles with tiles.
     * @param tiles
     */
    public static void fillWithTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tile();
            }
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile Tile() {
        int tileNum = RANDOM.nextInt(9);
        //int tileNum = 1;
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.FLOOR;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.MOUNTAIN;
            case 6: return Tileset.SAND;
            case 7: return Tileset.WATER;
            case 8: return Tileset.LOCKED_DOOR;
            default: return Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];

        tileinit(world);

        //fillWithTiles(world);
        addBunchOfHexagon(45, 45, 5, 4, world);

        ter.renderFrame(world);
        Random r = new Random(1000);
    }

}
