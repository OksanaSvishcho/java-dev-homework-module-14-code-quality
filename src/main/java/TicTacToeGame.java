import java.util.Scanner;

public class TicTacToeGame {
    private static final int BOARD_SIZE = 9;
    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = createBoard();
        boolean gameInProgress = true;

        while (gameInProgress) {
            displayBoard(board);
            makePlayerMove(board, scanner);
            if (checkWinner(board, PLAYER_SYMBOL)) {
                displayBoard(board);
                System.out.println("You won the game!");
                break;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            makeComputerMove(board);
            if (checkWinner(board, COMPUTER_SYMBOL)) {
                displayBoard(board);
                System.out.println("You lost the game!");
                gameInProgress = false;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("It's a draw!");
                gameInProgress = false;
            }
        }
    }

    private static char[] createBoard() {
        char[] board = new char[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = Character.forDigit(i + 1, 10);
        }
        return board;
    }

    private static void displayBoard(char[] board) {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void makePlayerMove(char[] board, Scanner scanner) {
        int input;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            try {
                input = scanner.nextInt();
                if (input >= 1 && input <= BOARD_SIZE && board[input - 1] != PLAYER_SYMBOL && board[input - 1] != COMPUTER_SYMBOL) {
                    board[input - 1] = PLAYER_SYMBOL;
                    break;
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input from scanner
            }
        }
    }

    private static void makeComputerMove(char[] board) {
        int rand;
        while (true) {
            rand = (int) (Math.random() * BOARD_SIZE);
            if (board[rand] != PLAYER_SYMBOL && board[rand] != COMPUTER_SYMBOL) {
                board[rand] = COMPUTER_SYMBOL;
                break;
            }
        }
    }

    private static boolean checkWinner(char[] board, char symbol) {
        return (board[0] == symbol && board[1] == symbol && board[2] == symbol) ||
                (board[3] == symbol && board[4] == symbol && board[5] == symbol) ||
                (board[6] == symbol && board[7] == symbol && board[8] == symbol) ||
                (board[0] == symbol && board[3] == symbol && board[6] == symbol) ||
                (board[1] == symbol && board[4] == symbol && board[7] == symbol) ||
                (board[2] == symbol && board[5] == symbol && board[8] == symbol) ||
                (board[0] == symbol && board[4] == symbol && board[8] == symbol) ||
                (board[2] == symbol && board[4] == symbol && board[6] == symbol);
    }

    private static boolean isBoardFull(char[] board) {
        for (char cell : board) {
            if (cell != PLAYER_SYMBOL && cell != COMPUTER_SYMBOL) {
                return false;
            }
        }
        return true;
    }
}
