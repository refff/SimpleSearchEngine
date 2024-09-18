package search;

public class Main {
    public static void main(String[] args) {
        String path = args[1];
        //String path = "/Users/fedor/IdeaProjects/Simple Search Engine (Java)/Simple Search Engine (Java)/task/src/search/names.txt";
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.start(path);
    }
}
