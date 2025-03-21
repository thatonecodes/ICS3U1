import java.io.IOException;

public class WindowsArrowKeys {
    static int row = 0, col = 0;
    static final int GRID_SIZE = 3;

    public static void main(String[] args) throws IOException {
        System.out.println("Use arrow keys to move. Press ENTER to select. Press 'q' to quit.\n");
        
        while (true) {
            printGrid();
            int key = System.in.read(); // Read input
            
            if (key == 224) { // Windows uses 224 for arrow keys
                switch (System.in.read()) {
                    case 72: row = Math.max(0, row - 1); break; // Up
                    case 80: row = Math.min(GRID_SIZE - 1, row + 1); break; // Down
                    case 77: col = Math.min(GRID_SIZE - 1, col + 1); break; // Right
                    case 75: col = Math.max(0, col - 1); break; // Left
                }
            } else if (key == 13) { // Enter key
                System.out.printf("Selected Position: (%d, %d)%n", row, col);
            } else if (key == 'q') { // Quit
                System.out.println("Exiting...");
                return;
            }
        }
    }

    private static void printGrid() {
        System.out.print("\033[H\033[2J"); // Clears terminal
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                System.out.print((r == row && c == col) ? "[X] " : "[ ] ");
            }
            System.out.println();
        }
    }
}
