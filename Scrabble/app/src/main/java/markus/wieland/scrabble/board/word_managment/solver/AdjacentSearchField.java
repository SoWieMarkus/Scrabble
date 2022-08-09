package markus.wieland.scrabble.board.word_managment.solver;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.Field;
import markus.wieland.scrabble.board.word_managment.SearchTree;
import markus.wieland.scrabble.board.word_managment.SearchTreeNode;
import markus.wieland.scrabble.game.Inventory;
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

    private Pattern patternLeft;
    private Pattern patternUp;

    private Set<Direction> directionsDiscovered;

    private boolean horizontal;
    private boolean vertical;

    private final Set<Prefix> prefixesLeft;
    private final Set<Prefix> prefixesUp;

    public AdjacentSearchField(Field field) {
        super(field.getCoordinate());
        this.validLetters = new ValidLetters();
        this.directionsDiscovered = new HashSet<>();
        this.stepsLeft = 0;
        this.patternLeft = new Pattern();
        this.patternUp = new Pattern();
        this.prefixesLeft = new HashSet<>();
        this.prefixesUp = new HashSet<>();
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

    public void calculateValidPrefix(Inventory inventory, SearchTree searchTree) {


        if (stepsLeft > 0) {
            Set<Prefix> left = inventory.getPrefixTree().generatePrefix(stepsLeft, patternLeft);
            for (Prefix prefix : left) {
                SearchTreeNode searchTreeNode = searchTree.search(prefix.getPrefixString() + wordLeft);
                if (searchTreeNode == null) continue;
                prefix.setSearchTreeNode(searchTreeNode);
                prefix.setStartCoordinate(getCoordinate().getCoordinate(Direction.LEFT, prefix.getPrefixString().length()));
                prefix.setEndCoordinateOfWholeCurrentWord(getCoordinate().getCoordinate(Direction.RIGHT, wordRight.length()));
                prefixesLeft.add(prefix);
            }
        }

        if (stepsUp > 0) {
            Set<Prefix> up = inventory.getPrefixTree().generatePrefix(stepsUp, patternUp);
            for (Prefix prefix : up) {
                SearchTreeNode searchTreeNode = searchTree.search(prefix.getPrefixString() + wordDown);
                if (searchTreeNode == null) continue;
                prefix.setSearchTreeNode(searchTreeNode);
                prefix.setStartCoordinate(getCoordinate().getCoordinate(Direction.UP, prefix.getPrefixString().length()));
                prefix.setEndCoordinateOfWholeCurrentWord(getCoordinate().getCoordinate(Direction.DOWN, wordDown.length()));

                prefixesUp.add(prefix);
            }
        }
    }
}
