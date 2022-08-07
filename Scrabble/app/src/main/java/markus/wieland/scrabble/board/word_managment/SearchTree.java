package markus.wieland.scrabble.board.word_managment;

import android.app.Activity;

import markus.wieland.scrabble.helper.FileReader;

public class SearchTree {

    private static final SearchTree instance = new SearchTree();

    public static SearchTree getInstance(Activity activity){
        if (!instance.isInitialized()) {
            FileReader fileReader = new FileReader(activity);
            instance.addAll(fileReader.read("words.txt").split("\r\n"));
        }
        return instance;
    }

    private final SearchTreeNode root;

    private SearchTree(){
        this.root = new SearchTreeNode();
    }

    public void addAll(String[] words) {
        for (String word : words) {
            add(word);
        }
    }

    public boolean isInitialized(){
        return !root.getChildren().isEmpty();
    }

    public void add(String word) {
        SearchTreeNode currentNode = root;
        for (char letter : word.toCharArray()) {
            if (!currentNode.getChildren().containsKey(letter)) {
                currentNode.getChildren().put(letter, new SearchTreeNode());
            }
            currentNode = currentNode.getChildren().get(letter);
            assert currentNode != null;
        }
        currentNode.setTerminal(true);
    }

}
