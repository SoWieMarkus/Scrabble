package markus.wieland.scrabble.board.word_managment.word_finder;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import markus.wieland.scrabble.helper.Axis;

@Getter
public class ValidLetters {

    private Set<Character> validLettersHorizontal;
    private Set<Character> validLettersVertical;

    public void add(Axis axis, char letter) {
        switch (axis) {
            case VERTICAL:
                if (validLettersVertical == null) validLettersVertical = new HashSet<>();
                validLettersVertical.add(letter);
                break;
            case HORIZONTAL:
                if (validLettersHorizontal == null) validLettersHorizontal = new HashSet<>();
                validLettersVertical.add(letter);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    public boolean lettersExists(Axis axis) {
        return false;
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
