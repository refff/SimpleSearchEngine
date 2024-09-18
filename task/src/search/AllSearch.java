package search;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AllSearch extends Search{

    @Override
    public HashSet<String> search(Map<String, List<Integer>> invertedIndex, ArrayList<String> input, String query) {
        HashSet<String> result = new HashSet<>();

        String[] searchWords = query.split(" ");
        HashSet<Integer> mapIndexes = new HashSet<>(invertedIndex.getOrDefault(searchWords[0].toLowerCase(), new ArrayList<>()));

        for (String word : searchWords) {
            List<Integer> wordIndexes = invertedIndex.get(word.toLowerCase());

            if (wordIndexes == null) {
                return new HashSet<>();
            }

            mapIndexes.retainAll(wordIndexes);

            if (mapIndexes.isEmpty()) {
                return new HashSet<>();
            }
        }

        for (Integer index : mapIndexes) {
            result.add(input.get(index));
        }

        return result;
    }
}
