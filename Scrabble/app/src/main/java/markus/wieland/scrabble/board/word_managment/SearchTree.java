package markus.wieland.scrabble.board.word_managment;

import android.app.Activity;

import java.util.List;

import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.board.word_managment.solver.words.prefix.Prefix;
import markus.wieland.scrabble.helper.Axis;
import markus.wieland.scrabble.helper.FileReader;
import markus.wieland.scrabble.helper.Tree;

public class SearchTree extends Tree {

    private static final SearchTree instance = new SearchTree();

    public static SearchTree getInstance(Activity activity) {
        if (!instance.isInitialized()) {
            initialize(activity);
        }
        return instance;
    }

    public static void initialize(Activity activity) {
        FileReader fileReader = new FileReader(activity);
        instance.addAll(fileReader.read("words.txt").split("\r\n"));
    }

    private SearchTree() {
        super(new SearchTreeNode());
    }

    public void addAll(String[] words) {
        for (String word : words) {
            add(word);
        }
    }

    public boolean isInitialized() {
        return !getRoot().getChildren().isEmpty();
    }

    @Override
    public SearchTreeNode getRoot() {
        return (SearchTreeNode) super.getRoot();
    }

    public void add(String word) {
        SearchTreeNode currentNode = getRoot();
        for (char letter : word.toCharArray()) {
            if (!currentNode.contains(letter)) {
                currentNode.add(letter, new SearchTreeNode());
            }
            currentNode = currentNode.get(letter);
            assert currentNode != null;
        }
        currentNode.setTerminal(true);
    }

    public SearchTreeNode search(String word) {
        return search(getRoot(), word);
    }

    public SearchTreeNode search(SearchTreeNode node, String word) {
        SearchTreeNode currentNode = node;
        for (char letter : word.toCharArray()) {
            if (!currentNode.contains(letter)) {
                return null;
            }
            currentNode = currentNode.get(letter);
        }
        return currentNode;
    }

    public boolean isValidPrefix(String prefix) {
        return search(prefix) != null;
    }

    public boolean isValidWord(String word) {
        return isValidWord(getRoot(), word);
    }

    public boolean isValidWord(SearchTreeNode searchTreeNode, String word) {
        SearchTreeNode finalNode = search(searchTreeNode, word);
        return finalNode != null && finalNode.isTerminal();
    }


}
