package sk.tuke.gamestudio.game.mines.consoleui;

import sk.tuke.gamestudio.game.mines.core.Clue;
import sk.tuke.gamestudio.game.mines.core.Field;
import sk.tuke.gamestudio.game.mines.core.GameState;
import sk.tuke.gamestudio.game.mines.core.Tile;
import sk.tuke.gamestudio.game.mines.entity.Score;
import sk.tuke.gamestudio.game.mines.service.ScoreService;
import sk.tuke.gamestudio.game.mines.service.ScoreServiceJDBC;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {
    public static final String GAME_NAME = "mines";
    private static final Pattern COMMAND_PATTERN = Pattern.compile("([OM])([A-I])([1-9])");

    private final Field field;

    private final Scanner scanner = new Scanner(System.in);

    private ScoreService scoreService = new ScoreServiceJDBC();

    public ConsoleUI(Field field) {
        this.field = field;
    }

    public void play() {
        printTopScores();

        do {
            printField();
            processInput();
        } while (field.getState() == GameState.PLAYING);

        printField();

        if (field.getState() == GameState.FAILED) {
            System.out.println("Game failed!");
        } else {
            System.out.println("Game solved!");
            scoreService.addScore(new Score("mines", System.getProperty("user.name"), field.getScore(), new Date()));
        }
    }

    private void printField() {
        System.out.print(" ");
        for (int column = 0; column < field.getColumnCount(); column++) {
            System.out.print(" ");
            System.out.print(column + 1);
        }
        System.out.println();

        for (int row = 0; row < field.getRowCount(); row++) {
            System.out.print((char) (row + 'A'));
            for (int column = 0; column < field.getColumnCount(); column++) {
                Tile tile = field.getTile(row, column);
                System.out.print(" ");
                switch (tile.getState()) {
                    case CLOSED:
                        System.out.print("-");
                        break;
                    case MARKED:
                        System.out.print("M");
                        break;
                    case OPEN:
                        if (tile instanceof Clue) {
                            System.out.print(((Clue) tile).getValue());
                        } else {
                            System.out.print("X");
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported tile state " + tile.getState());
                }
            }
            System.out.println();
        }
    }

    private void processInput() {
        System.out.print("Enter command (X - exit, OA1 - open, MB2 - mark): ");
        String line = scanner.nextLine().toUpperCase();
        if ("X".equals(line))
            System.exit(0);

        Matcher matcher = COMMAND_PATTERN.matcher(line);
        if (matcher.matches()) {
            int row = line.charAt(1) - 'A';
            int column = Integer.parseInt(matcher.group(3)) - 1;

            if (matcher.group(1).equals("O")) {
                field.openTile(row, column);
            } else if (line.startsWith("M")) {
                field.markTile(row, column);
            }
        } else {
            System.err.println("Wrong input " + line);
        }
    }

    private void printTopScores() {
        List<Score> scores = scoreService.getTopScores(GAME_NAME);
        for(Score score : scores) {
            System.out.printf("%s %d\n", score.getPlayer(), score.getPoints());
        }
    }
}
