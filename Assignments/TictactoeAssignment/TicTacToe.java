package TictactoeAssignment;

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
        String separator = "\n" + "-".repeat(15);
        System.out.println("\nThe current grid is:\n" + separator);
        for (String[] row : grid) {
            for (String cell : row) {
                if (cell == null) { // add placeholder if null
                    System.out.print(addColourToString("  #  ", "blue"));
                } else if (cell.equals(X_SYMBOL)) { // x symbol
                    System.out.print(addColourToString(cell, PLAYER1_COLOUR));
                } else { // add O symbol red
                    System.out.print(addColourToString(cell, PLAYER2_COLOUR));
                }
            }
            System.out.println(separator);
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

    public static boolean verifyRange(int row, int col) {
        return row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLUMNS;
    }

    public static boolean addCharacter(String currentPlayer, String[][] grid, int[] choice) {
        // returns a true or false value depending on if it successfuly added character
        int row = choice[0];
        int col = choice[1];

        if (!verifyRange(row, col)) {
            System.out.println("Invalid choice! Out of range of the grid.");
            return false;
        }

        if (grid[row][col] != null) {
            String occupiedBy = grid[row][col].equals(X_SYMBOL) ? PLAYER1_NAME : PLAYER2_NAME;
            System.out.printf("\nInvalid input! There is already %s's symbol at this spot!%n",
                    addColourToName(occupiedBy));
            return false;
        }

        grid[row][col] = (currentPlayer.equals(PLAYER1_NAME)) ? X_SYMBOL : O_SYMBOL;
        return true;
    }

    public static boolean checkLine(String a, String b, String c) {
        return a != null && b != null && c != null && a.equals(b) && b.equals(c);
    }

    public static boolean checkWin(String[][] grid, String currentPlayer) {
        for (int i = 0; i < GRID_ROWS; i++) {
            if (checkLine(grid[i][0], grid[i][1], grid[i][2]) // Row win
                    || checkLine(grid[0][i], grid[1][i], grid[2][i])) { // Column win
                return true;
            }
        }
        return checkLine(grid[0][0], grid[1][1], grid[2][2]) // Main diagonal
                || checkLine(grid[0][2], grid[1][1], grid[2][0]); // Other diagonal
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
        while (true) {
            try {
                System.out.printf("%s, enter row and column index (1-%d row, 1-%d column): ",
                        addColourToName(currentPlayer), GRID_ROWS, GRID_COLUMNS);
                String[] input = scanner.nextLine().split(" ");
                int row = Integer.parseInt(input[0]) - 1;
                int col = Integer.parseInt(input[1]) - 1;

                if (verifyRange(row, col)) {
                    return new int[] { row, col };
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please input 2 valid integers seperated by a space!");
                continue;
            }
            System.out.println("Invalid input! Enter numbers within range!");
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
        System.out.println("\nBoard numbering:");
        System.out.println("     1       2       3");
        System.out.println("1|   #   |   #   |   #   |\n");
        System.out.println("2|   #   |   #   |   #   |\n");
        System.out.println("3|   #   |   #   | " + addColourToString(X_SYMBOL, PLAYER1_COLOUR) + " |\n");
        System.out.println("Here, the " + addColourToString("X", PLAYER1_COLOUR) + " would be " + plyr1
                + " at row 3 and column 3, (3,3).\n");
        System.out.println(
                "To play, enter the row and column index in one line, seperated by a single space. ex.(3 2)\n");
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
            if (addCharacter(currentPlayer, grid, getUserInput(currentPlayer, scanner, grid))) {
                if (checkWin(grid, currentPlayer) || checkTie(grid)) {
                    displayGrid(grid);
                    printResult(grid, currentPlayer);
                    break;
                }
                currentPlayer = switchPlayer(currentPlayer);
            }
        }

        scanner.close();
    }
}