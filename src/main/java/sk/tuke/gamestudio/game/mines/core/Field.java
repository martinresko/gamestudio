package sk.tuke.gamestudio.game.mines.core;

import java.util.Random;

public class Field {
    private GameState state = GameState.PLAYING;

    private final int rowCount;

    private final int columnCount;

    private final int mineCount;

    private final Tile[][] tiles;

    private int numberOfOpenTiles;

    private long startMillis;

    public Field(int rowCount, int columnCount, int mineCount) {
        if (rowCount * columnCount <= mineCount)
            throw new IllegalArgumentException("Illegal setting");

        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
    }

    private void generate() {
        generateMines();
        fillWithClues();
        startMillis = System.currentTimeMillis();
    }

    private void generateMines() {
        Random random = new Random();

        for (int storedMines = 0; storedMines < mineCount; ) {
            int row = random.nextInt(rowCount);
            int column = random.nextInt(columnCount);
            if (tiles[row][column] == null) {
                tiles[row][column] = new Mine();
                storedMines++;
            }
        }
    }

    private void fillWithClues() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                Tile tile = getTile(row, column);
                if (tile == null) {
                    tiles[row][column] = new Clue(getNeighbourMinesCount(row, column));
                }
            }
        }
    }

    private int getNeighbourMinesCount(int row, int column) {
        int count = 0;
        if (row > 0) {
            if (column > 0 && tiles[row - 1][column - 1] instanceof Mine)
                count++;
            if (tiles[row - 1][column] instanceof Mine)
                count++;
            if (column < columnCount - 1 && tiles[row - 1][column + 1] instanceof Mine)
                count++;
        }

        if (column > 0 && tiles[row][column - 1] instanceof Mine)
            count++;
        if (column < columnCount - 1 && tiles[row][column + 1] instanceof Mine)
            count++;

        if (row < rowCount - 1) {
            if (column > 0 && tiles[row + 1][column - 1] instanceof Mine)
                count++;
            if (tiles[row + 1][column] instanceof Mine)
                count++;
            if (column < columnCount - 1 && tiles[row + 1][column + 1] instanceof Mine)
                count++;
        }
        return count;
    }

    public GameState getState() {
        return state;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    public void openTile(int row, int column) {
        Tile tile = tiles[row][column];
        if (tile.getState() == TileState.CLOSED) {
            tile.setState(TileState.OPEN);
            numberOfOpenTiles++;

            if (tile instanceof Mine)
                state = GameState.FAILED;
            else {
                if (((Clue) tile).getValue() == 0)
                    openNeighbourhTiles(row, column);

                if (isSolved())
                    state = GameState.SOLVED;
            }
        }
    }

    private void openNeighbourhTiles(int row, int column) {
        if (row > 0) {
            if (column > 0)
                openTile(row - 1, column - 1);
            openTile(row - 1, column);
            if (column < columnCount - 1)
                openTile(row - 1, column + 1);
        }

        if (column > 0)
            openTile(row, column - 1);
        if (column < columnCount - 1)
            openTile(row, column + 1);

        if (row < rowCount - 1) {
            if (column > 0)
                openTile(row + 1, column - 1);
            openTile(row + 1, column);
            if (column < columnCount - 1)
                openTile(row + 1, column + 1);
        }
    }

    private boolean isSolved() {
        return rowCount * columnCount - mineCount == numberOfOpenTiles;
    }

    public void markTile(int row, int column) {
        Tile tile = tiles[row][column];
        if (tile.getState() == TileState.CLOSED) {
            tile.setState(TileState.MARKED);
        } else if (tile.getState() == TileState.MARKED) {
            tile.setState(TileState.CLOSED);
        }
    }

    public int getScore() {
        return rowCount * columnCount * 3 - (int) (System.currentTimeMillis() - startMillis) / 1000;
    }
}
