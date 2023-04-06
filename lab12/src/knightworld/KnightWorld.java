package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private final TETile[][] tiles;

    public KnightWorld(int width, int height, int holeSize) {
        TETile[][] tiles = new TETile[width][height];
        this.tiles = tiles;
        int denom = 5 * holeSize;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((x % denom == 0 && y % denom == holeSize) || (x % denom == holeSize && y % denom == 4 * holeSize) ||
                        (x % denom == 2 * holeSize && y % denom == 2 * holeSize) ||
                        (x % denom == 3 * holeSize && y % denom == 0)
                        || (x % denom == 4 * holeSize && y % denom == 3 * holeSize)) {
                    //createSquare(x, y, holeSize, Tileset.NOTHING, this.tiles);
                    for (int axis1 = x; axis1 <= x + holeSize - 1; axis1++) {
                        for (int axis2 = y; axis2 <= y + holeSize - 1; axis2++) {
                            if (axis1 > width - 1 || axis2 > height - 1) {
                                break;
                            }
                            tiles[axis1][axis2] = Tileset.NOTHING;
                        }
                    }
                } else {
                    //createSquare(x, y, holeSize, Tileset.FLOWER, this.tiles);
                    for (int j = 0; j < width; j++) {
                        for (int i = 0; i < height; i++) {
                            if (tiles[j][i] != Tileset.NOTHING) {
                                tiles[j][i] = Tileset.FLOWER;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
    public void createSquare(int x, int y, int holeSize, TETile tile, TETile[][] tiles) {
        for (int axis1 = x; axis1 < x + holeSize - 1; x++) {
            for (int axis2 = y; axis2 < y + holeSize - 1; y++) {
                tiles[axis1][axis2] = tile;
            }
        }
    }*/

    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 3;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
