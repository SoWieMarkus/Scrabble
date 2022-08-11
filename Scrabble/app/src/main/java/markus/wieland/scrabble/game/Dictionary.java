package markus.wieland.scrabble.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dictionary {

    private final HashMap<String, Word> words;

    public Dictionary() {
        this.words = new HashMap<>();
    }

    public List<Word> filterNewWords(List<Word> allWords) {
        List<Word> newWords = new ArrayList<>();
        for (Word word : allWords) {
            if (!words.containsKey(word.getId())){
                newWords.add(word);
            }
        }
        return newWords;
    }

    public void save(List<Word> newWords) {
        for (Word word : newWords){
            words.put(word.getId(), word);
        }
    }

}
