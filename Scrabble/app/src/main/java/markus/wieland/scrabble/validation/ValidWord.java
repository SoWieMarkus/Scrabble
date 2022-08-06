package markus.wieland.scrabble.validation;

import lombok.Getter;
import markus.wieland.scrabble.game.Parts;

@Getter
public class ValidWord {

    private final String word;
    private final Characters parts;

    public ValidWord(String word) {
        this.word = word;
        this.parts = new Characters(word);
    }

    public boolean doesMatchPattern(String regex) {
        return word.matches(regex);
    }

    public boolean isPossible(Characters inventoryLetters) {
       return  parts.isPossible(inventoryLetters);
    }

}
