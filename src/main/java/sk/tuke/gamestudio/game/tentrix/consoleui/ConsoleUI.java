package sk.tuke.gamestudio.game.tentrix.consoleui;

import lombok.AllArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.GameState;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;
import sk.tuke.gamestudio.game.tentrix.models.Field;
import sk.tuke.gamestudio.game.tentrix.models.Shape;
import sk.tuke.gamestudio.game.tentrix.models.Tile;
import sk.tuke.gamestudio.game.tentrix.service.GameEngineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ConsoleUI {

    public static final String GAME_NAME = "tentrix";

    private final Field field;

    private final GameEngineService gameEngineService;

    public void play() {
        do {
            printField();
            printShapeOptions();

            Scanner readinput = new Scanner(System.in);

            System.out.println("Vložte X-ovú súradnicu:");
            int xCoordinate = readinput.nextInt(10);
            System.out.println("Vložte Y-lónovú súradnicu:");
            int yCoordinate = readinput.nextInt(10);

            System.out.println("Vyberte si jednu z možností");
            int chosenShape = readinput.nextInt(3);
            processInput(xCoordinate, yCoordinate, field.getShapes().get(chosenShape));

            clearFullLines();
            //TODO: skontroluj, ci hrac neprehral
            //checkForGameOver();
        } while (field.getState() == GameState.PLAYING);

        printField();

        if (field.getState() == GameState.FAILED) {
            System.out.println("Game failed!");
        } else {
            System.out.println("Game solved!");
        }
    }

    private void clearFullLines() {
        List<Tile> tilesInColumn = new ArrayList<>();
        for(int columnNumber = 0; columnNumber < field.getColCount(); columnNumber++) {
            if(gameEngineService.isColumnFull(columnNumber, tilesInColumn)) {
                tilesInColumn.forEach(tile -> tile.setState(TileState.EMPTY));
            }
            tilesInColumn.clear();
        }

        List<Tile> tilesInRow = new ArrayList<>();
        for(int rowNumber = 0; rowNumber < field.getRowCount(); rowNumber++) {
            if(gameEngineService.isRowFull(rowNumber, tilesInRow)) {
                tilesInRow.forEach(tile -> tile.setState(TileState.EMPTY));
            }
            tilesInRow.clear();
        }
    }

    private void printField() {
        for (int row = field.getRowCount()-1; row >= 0 ; row--)
        {
            for (int column = 0; column <= field.getColCount()-1 ; column++)
            {
                Tile tile = field.getTile(column,row);

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

    private void processInput(int x, int y, Shape shape) {
        int xCoordinate = x-1;
        int yCoordinate = y-1;
        List<Tile> pickedTiles;
        int[][] coordinates = shape.provideShapeCoordinates();

        try {
            pickedTiles = this.gameEngineService.retrieveTiles(xCoordinate, yCoordinate, coordinates[0]);
        } catch (Exception e) {
            System.out.println("Nevojde sa ti to tam");
            field.setState(GameState.FAILED);
            return;
        }

        if (gameEngineService.SpaceAvailabilityCheck(pickedTiles)) {
            System.out.println("Nevojde sa ti to tam");
            field.setState(GameState.FAILED);
            return;
        }

        pickedTiles.forEach(tile -> tile.setState(TileState.FULL));
        //field.setState(GameState.SOLVED);
    }

/*    private void checkForGameOver() {
    }*/
}
