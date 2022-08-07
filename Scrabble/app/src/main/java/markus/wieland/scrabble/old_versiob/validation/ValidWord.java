package markus.wieland.scrabble.old_versiob.validation;

import lombok.Getter;

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
