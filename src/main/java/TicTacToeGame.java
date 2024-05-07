import java.util.Scanner;

public class TicTacToeGame {

    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char USER_MARKER = 'X';
    private static final char COMPUTER_MARKER = 'O';

    private final char[][] board;
    private static final Scanner scanner = new Scanner(System.in);

    public TicTacToeGame() {
        board = initializeBoard();
    }

    public void playGame() {
        boolean isUserTurn = true;
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();

            if (isUserTurn) {
                userMove();
            } else {
                computerMove();
            }

            if (checkWin(USER_MARKER)) {
                printBoard();
                System.out.println("You won the game!");
                gameEnded = true;
            } else if (checkWin(COMPUTER_MARKER)) {
                printBoard();
                System.out.println("You lost the game!");
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            }

            isUserTurn = !isUserTurn;
        }
    }

    private char[][] initializeBoard() {
        char[][] board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        return board;
    }

    private void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private void userMove() {
        int row, col;
        do {
            System.out.print("Enter row and column (1-3): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));
        board[row][col] = USER_MARKER;
    }

    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != EMPTY) {
            System.out.println("Invalid input. Enter again.");
            return false;
        }
        return true;
    }

    private void computerMove() {
        int row, col;
        do {
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);
        } while (!isValidMove(row, col));
        board[row][col] = COMPUTER_MARKER;
    }

    private boolean checkWin(char marker) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == marker && board[i][1] == marker && board[i][2] == marker) {
                return true;
            }
            if (board[0][i] == marker && board[1][i] == marker && board[2][i] == marker) {
                return true;
            }
        }
        if (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) {
            return true;
        }
        return board[0][2] == marker && board[1][1] == marker && board[2][0] == marker;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
