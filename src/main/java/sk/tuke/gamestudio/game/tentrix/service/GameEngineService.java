package sk.tuke.gamestudio.game.tentrix.service;

import sk.tuke.gamestudio.game.tentrix.enums.TileState;
import sk.tuke.gamestudio.game.tentrix.models.Field;
import sk.tuke.gamestudio.game.tentrix.models.Tile;

import java.util.ArrayList;
import java.util.List;

public class GameEngineService {

    public boolean SpaceAvailabilityCheck(List<Tile> pickedTiles) {
        return pickedTiles.stream().anyMatch(tile -> tile.getState() == TileState.FULL);
    }

    public List<Tile> retrieveTiles(int xCoordinate, int yCoordinate, int[] coordinates, Field field) {
        List<Tile> pickedTiles = new ArrayList<>();

        pickedTiles.add(field.getTile(coordinates[0]+xCoordinate, coordinates[1]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[2]+xCoordinate, coordinates[3]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[4]+xCoordinate, coordinates[5]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[6]+xCoordinate, coordinates[7]+yCoordinate));
        return pickedTiles;
    }

}
