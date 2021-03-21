package sk.tuke.gamestudio.game.tentrix.consoleui;

import sk.tuke.gamestudio.game.tentrix.Tile;
import sk.tuke.gamestudio.game.tentrix.core.Field;
import sk.tuke.gamestudio.game.tentrix.enums.GameState;
import sk.tuke.gamestudio.game.tentrix.enums.TileState;

public class ConsoleUI {

    public static final String GAME_NAME = "tentrix";
    //private static final Pattern COMMAND_PATTERN = Pattern.compile("([OM])([A-I])([1-9])");

    private final Field field;

    //private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(Field field) {
        this.field = field;
    }

    public void play() {
        do {
            printField();
            processInput();
        } while (field.getState() == GameState.PLAYING);

        //printField();

        if (field.getState() == GameState.FAILED) {
            System.out.println("Game failed!");
        } else {
            System.out.println("Game solved!");
        }
    }

    private void printField() {
        for (int row = 0; row < field.getRowCount(); row++)
        {
            for (int column = 0; column < field.getColCount(); column++)
            {
                Tile tile = field.getTile(row,column);

                if (tile.getState() == TileState.EMPTY) {
                    System.out.print("-");
                }
                else if(tile.getState() == TileState.FULL){
                    System.out.println("X");
                } else {
                    throw new RuntimeException("nedefinovany stav");
                }
            }
            System.out.println();
            field.setState(GameState.SOLVED);
        }
    }

    private void processInput() {
    }
}
