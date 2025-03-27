package DictionaryAssignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
    public static HashSet<String> readDictionary(String fileName) {
        BufferedReader inputStream = null;
        String line = "";
        HashSet<String> dictionary = new HashSet<String>();
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
        String fileName = ".\\Assignments\\DictionaryAssignment\\dictionary.txt"; 
        HashSet<String> dictionary = readDictionary(fileName);

        System.out.println("Please input an english sentence: ");
        String sentence = scanner.nextLine();
        String[] words = sentence.split(" ");
        int count = 1;
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                System.out.println(count + ". " + word + " <valid>");
            } else { 
                System.out.println(count + ". " + word + " <invalid>");
            }
            count++;
        }
        scanner.close();
    }
}
