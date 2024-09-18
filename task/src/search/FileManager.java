package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileManager {
    private File file;

    public FileManager(String path) {
        this.file = new File(path);
    }

    public ArrayList<String> read() {
        ArrayList<String> input = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                input.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    public Map<String, List<Integer>> invertedIndex() {
        Map<String, List<Integer>> invertedIndex = new HashMap<>();
        ArrayList<String> input = read();

        int lineNum = 0;
        for (String line : input) {

            List<String> token = Arrays.asList(line.toLowerCase().split(" "));
            Set<String> values = new HashSet<>();
            values.addAll(token);

            for (String val : values) {
                List<Integer> lines;

                if (invertedIndex.containsKey(val)) {
                    lines = invertedIndex.get(val);
                } else {
                    lines = new ArrayList<>();
                }

                if (token.contains(val)) {
                    lines.add(lineNum);
                }
                invertedIndex.put(val, lines);
            }

            lineNum++;

        }

        return invertedIndex;
    }
}
