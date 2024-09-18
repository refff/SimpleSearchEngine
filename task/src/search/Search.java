package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class Search {
    public abstract HashSet<String> search(Map<String, List<Integer>> invertedIndex, ArrayList<String> input, String word);
}
