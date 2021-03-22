package sk.tuke.gamestudio.game.tentrix.service;

import lombok.AllArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;
import sk.tuke.gamestudio.game.tentrix.models.Field;
import sk.tuke.gamestudio.game.tentrix.models.Tile;
import sk.tuke.gamestudio.game.tentrix.models.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class GameEngineService {

    private final Field field;

    private final Scanner readInput = new Scanner(System.in);

    public boolean SpaceAvailabilityCheck(List<Tile> pickedTiles) {
        return pickedTiles.stream().anyMatch(tile -> tile.getState() == TileState.FULL);
    }

    public List<Tile> retrieveTiles(int xCoordinate, int yCoordinate, int[] coordinates) {
        List<Tile> pickedTiles = new ArrayList<>();

        pickedTiles.add(field.getTile(coordinates[0]+xCoordinate, coordinates[1]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[2]+xCoordinate, coordinates[3]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[4]+xCoordinate, coordinates[5]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[6]+xCoordinate, coordinates[7]+yCoordinate));
        return pickedTiles;
    }

    public boolean isColumnFull(int columnNumber, List<Tile> tilesInColumn) {
        for(int rowNumber = 0; rowNumber < field.getRowCount(); rowNumber++) {
            Tile actualTile = field.getTile(columnNumber, rowNumber);
            if(actualTile.getState() == TileState.EMPTY) {
                return false;
            }
            tilesInColumn.add(actualTile);
        }
        return true;
    }

    public boolean isRowFull(int rowNumber, List<Tile> titlesInRow) {
        for(int columnNumber = 0; columnNumber < field.getColCount(); columnNumber++) {
            Tile actualTile = field.getTile(columnNumber, rowNumber);
            if(actualTile.getState() == TileState.EMPTY) {
                return false;
            }
            titlesInRow.add(actualTile);
        }
        return true;
    }

    public UserInput gainUserInput() {
        UserInput userInput = new UserInput();

        System.out.println("Vložte X-ovú súradnicu:");
        userInput.setXCoordinate(readInput.nextInt(10));
        System.out.println("Vložte Y-lónovú súradnicu:");
        userInput.setYCoordinate(readInput.nextInt(10));
        System.out.println("Vyberte si jednu z možností");
        userInput.setShape(readInput.nextInt(3));

        return userInput;
    }

    public void clearFullColumns() {
        List<Tile> tilesInColumn = new ArrayList<>();
        for(int columnNumber = 0; columnNumber < field.getColCount(); columnNumber++) {
            if(isColumnFull(columnNumber, tilesInColumn)) {
                tilesInColumn.forEach(tile -> tile.setState(TileState.EMPTY));
            }
            tilesInColumn.clear();
        }
    }

    public void clearFullRows() {
        List<Tile> tilesInRow = new ArrayList<>();
        for(int rowNumber = 0; rowNumber < field.getRowCount(); rowNumber++) {
            if(isRowFull(rowNumber, tilesInRow)) {
                tilesInRow.forEach(tile -> tile.setState(TileState.EMPTY));
            }
            tilesInRow.clear();
        }
    }

}
