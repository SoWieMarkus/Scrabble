package markus.wieland.scrabble.new_version;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.new_version.board.BoardMatrix;

public class Dictionary extends ArrayList<Word> {

    public List<Word> getNewWords(BoardMatrix boardMatrix) {
        List<Word> extractedWords = boardMatrix.extractWords();
        List<Word> newWords = new ArrayList<>();
        for (Word newWord : extractedWords) {
            boolean found = false;
            for (Word word : this) {
                if (word.getId().equals(newWord.getId())) {
                    found = true;
                    break;
                }
            }
            if (found) continue;
            newWords.add(newWord);
        }
        return newWords;
    }

    public void save(List<Word> newWords) {
        for (Word word : newWords) {
            boolean alreadyExists = false;
            for (Word existingWord : this) {
                if (!word.getId().equals(existingWord.getId())) continue;
                alreadyExists = true;
                break;
            }
            if (alreadyExists) throw new IllegalStateException("This word shouldn't exist!");
            this.add(word);
        }
    }

}
