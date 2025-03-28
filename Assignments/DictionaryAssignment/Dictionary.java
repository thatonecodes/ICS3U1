package DictionaryAssignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
    public static HashSet<String> readDictionary(String fileName) {
        BufferedReader inputStream = null;
        String line = "";
        HashSet<String> dictionary = new HashSet<>();
        try {
            inputStream = new BufferedReader(new FileReader(fileName));

            while ((line = inputStream.readLine()) != null) {
                dictionary.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        } catch (IOException e) {
            System.out.println("Error reading file!");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the file!");
            }
        }
        return dictionary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // make filepath interchangeable with different operating systems
        String fileName = Path.of("Assignments", "DictionaryAssignment", "dictionary.txt").toString();
        HashSet<String> dictionary = readDictionary(fileName);

        System.out.println("\nPlease input an english sentence: ");
        String[] words = scanner.nextLine().split(" ");

        int count = 1;
        for (String word : words) {
            System.out.println((dictionary.contains(word.toLowerCase())) ? count + ". " + word + "<valid>"
                    : count + ". " + word + "<invalid>");
            count++;
        }
        scanner.close();
    }
}
