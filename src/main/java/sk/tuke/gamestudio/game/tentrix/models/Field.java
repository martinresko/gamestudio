package sk.tuke.gamestudio.game.tentrix.models;

import lombok.Getter;
import lombok.Setter;
import sk.tuke.gamestudio.game.tentrix.enums.GameState;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class Field {

   private GameState state = GameState.PLAYING;

   private final int rowCount;
   private final int colCount;
   private int shapeCount;

   private final Tile[][] tiles;
   private List<Shape> shapes = new LinkedList<>();

    public Field(int rowCount, int colCount, int shapeCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.shapeCount = shapeCount;

        tiles = new Tile[rowCount][colCount];

        generateShapes();
        initializeTiles();
    }

    private void generateShapes() {
        shapes.add(Shape.create());
        shapes.add(Shape.create());
        shapes.add(Shape.create());
    }

    private void initializeTiles() {
        for (int column = 0; column < colCount; column++) {
            for (int row = 0; row < rowCount; row++) {
                Tile tile = getTile(column, row);
                if (tile == null) {
                    tiles[column][row] = new Tile(TileState.EMPTY);
                }
            }
        }
    }

    public Tile getTile(int column, int row) {
        return tiles[column][row];
    }

}
