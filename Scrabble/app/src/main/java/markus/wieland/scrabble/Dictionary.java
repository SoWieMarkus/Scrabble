package markus.wieland.scrabble;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.game.PlacedLetter;
import markus.wieland.scrabble.game.Word;
import markus.wieland.scrabble.new_version.helper.Coordinate;
import markus.wieland.scrabble.new_version.helper.Matrix;

public class Dictionary {

    private final List<Word> words;

    public Dictionary() {
        this.words = new ArrayList<>();
    }

    private List<Word> extractWords(Matrix<PlacedLetter> letterMatrix) {

        List<Word> extractedWords = new ArrayList<>();

        Word horizontal = new Word();
        Word vertical = new Word();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                Coordinate verticalCoordinate = new Coordinate(j, i);
                Coordinate horizontalCoordinate = new Coordinate(i, j);

                PlacedLetter verticalLetter = letterMatrix.get(verticalCoordinate);
                PlacedLetter horizontalLetter = letterMatrix.get(horizontalCoordinate);

                if (verticalLetter == null) {
                    if (vertical.getLength() > 1) {
                        extractedWords.add(vertical);
                    }
                    vertical = new Word();
                } else {
                    verticalLetter.setCoordinate(verticalCoordinate);
                    vertical.add(verticalLetter);
                }

                if (horizontalLetter == null) {
                    if (horizontal.getLength() > 1) {
                        extractedWords.add(horizontal);
                    }
                    horizontal = new Word();
                } else {
                    horizontal.add(horizontalLetter);
                    horizontalLetter.setCoordinate(horizontalCoordinate);
                }
            }
        }

        return extractedWords;
    }

    public List<Word> getNewWords(Matrix<PlacedLetter> letterMatrix){
        List<Word> extractedWords =extractWords(letterMatrix);
        List<Word> newWords = new ArrayList<>();
        for (Word newWord : extractedWords) {
            boolean found = false;
            for (Word word : this.words) {
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
            for (Word existingWord : words) {
                if (!word.getId().equals(existingWord.getId())) continue;
                alreadyExists = true;
                break;
            }
            if (alreadyExists) throw new IllegalStateException("This word shouldn't exist!");
            words.add(word);
        }
    }

}
