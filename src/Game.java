import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private boolean turn = true;
    private Cell[][] field = new Cell[3][3];
    private boolean isRunning;
    private GameStatus gameStatus;
    private Cell winner = Cell.VOID;
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
        isRunning = true;
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
            printField();
            System.out.println("Draw!");
        } else if (gameStatus == GameStatus.WIN) {
            printField();
            System.out.println(winner + " wins!");
        }
    }

    public void kill() {
        isRunning = false;
    }

    private void changeTurn() {
        turn = !turn;
    }

    private GameStatus checkField() {

        if (isWinDiagonal()) {
            winner = field[1][1];
            return GameStatus.WIN;
        }

        for (int i = 0; i < 3; i++) {
            if (isWinRow(i)) {
                winner = field[i][0];
                return GameStatus.WIN;
            }
            if (isWinColumn(i)) {
                winner = field[0][i];
                return GameStatus.WIN;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == Cell.VOID) {
                    return GameStatus.CONTINUE;
                }
            }
        }

        return GameStatus.DRAW;
    }

    private boolean isWinRow(int n) {
        return (field[n][0] == field[n][1]) && (field[n][1] == field[n][2]) && field[n][0] != Cell.VOID;
    }

    private boolean isWinColumn(int n) {
        return (field[0][n] == field[1][n]) && (field[1][n] == field[2][n]) && field[0][n] != Cell.VOID;
    }

    private boolean isWinDiagonal() {
        if (field[1][1] == Cell.VOID) return false;
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) return true;
        if (field[0][2] == field [1][1] && field[1][1] == field [2][0]) return true;
        return false;
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
                System.out.print(" " + field[y][x]);
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
            field[cords[0]][cords[1]] = Cell.X;
        } else {
            field[cords[0]][cords[1]] = Cell.O;
        }
    }

}
