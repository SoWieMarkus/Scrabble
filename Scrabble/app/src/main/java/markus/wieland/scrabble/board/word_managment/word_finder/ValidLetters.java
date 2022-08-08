package markus.wieland.scrabble.board.word_managment.word_finder;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import markus.wieland.scrabble.helper.Axis;

@Getter
public class ValidLetters {

    private final Set<Character> validLettersHorizontal;
    private final Set<Character> validLettersVertical;

    public ValidLetters(){
        this.validLettersHorizontal = new HashSet<>();
        this.validLettersVertical = new HashSet<>();
    }

    public void add(Axis axis, char letter) {
        switch (axis) {
            case VERTICAL:
                validLettersVertical.add(letter);
                break;
            case HORIZONTAL:
                validLettersHorizontal.add(letter);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    public Set<Character> getAllCharacters(){
        Set<Character> characters = new HashSet<>();
        characters.addAll(validLettersHorizontal);
        characters.addAll(validLettersVertical);
        return characters;
    }

    public boolean contains(Axis axis, char letter) {
        switch (axis) {
            case VERTICAL:
                return validLettersVertical.contains(letter);
            case HORIZONTAL:
                return validLettersHorizontal.contains(letter);
            default:
                throw new IllegalStateException();
        }
    }

}
