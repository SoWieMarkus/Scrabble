package markus.wieland.scrabble.board.word_managment.word_finder;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import markus.wieland.scrabble.helper.Axis;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
public class ValidLetters {

    private Set<Character> validLettersHorizontal;
    private Set<Character> validLettersVertical;

    public ValidLetters(){
        this.validLettersHorizontal = null;
        this.validLettersVertical = null;
    }

    public void add(Axis axis, char letter) {
        switch (axis) {
            case VERTICAL:
                if (validLettersVertical == null) validLettersVertical = new HashSet<>();
                validLettersVertical.add(letter);
                break;
            case HORIZONTAL:
                if (validLettersHorizontal == null) validLettersHorizontal = new HashSet<>();
                validLettersHorizontal.add(letter);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    public Set<Character> get(Axis axis) {
        switch (axis) {
            case VERTICAL:
               return validLettersVertical;
            case HORIZONTAL:
                return validLettersHorizontal;
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
