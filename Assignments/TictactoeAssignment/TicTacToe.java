package TictactoeAssignment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static final int GRID_COLUMNS = 3;
    public static final int GRID_ROWS = 3;
    public static final String X_SYMBOL = "  X  "; // spaces for formatting
    public static final String O_SYMBOL = "  O  ";
    public static final String PLAYER1_NAME = "Player1";
    public static final String PLAYER2_NAME = "Player2";
    public static final String PLAYER1_COLOUR = "yellow";
    public static final String PLAYER2_COLOUR = "red";

    public static String addColourToString(String returnString, String colour) {
        final String RESET = "\u001B[0m"; // ansi escape codes for colours
        final String[] COLOURS = { "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m" };

        switch (colour.toLowerCase()) {
            case "red":
                return COLOURS[0] + returnString + RESET;
            case "green":
                return COLOURS[1] + returnString + RESET;
            case "yellow":
                return COLOURS[2] + returnString + RESET;
            case "blue":
                return COLOURS[3] + returnString + RESET;
            case "rainbow":
                StringBuilder rainbowString = new StringBuilder();
                for (int i = 0; i < returnString.length(); i++) {
                    rainbowString.append(COLOURS[i % COLOURS.length]) // mod to cycle through colors
                            .append(returnString.charAt(i));
                }
                return rainbowString.append(RESET).toString();
            default:
                return returnString;
        }
    }

    public static void displayGrid(String[][] grid) {
        String seperator = "\n----------------";
        System.out.println("\nThe current grid is:\n" + seperator);
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLUMNS; j++) {
                if (grid[i][j] == null) { // add placeholder if null
                    System.out.print(addColourToString("  #  ", "blue"));
                } else if (grid[i][j].equals(X_SYMBOL)) { // x symbol
                    System.out.print(addColourToString(grid[i][j], "yellow"));
                } else { // add O symbol red
                    System.out.print(addColourToString(grid[i][j], "red"));
                }
            }
            System.out.println(seperator);
        }
        System.out.println(); // newline
    }

    public static String switchPlayer(String currentPlayer) {
        return currentPlayer.equals(PLAYER1_NAME) ? PLAYER2_NAME : PLAYER1_NAME;
    }

    public static String addColourToName(String plyrName) {
        return plyrName.equals(PLAYER1_NAME) ? addColourToString(plyrName, PLAYER1_COLOUR)
                : addColourToString(plyrName, PLAYER2_COLOUR);
    }

    public static boolean addCharacter(String currentPlayer, String[][] grid, int[] choice) {
        // returns a true or false value depending on if it successfuly added character
        try {
            if (grid[choice[0]][choice[1]] == null) {
                // add "X" or "O" depending on the current player
                grid[choice[0]][choice[1]] = currentPlayer.equals(PLAYER1_NAME) ? X_SYMBOL : O_SYMBOL;
                return true;
            } else {
                System.out.printf("\nInvalid input, there is %s's symbol at this spot!\n",
                        addColourToName(grid[choice[0]][choice[1]].equals(X_SYMBOL) ? PLAYER1_NAME : PLAYER2_NAME));
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice input, out of range of the grid!");
            return false;
        }
    }

    public static boolean checkLine(String a, String b, String c, String symbol) {
        return a != null && b != null && c != null && a.equals(symbol) && b.equals(symbol) && c.equals(symbol);
    }

    public static boolean checkWin(String[][] grid, String currentPlayer) {
        String symbol = currentPlayer.equals(PLAYER1_NAME) ? X_SYMBOL : O_SYMBOL;

        for (int i = 0; i < GRID_ROWS; i++) {
            if (checkLine(grid[i][0], grid[i][1], grid[i][2], symbol) // Row win
                    || checkLine(grid[0][i], grid[1][i], grid[2][i], symbol)) { // Column win
                return true;
            }
        }
        return checkLine(grid[0][0], grid[1][1], grid[2][2], symbol) // Main diagonal
                || checkLine(grid[0][2], grid[1][1], grid[2][0], symbol); // Other diagonal
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
        int[] choices = new int[2];
        while (true) {
            try {
                // make it by 1 instead of 0 index
                System.out.printf("%s, enter row index (1-%d): ", addColourToName(currentPlayer), GRID_ROWS);
                int row = scanner.nextInt() - 1;
                System.out.printf("%s, enter column index (1-%d): ", addColourToName(currentPlayer), GRID_COLUMNS);
                int column = scanner.nextInt() - 1;

                if (row >= 0 && row < GRID_ROWS && column >= 0 && column < GRID_COLUMNS) {
                    choices[0] = row; // minus 1 index
                    choices[1] = column;
                    return choices;
                }

                System.out.println("Invalid input! Enter numbers within range!");
            } catch (InputMismatchException e) {
                System.out.println("You can only input valid integers!");
                scanner.nextLine(); // consume invalid input
            }
        }
    }

    public static void printInfo() {
        String plyr1 = addColourToString(PLAYER1_NAME, PLAYER1_COLOUR);
        String plyr2 = addColourToString(PLAYER2_NAME, PLAYER2_COLOUR);
        String ticTacToeString = addColourToString("tictactoe", "rainbow");
        System.out.println(
                addColourToString("\nWelcome to a new game of " + ticTacToeString + "!\n",
                        "green"));
        System.out.printf("This %s game cycles between %s (%s) and %s (%s).\n",
                ticTacToeString,
                plyr1, addColourToString("X", PLAYER1_COLOUR),
                plyr2, addColourToString("O", PLAYER2_COLOUR));
        System.out.println("Board numbering:\n1 2 3\n2\n3\n");
        System.out.println(addColourToString("'#' represents an unused spot.", "blue"));
    }

    public static void printResult(String[][] grid, String currentPlayer) {
        if (checkWin(grid, currentPlayer)) {
            System.out.println(
                    addColourToString(String.format("Hooray! The winner is: %s", addColourToName(currentPlayer)),
                            "green"));
        } else {
            System.out.println(addColourToString("All spots are filled with no winner! It's a tie!", "red"));
        }
    }

    public static void main(String[] args) {
        String[][] grid = new String[GRID_ROWS][GRID_COLUMNS];
        String currentPlayer = PLAYER1_NAME;
        Scanner scanner = new Scanner(System.in);
        printInfo();

        while (true) { // main loop
            displayGrid(grid);
            if (addCharacter(currentPlayer, grid, getUserInput(currentPlayer, scanner, grid))
                    && (checkWin(grid, currentPlayer) || checkTie(grid))) {
                displayGrid(grid);
                printResult(grid, currentPlayer);
                break;
            }
            currentPlayer = switchPlayer(currentPlayer);
        }

        scanner.close();
    }
}