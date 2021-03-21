package sk.tuke.gamestudio.game.tentrix;

import java.util.Random;

public class Field {
   private GameState state = GameState.PLAYING;


   private final int rowCount;

   private final int colCount;

   private final int shapeCount;


   private final Tile[][] tiles;
   private final Tile[] shapeTiles;

    public Field(int rowCount, int colCount, int shapeCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.shapeCount = shapeCount;


        tiles = new Tile[rowCount][colCount];
        shapeTiles = new Tile[shapeCount];
        generate();
    }

    private void generate() {

        generateShape();
    }

    public void generateShape() {
        Random random = new Random();
        int col = random.nextInt(rowCount);
        //int row = random.nextInt(colCount);
        //tiles [row][col] = new Shape();
    }



    public GameState getState() {
        return state;
    }

    public int getColCount() {
        return colCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getShapeCount() {
        return shapeCount;
    }

    public Tile getsTile(int col) {
        return shapeTiles[col];
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }
}
