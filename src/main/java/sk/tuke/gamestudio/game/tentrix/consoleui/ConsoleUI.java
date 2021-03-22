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
            //TODO: prerobit user input na shape z int na char a potom v userInput triede spravit mapovanie z charu na int
            processInput(userInput.getXCoordinate(), userInput.getYCoordinate(), field.getShapes().get(userInput.getShape()));
            clearFullLines();
            System.out.println("input: " + userInput);

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
