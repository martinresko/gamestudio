package sk.tuke.gamestudio.game.tentrix.service;

import lombok.AllArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;
import sk.tuke.gamestudio.game.tentrix.models.Field;
import sk.tuke.gamestudio.game.tentrix.models.Tile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GameEngineService {

    private final Field field;

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

}
