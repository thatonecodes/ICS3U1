package TictactoeAssignment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static final int GRID_COLUMNS = 3;
    public static final int GRID_ROWS = 3;

    public static void displayGrid(String[][] grid) {
        System.out.println("The current grid is:\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] == null ? "#" : grid[i][j]);
            }
            System.out.println(); // newline
        }
        System.out.println();
    }

    public static String switchPlayer(String currentPlayer) {
        return currentPlayer.equals("Player1") ? "Player2" : "Player1";
    }

    public static boolean addCharacter(String currentPlayer, String[][] grid, int[] choice) {
        if (grid[choice[0]][choice[1]] == null) {
            // add "X" or "O" depending on the current player
            if (currentPlayer.equals("Player1")) {
                grid[choice[0]][choice[1]] = "X";
            } else {
                grid[choice[0]][choice[1]] = "O";
            }
            return true;
        } else {
            System.out.printf("\nInvalid input, there is %s's symbol at this spot!\n\n",
                    grid[choice[0]][choice[1]].equals("X") ? "Player1" : "Player2");
            return false;
        }
    }

    public static boolean checkWin(String[][] grid, String currentPlayer) {
        String symbol = currentPlayer.equals("Player1") ? "X" : "O";

        for (int i = 0; i < GRID_ROWS; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                return true; // row win
            }
            if (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol) {
                return true; // column win
            }
        }
        if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) {
            return true; // diagonal win
        }
        if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) {
            return true; // diagonal win
        }

        return false;
    }

    public static boolean checkTie(String[][] grid) {
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLUMNS; j++) {
                if (grid[i][j] == null) {
                    return false; // only tie when no empty spots left 
                }
            }
        }
        return true;
    }

    public static int[] getUserInput(String currentPlayer, Scanner scanner, String[][] grid) {
        int[] choices = new int[2]; // the array stores the column and the row (2 options)

        System.out.printf("%s, please enter a row index (0-%d): ", currentPlayer, grid.length - 1);
        try {
            int row = scanner.nextInt();
            if (row < 0 || row >= grid.length) {
                System.out.printf("Input must be a positive integer less than %d!\n", grid.length);
                return getUserInput(currentPlayer, scanner, grid);
            }
            choices[0] = row;

            System.out.printf("%s, please enter a column index (0-%d): ", currentPlayer, grid[row].length - 1);
            int column = scanner.nextInt();
            if (column < 0 || column >= grid[row].length) {
                System.out.printf("Input must be a positive integer less than %d!\n", grid[row].length);
                return getUserInput(currentPlayer, scanner, grid);
            }
            choices[1] = column;

        } catch (InputMismatchException e) {
            System.out.println("You can only input valid integers!");
            scanner.next(); // consume the invalid input
            return getUserInput(currentPlayer, scanner, grid);
        }

        return choices;
    }

    public static void main(String[] args) {
        String[][] grid = new String[GRID_COLUMNS][GRID_ROWS];
        String currentPlayer = "Player1";
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to a new game of tictactoe!\n");
        System.out.println("The game will cycle players, starting from player1 to player2, then back to player1, etc.");
        System.out.println("\nPlayer 1 will be 'X' and player 2 is 'O'.");
        String boardNumbers = "0 1 2\n" + "1\n" + "2\n";
        System.out.println("\nThe board numbering will be as such:");
        System.out.println(boardNumbers);
        System.out.println("\nThe '#' symbol represents an unused spot.\n");
        while (true) {
            displayGrid(grid);
            int[] choice = getUserInput(currentPlayer, scanner, grid);
            if (addCharacter(currentPlayer, grid, choice)) {
                if (checkWin(grid, currentPlayer)) {
                    System.out.printf("Hooray! %s has won the game!%n", currentPlayer);
                    break;
                } else if (checkTie(grid)) {
                    System.out.println("All spots are filled with no winner! It's a tie!");
                    break;
                }
                currentPlayer = switchPlayer(currentPlayer);
            }
        }
        scanner.close();
    }
}