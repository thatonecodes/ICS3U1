package utils;

public class CircleTerminal {

    public static void draw() {
        int radius = 10;
        double diameter = 2 * radius;
        char fillChar = '*';
        for (int y = 0; y <= diameter; y++) {
            for (int x = 0; x <= diameter; x++) {
                // Convert (x, y) to a coordinate system with the center at (radius, radius)
                double distance = Math.sqrt(
                    Math.pow(x - radius, 2) + Math.pow(y - radius, 2)
                );

                if (Math.abs(distance - radius) < 1.5) {
                    System.out.print(fillChar + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
