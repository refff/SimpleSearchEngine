package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AnySearch extends Search{
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

        return result;
    }
}
