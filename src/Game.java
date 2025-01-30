import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private boolean turn = true;
    private Cell[][] field = new Cell[3][3];
    private boolean isRunning = true;
    private GameStatus gameStatus;
    private final Scanner scanner = new Scanner(System.in);

    public Game() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(field[i], Cell.VOID);
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void start() {
        while (isRunning) {
            gameStatus = checkField();
            if (gameStatus == GameStatus.DRAW || gameStatus == GameStatus.WIN) {
                break;
            }

            printTurn();
            printField();

            makeMove();

            changeTurn();

        }
        if (gameStatus == GameStatus.DRAW) {

        } else if (gameStatus == GameStatus.WIN) {

        }
    }

    public void kill() {
        isRunning = false;
    }

    private void changeTurn() {
        turn = !turn;
    }

    private GameStatus checkField() {
        return GameStatus.CONTINUE;
    }

    private void printTurn() {
        System.out.print("Turn: ");
        if (turn) {
            System.out.print(Cell.X);
        } else {
            System.out.print(Cell.O);
        }
        System.out.println();
    }

    private void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }

    private void printField() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(" " + field[x][y]);
            }
            System.out.println();
        }
    }

    private int charToInt(char c) {
        if (c == '1') return 0;
        if (c == '2') return 1;
        if (c == '3') return 2;
        throw new IllegalArgumentException();
    }

    private int[] read() {
        String input;
        int[] out = new int[2];
        while (true) {
            String line = scanner.nextLine().strip();
            if (line.matches("[123][123]")) {
                out[0] = charToInt(line.charAt(0));
                out[1] = charToInt(line.charAt(1));
                break;
            }
            System.out.println("Invalid input!");
        }
        return out;
    }

    private void makeMove() {
        int[] cords = read();
        if (turn) {
            field[cords[1]][cords[0]] = Cell.X;
        } else {
            field[cords[1]][cords[0]] = Cell.O;
        }
    }

}
