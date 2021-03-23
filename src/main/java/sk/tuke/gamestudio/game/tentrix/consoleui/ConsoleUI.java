package sk.tuke.gamestudio.game.tentrix.consoleui;

import lombok.AllArgsConstructor;
import sk.tuke.gamestudio.game.tentrix.enums.GameState;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;
import sk.tuke.gamestudio.game.tentrix.models.Field;
import sk.tuke.gamestudio.game.tentrix.models.Shape;
import sk.tuke.gamestudio.game.tentrix.models.Tile;
import sk.tuke.gamestudio.game.tentrix.models.UserInput;
import sk.tuke.gamestudio.game.tentrix.service.GameEngineService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class ConsoleUI {

    public static final String GAME_NAME = "tentrix";

    private final Field field;

    private final GameEngineService gameEngineService;

    public void play() {
        do {
            printField();
            printShapeOptions();
            UserInput userInput = gameEngineService.gainUserInput();
            processInput(userInput.getXCoordinate(), userInput.getYCoordinate(), field.getShapes().get(userInput.getShape()));
            clearFullLines();
            removeUsedShape(userInput.getShape());

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
        gameEngineService.clearFullColumns();
        gameEngineService.clearFullRows();
    }

    private void printField() {
        for (int row = field.getRowCount()-1; row >= 0 ; row--)
        {
            for (int column = 0; column <= field.getColCount()-1 ; column++)
            {
                Tile tile = field.getTile(column,row);
                String output = tile.getState() == TileState.EMPTY ? " - " : " X ";
                System.out.print(output);
            }
            System.out.println();
        }
    }

    private void printShapeOptions() {
        AtomicInteger shapeIndex = new AtomicInteger();
        field.getShapes().forEach(shape -> {
            System.out.println("Moznost cislo " + (shapeIndex.get() + 1) + ": ");
            System.out.println(field.getShapes().get(shapeIndex.get()).getPattern()[shape.getOrientation()]);
            shapeIndex.set(shapeIndex.get() + 1);
        });
    }

    private void processInput(int x, int y, Shape shape) {
        int xCoordinate = x-1;
        int yCoordinate = y-1;
        List<Tile> pickedTiles;
        int[][] coordinates = shape.provideShapeCoordinates();

        try {
            pickedTiles = this.gameEngineService.retrieveTiles(xCoordinate, yCoordinate, coordinates[shape.getOrientation()]);
        } catch (Exception e) {
            System.out.println("Nevojde sa ti to tam lebo si na okraji.");
            field.setState(GameState.FAILED);
            return;
        }

        if (gameEngineService.SpaceAvailabilityCheck(pickedTiles)) {
            System.out.println("Nevojde sa ti to tam lebo je pole obsadene.");
            field.setState(GameState.FAILED);
            return;
        }

        pickedTiles.forEach(tile -> tile.setState(TileState.FULL));
    }

    private void removeUsedShape(int shapeIndex) {
        field.getShapes().remove(shapeIndex);
        if(field.getShapes().isEmpty()) {
            field.generateShapes();
        }
    }

/*    private void checkForGameOver() {
    }*/
}
