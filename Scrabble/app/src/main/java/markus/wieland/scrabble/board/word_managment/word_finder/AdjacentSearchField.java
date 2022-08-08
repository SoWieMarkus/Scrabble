package markus.wieland.scrabble.board.word_managment.word_finder;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.Field;
import markus.wieland.scrabble.board.word_managment.SearchTree;
import markus.wieland.scrabble.board.word_managment.SearchTreeNode;
import markus.wieland.scrabble.helper.Axis;
import markus.wieland.scrabble.helper.Direction;

@Getter
@Setter
public class AdjacentSearchField extends SearchField {

    private final ValidLetters validLetters;
    private int stepsUp;
    private int stepsLeft;

    private String wordLeft;
    private String wordUp;
    private String wordRight;
    private String wordDown;

    private Set<Direction> directionsDiscovered;

    private boolean horizontal;
    private boolean vertical;

    public AdjacentSearchField(Field field) {
        super(field.getCoordinate());
        this.validLetters = new ValidLetters();
        this.directionsDiscovered = new HashSet<>();
        this.stepsLeft = 0;
        this.stepsUp = 0;
    }

    public void update(Field field) {
        setSpecialBlockType(field.getSpecialBlockType());
    }

    public void addDirectionDiscovered(Direction direction) {
        directionsDiscovered.add(direction);
        if (direction.isHorizontal()) horizontal = true;
        if (direction.isVertical()) vertical = true;
    }

    public void add(Axis axis, char letter) {
        validLetters.add(axis, letter);
    }

    public String getWord(Direction direction) {
        switch (direction) {
            case RIGHT:
                return wordRight;
            case DOWN:
                return wordDown;
            case UP:
                return wordUp;
            case LEFT:
                return wordLeft;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String getWordByAxis(Axis axis, char letter) {
        return getWord(axis.getDirectionNegative()) + letter + getWord(axis.getDirectionPositive());
    }

    public boolean canBePlaced(Axis axis, char letter) {
        if (directionsDiscovered.size() == 4)
            return validLetters.getAllCharacters().contains(letter);

        //TODO

        return validLetters.contains(axis.getOtherAxis(), letter);
    }

}
