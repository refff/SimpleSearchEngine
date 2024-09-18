package search;

import java.util.*;

public class SearchEngine {
    private final Scanner scanner = new Scanner(System.in);
    FileManager fileManager;
    Search search;


    public void start(String path) {
        fileManager = new FileManager(path);
        menu();
    }

    private void menu() {
        int option;
        ArrayList<String> data = fileManager.read();

        do {
            System.out.println("""
                    === Menu ===
                    1. Find a person
                    2. Print all people
                    0. Exit""");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> findPerson(data);
                case 2 -> printAll(data);
                case 0 -> {}
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        } while (option != 0);


        System.out.println("\nBye!");
    }

    private void findPerson(ArrayList<String> data) {
        HashSet<String> result = null;

        switch (matchingStrategy()) {
            case "ANY" -> result = new AnySearch().search(fileManager.invertedIndex(), data, searchWord());
            case "ALL" -> result = new AllSearch().search(fileManager.invertedIndex(), data, searchWord());
            case "NONE" -> result = new NoneSearch().search(fileManager.invertedIndex(), data, searchWord());
        }

        //HashSet<String> result = search(fileManager.invertedIndex(), data, searchWord());
        //HashSet<String> result = new AnySearch().search(fileManager.invertedIndex(), data, searchWord());
        //result = new AllSearch().search(fileManager.invertedIndex(), data, searchWord());

        if (result.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(result.size() + " persons found:");
            System.out.println(String.join("\n", result));
        }
        System.out.println();
    }

    private String matchingStrategy() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        return scanner.nextLine();
    }

    private String searchWord() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        return scanner.nextLine();
    }

    private HashSet<String> search(Map<String, List<Integer>> invertedIndex, ArrayList<String> input, String word) {
        HashSet<String> result = new HashSet<>();

        for (Map.Entry<String, List<Integer>> entry : invertedIndex.entrySet()) {
            if (entry.getKey().toLowerCase().matches(word.toLowerCase())) {
                List<Integer> list = entry.getValue();
                for (Integer val : list) {
                    result.add(input.get(val));
                }
            }
        }

        return result;
    }

    private void printAll(ArrayList<String> input) {
        System.out.println("\n=== List of people ===");
        for(String person:input) System.out.println(person);
        System.out.println();
    }
}
