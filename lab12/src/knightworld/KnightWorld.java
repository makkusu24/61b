package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    private int holeSize;

    public KnightWorld(int width, int height, int holeSize) {
        this.holeSize = holeSize;
        TETile[][] tiles = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Place a hole in every other square, offset by one in odd rows
                if ((x % (holeSize * 2) == 0 && y % (holeSize * 2) == 0)
                        || (x % (holeSize * 2) == holeSize && y % (holeSize * 2) == holeSize)) {
                    tiles[x][y] = Tileset.NOTHING;
                } else {
                    tiles[x][y] = Tileset.FLOWER;
                }
            }
        }
        this.tiles = tiles;
    }

    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 2;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
