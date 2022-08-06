package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.new_version.board.Letter;

public class SearchTree {

    private final SearchTreeNode start;
    private static final char[] allPossibleChars = new char[29];

    public SearchTree(String[] words) {
        this();
        for (String word : words) {
            add(word);
        }
    }

    private static void initializeAllPossibleChars() {
        char startChar = 'A';
        for (int i = 0; i < 26; i++) {
            allPossibleChars[i] = (char) (startChar + i);
        }
        allPossibleChars[26] = 'Ä';
        allPossibleChars[27] = 'Ö';
        allPossibleChars[28] = 'Ü';
    }

    public SearchTree() {
        this.start = new SearchTreeNode(null);
        initializeAllPossibleChars();
    }

    public boolean isValid(String word) {
        SearchTreeNode searchTreeNode = search(word);
        return searchTreeNode == null;
    }

    public boolean isValidAndTerminal(String word) {
        SearchTreeNode searchTreeNode = search(word);
        if (searchTreeNode == null) return false;
        return searchTreeNode.isTerminal();
    }

    public void add(String word) {
        SearchTreeNode currentNode = start;
        for (char letter : word.toCharArray()) {
            if (!currentNode.getChildren().containsKey(letter)) {
                currentNode.getChildren().put(letter, new SearchTreeNode(currentNode));
            }
            currentNode = currentNode.getChildren().get(letter);
            assert currentNode != null;
        }
        currentNode.setTerminal(true);
    }

    public SearchTreeNode search(String word) {
        SearchTreeNode currentNode = start;
        for (char letter : word.toCharArray()) {
            if (!currentNode.getChildren().containsKey(letter)) {
                return null;
            }
            currentNode = currentNode.getChildren().get(letter);
            assert currentNode != null;
        }
        return currentNode;
    }

    public List<SearchTreeNode> searchWithJoker(String word) {
        List<SearchTreeNode> searchWithJoker = new ArrayList<>();
        for (String replacedWord : getAllPossibleJokerCombinations(word)) {
            SearchTreeNode searchTreeNode = search(replacedWord);
            if (searchTreeNode == null || !searchTreeNode.isTerminal()) continue;
            searchWithJoker.add(search(replacedWord));
            Log.e("Test", replacedWord);
        }
        return searchWithJoker;
    }

    private List<String> getAllPossibleJokerCombinations(String word) {
        List<String> wordsWithJoker = new ArrayList<>();
        for (char letter : allPossibleChars) {
            String wordWithoutFirstJoker = word.replaceFirst("\\?", String.valueOf(letter));

            if (!wordWithoutFirstJoker.contains("?")){
                wordsWithJoker.add(wordWithoutFirstJoker);
                continue;
            }
            wordsWithJoker.addAll(getAllPossibleJokerCombinations(wordWithoutFirstJoker));
        }
        return wordsWithJoker;
    }

    public boolean isInitialized() {
        return !start.getChildren().isEmpty();
    }


}
