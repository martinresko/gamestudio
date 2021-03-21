package sk.tuke.gamestudio.game.tentrix.consoleui;

import sk.tuke.gamestudio.game.tentrix.Tile;
import sk.tuke.gamestudio.game.tentrix.core.Field;
import sk.tuke.gamestudio.game.tentrix.core.Shape;
import sk.tuke.gamestudio.game.tentrix.enums.GameState;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleUI {

    public static final String GAME_NAME = "tentrix";

    private final Field field;

    public ConsoleUI(Field field) {
        this.field = field;
    }

    Random random = new Random();


    public void play() {
        do {
            printField();
            printShapeOptions();

            int first = random.nextInt(9);
            int second = random.nextInt(9);
            System.out.println("First: " + first + " second: " + second);
            processInput(first, second, field.getShapes().get(0));
        } while (field.getState() == GameState.PLAYING);

        printField();

        if (field.getState() == GameState.FAILED) {
            System.out.println("Game failed!");
        } else {
            System.out.println("Game solved!");
        }
    }

    private void printField() {
        for (int row = field.getRowCount()-1; row >= 0 ; row--)
        {
            for (int column = 0; column <= field.getColCount()-1 ; column++)
            {
                Tile tile = field.getTile(row,column);

                if (tile.getState() == TileState.EMPTY) {
                    System.out.print(" - ");
                }
                else if(tile.getState() == TileState.FULL){
                    System.out.print(" X ");
                } else {
                    throw new RuntimeException("nedefinovany stav");
                }
            }
            System.out.println();
        }

    }

    private void printShapeOptions() {
        System.out.println("Moznost A je:");
        System.out.println(field.getShapes().get(0).getPattern());

        System.out.println();
        System.out.println("Moznost B je:");
        System.out.println(field.getShapes().get(1).getPattern());

        System.out.println();
        System.out.println("Moznost C je:");
        System.out.println(field.getShapes().get(2).getPattern());
    }

    private void processInput(int x, int y, Shape tempName) {
        int xCoordinate = --x;
        int yCoordinate = --y;
        List<Tile> pickedTiles;
        int[][] coordinates = tempName.getCoordinates();

        try {
            pickedTiles = retrieveTiles(xCoordinate, yCoordinate, coordinates[0]);
        } catch (Exception e) {
            System.out.println("Nevojde sa ti to tam");
            field.setState(GameState.FAILED);
            return;
        }

        if (SpaceAvailabilityCheck(pickedTiles)) {
            System.out.println("Nevojde sa ti to tam");
            field.setState(GameState.FAILED);
            return;
        }

        pickedTiles.forEach(tile -> tile.setState(TileState.FULL));
        //field.setState(GameState.SOLVED);
    }

    private boolean SpaceAvailabilityCheck(List<Tile> pickedTiles) {
        return pickedTiles.stream().anyMatch(tile -> tile.getState() == TileState.FULL);
    }

    private List<Tile> retrieveTiles(int xCoordinate, int yCoordinate, int[] coordinates) {
        List<Tile> pickedTiles = new ArrayList<>();

        pickedTiles.add(field.getTile(coordinates[0]+xCoordinate, coordinates[1]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[2]+xCoordinate, coordinates[3]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[4]+xCoordinate, coordinates[5]+yCoordinate));
        pickedTiles.add(field.getTile(coordinates[6]+xCoordinate, coordinates[7]+yCoordinate));
        return pickedTiles;
    }

}
