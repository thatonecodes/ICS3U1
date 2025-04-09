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
        HashSet<String> dict = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dict.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return dict;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // make filepath interchangeable with different operating systems
        String filePath = Path.of("Assignments", "DictionaryAssignment", "dictionary.txt").toString();
        HashSet<String> dictionary = readDictionary(filePath);

        System.out.println("\n\nPlease input an english sentence: ");
        String[] words = scanner.nextLine().trim().split("\\s+");
        scanner.close();

        for (int i = 0; i < words.length; i++) {
            System.out.println(i + 1 + ". " + words[i]
                    + (dictionary.contains(words[i].toLowerCase()) ? " <valid>" : " <invalid>"));
        }
    }
}