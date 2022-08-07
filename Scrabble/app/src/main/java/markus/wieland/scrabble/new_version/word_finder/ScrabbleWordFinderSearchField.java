package markus.wieland.scrabble.new_version.word_finder;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Direction;
import markus.wieland.scrabble.old_versiob.validation.Characters;
import markus.wieland.scrabble.old_versiob.validation.ValidWord;
import markus.wieland.scrabble.old_versiob.validation.WordValidator;

@Getter
public class ScrabbleWordFinderSearchField extends ScrabbleWordFinderField {

    private final List<String> rightPatterns;
    private final List<String> downPatterns;

    boolean reachedFromDown;
    boolean reachedFromRight;

    boolean directlyAttached;

    private final List<ValidWord> validWords;

    public ScrabbleWordFinderSearchField(Coordinate coordinate, boolean directlyAttached) {
        super(coordinate);
        rightPatterns = new ArrayList<>();
        downPatterns = new ArrayList<>();
        this.validWords = new ArrayList<>();
        this.directlyAttached = directlyAttached;
    }

    public void setReachedFromDown(boolean reachedFromDown) {
        this.reachedFromDown = reachedFromDown;
    }

    public void setReachedFromRight(boolean reachedFromRight) {
        this.reachedFromRight = reachedFromRight;
    }

    public void setDiscoveredFrom(Direction direction) {
        switch (direction) {
            case UP:
                setReachedFromDown(true);
                break;
            case LEFT:
                setReachedFromRight(true);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String getPatterns(Direction direction) {
        switch (direction) {
            case DOWN:
                return "(" + String.join(")|(", downPatterns) + ")";
            case RIGHT:
                return "(" + String.join(")|(", rightPatterns) + ")";
            default:
                throw new IllegalArgumentException();
        }
    }

    public void add(Direction direction, String pattern) {
        switch (direction) {
            case DOWN:
                this.downPatterns.add(pattern);
                break;
            case RIGHT:
                this.rightPatterns.add(pattern);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void search(Activity activity, Characters characters) {
        validWords.clear();

        if (isReachedFromRight()) {
            validWords.addAll(WordValidator.getPossibleWords(activity, getPatterns(Direction.RIGHT), characters));
        }

        if (isReachedFromDown()) {
            validWords.addAll(WordValidator.getPossibleWords(activity, getPatterns(Direction.DOWN), characters));
        }

    }

}
