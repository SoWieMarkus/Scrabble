package markus.wieland.scrabble.validation;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import markus.wieland.scrabble.new_version.Word;
import markus.wieland.scrabble.new_version.helper.FileReader;
import markus.wieland.scrabble.game.Inventory;
import markus.wieland.scrabble.game.Parts;
import markus.wieland.scrabble.new_version.word_finder.new_versiob.SearchTree;
import markus.wieland.scrabble.new_version.word_finder.new_versiob.SearchTreeNode;

public class WordValidator {

    private static final List<ValidWord> validWords = new ArrayList<>();
    private static String validWordsAsString;
    private static final SearchTree searchTree = new SearchTree();

    private WordValidator(){}

    public static boolean validate(Activity activity, String word) {
        if (validWords.isEmpty()) {
            initializeList(activity);
        }
        SearchTreeNode searchTreeNode = searchTree.search(word);
        return searchTreeNode != null && searchTreeNode.isTerminal();
    }

    public static void initializeList(Activity activity) {
        FileReader fileReader = new FileReader(activity);
        validWordsAsString = fileReader.read("words.txt");
        String[] legalWords = fileReader.read("words.txt").split("\r\n");
        Log.i(WordValidator.class.getSimpleName(), "Read " + legalWords.length + " words!");
        validWords.clear();
        for (String word : legalWords) {
            validWords.add(new ValidWord(word));
            searchTree.add(word);
        }
        int x = 0;
    }

    public static List<ValidWord> getPossibleWords (Activity activity, Characters characters) {
        if (validWords.isEmpty()) initializeList(activity);

        List<ValidWord> possibleWords = new ArrayList<>();
        for (ValidWord validWord : validWords) {
            if (validWord.isPossible(characters)) {
                possibleWords.add(validWord);
            }
        }
        return possibleWords;
    }

    public static List<ValidWord> getPossibleWords(Activity activity, String pattern, Characters characters) {

        long millis = System.currentTimeMillis();
        if (validWords.isEmpty()) initializeList(activity);

        List<ValidWord> possibleWords = new ArrayList<>();
        for (ValidWord validWord : validWords) {

            if (!validWord.doesMatchPattern(pattern)) continue;
            //TODO remove this
            possibleWords.add(validWord);
            /*if (validWord.isPossible(characters)) {
                possibleWords.add(validWord);
            }*/


        }
        Log.e("Take 1", (System.currentTimeMillis()-millis)+"ms");

        millis = System.currentTimeMillis();
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(pattern)
                .matcher(validWordsAsString);
        while (m.find()) {
            allMatches.add(m.group());
        }

        Log.e("Take 2", (System.currentTimeMillis()-millis)+"ms");

        return possibleWords;
    }


}
