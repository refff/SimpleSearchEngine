package search;

import java.util.*;

public class NoneSearch extends Search{

    @Override
    public HashSet<String> search(Map<String, List<Integer>> invertedIndex, ArrayList<String> input, String query) {
        HashSet<String> result = new HashSet<>();
        String [] searchQuery = query.split(" ");

        for (String word : searchQuery){
            word = word.toLowerCase();
            List<Integer> idList = invertedIndex.get(word.toLowerCase());

            if (idList != null) {
                for (Integer val : idList) {
                    result.add(input.get(val));
                }
            }
        }

        input.removeAll(result);

        return new HashSet<>(input);
    }
}
