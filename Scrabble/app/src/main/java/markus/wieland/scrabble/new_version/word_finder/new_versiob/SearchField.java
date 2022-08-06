package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.new_version.board.Field;
import markus.wieland.scrabble.new_version.board.SpecialBlockType;
import markus.wieland.scrabble.new_version.helper.Direction;

@Getter
@Setter
public class SearchField {

    public static final String BREAK_PATTERN = "-";
    private static final char EMPTY = '.';
    private static final char ADJACENT = '_';

    private char letter;
    private SpecialBlockType specialBlockType;
    private Set<Character> validCharactersHorizontal;
    private Set<Character> validCharactersVertical;
    private final Set<Direction> directions;
    private final Set<Prefix> validPrefixHorizontal;
    private final Set<Prefix> validPrefixVertical;

    private int stepsUp;
    private int stepsLeft;

    public SearchField(Field field) {
        this.validPrefixHorizontal = new HashSet<>();
        this.validPrefixVertical = new HashSet<>();
        this.directions = new HashSet<>();
        this.validCharactersHorizontal = new HashSet<>();
        this.validCharactersVertical= new HashSet<>();
        this.letter = field.getLetter() == null ? EMPTY : field.getLetter().getValue();

        this.specialBlockType = isLetter() ? null : field.getSpecialBlock();

        if (this.letter == EMPTY && this.specialBlockType == SpecialBlockType.START) {
            this.letter = ADJACENT;
        }
        this.stepsUp = 0;
        this.stepsLeft = 0;
    }

    public boolean setAdjacent(Direction direction) {
        if (isLetter()) return false;
        this.letter = ADJACENT;
        this.directions.add(direction);
        return true;
    }

    public boolean isEmpty() {
        return letter == EMPTY;
    }

    public boolean isLetter() {
        return letter != EMPTY && letter != ADJACENT;
    }

    public boolean isAdjacent() {
        return letter == ADJACENT;
    }

    public char getCurrentCharOfField() {
        if (isLetter()) return letter;
        return getCurrentCharOfField();
    }

    public boolean containsHorizontal() {
        for (Direction direction : directions) {
            if (direction.isHorizontal()) return true;
        }
        return false;
    }

    public boolean containsVertical() {
        for (Direction direction : directions) {
            if (direction.isVertical()) return true;
        }
        return false;
    }

    public boolean contains(Direction direction) {
        return directions.contains(direction.getOppositeDirection());
    }

    public String getPattern() {
        if (validCharactersHorizontal.isEmpty() && containsHorizontal()) return BREAK_PATTERN;
        if (validCharactersVertical.isEmpty() && containsVertical()) return BREAK_PATTERN;
        Set<Character> newList = new HashSet<>();
        newList.addAll(validCharactersVertical);
        newList.addAll(validCharactersHorizontal);
        if (newList.isEmpty()) return ".";
        StringBuilder pattern = new StringBuilder();
        for (Character character : newList) {
            pattern.append(character).append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1);
        return "(" + pattern + ")";
    }


}
