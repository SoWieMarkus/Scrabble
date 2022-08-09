package markus.wieland.scrabble.board.word_managment.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import markus.wieland.scrabble.board.Board;
import markus.wieland.scrabble.board.Field;
import markus.wieland.scrabble.board.word_managment.SearchTree;
import markus.wieland.scrabble.board.word_managment.SearchTreeNode;
import markus.wieland.scrabble.game.Inventory;
import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.helper.Axis;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Direction;

public class SearchBoard extends Board {

    private final List<Coordinate> coordinatesOfAdjacentFields;

    public SearchBoard(Board board) {
        super(board.getDimension());

        this.coordinatesOfAdjacentFields = new ArrayList<>();

        for (Field field : board) {
            get(field.getCoordinate()).update(field);
        }

        for (Field field : this) {
            if (field.getLetter() == null) continue;
            setAdjacentFields(field);
        }

        for (Coordinate coordinate : coordinatesOfAdjacentFields) {
            AdjacentSearchField searchField = (AdjacentSearchField) get(coordinate);
            searchField.setPatternLeft(getPattern(Direction.LEFT, searchField.getStepsLeft(), coordinate));
            searchField.setPatternUp(getPattern(Direction.UP, searchField.getStepsUp(), coordinate));
        }
    }

    public void searchPrefix(Inventory inventory, SearchTree searchTree) {
        for (Coordinate coordinate : coordinatesOfAdjacentFields) {
            AdjacentSearchField adjacentSearchField = (AdjacentSearchField) get(coordinate);
            adjacentSearchField.calculateValidPrefix(inventory, searchTree);
        }
    }

    public List<Move> searchWords(SearchTree searchTree) {
        List<Move> moves = new ArrayList<>();
        for (Coordinate coordinate : coordinatesOfAdjacentFields) {

        }
        return moves;
    }

    private void setAdjacentFields(Field field) {
        for (Direction direction : Objects.requireNonNull(Direction.class.getEnumConstants())) {
            Coordinate nextCoordinate = field.getCoordinate().getNextCoordinate(direction);

            if (!getDimension().isInsideRange(nextCoordinate) || get(nextCoordinate).getLetter() != null)
                continue;
            if (get(nextCoordinate) instanceof AdjacentSearchField) {
                ((AdjacentSearchField) get(nextCoordinate)).addDirectionDiscovered(direction);
                continue;
            }

            AdjacentSearchField searchField = new AdjacentSearchField(field);
            searchField.setCoordinate(nextCoordinate);
            searchField.setStepsUp(getAmountFreeFields(Direction.UP, nextCoordinate));
            searchField.setStepsLeft(getAmountFreeFields(Direction.LEFT, nextCoordinate));
            searchField.setWordUp(getWord(Direction.UP, nextCoordinate));
            searchField.setWordLeft(getWord(Direction.LEFT, nextCoordinate));
            searchField.setWordDown(getWord(Direction.DOWN, nextCoordinate));
            searchField.setWordRight(getWord(Direction.RIGHT, nextCoordinate));
            searchField.addDirectionDiscovered(direction);

            set(nextCoordinate, searchField);
            coordinatesOfAdjacentFields.add(nextCoordinate);
        }
    }

    private Pattern getPattern(Direction direction, int steps, Coordinate coordinate) {
        Pattern pattern = new Pattern();
        int currentStep = 0;
        while(currentStep < steps){
            SearchField searchField = get(coordinate);
            if (searchField instanceof AdjacentSearchField) {
                pattern.add(currentStep, ((AdjacentSearchField) searchField)
                        .getValidLetters().get(direction.getAxis().getOtherAxis()));
            }
            currentStep++;
            coordinate  = coordinate.getNextCoordinate(direction);
        }
        return pattern;
    }

    private int getAmountFreeFields(Direction direction, Coordinate coordinate) {
        int amount = 0;
        Coordinate nextCoordinate = coordinate.getNextCoordinate(direction);

        while (getDimension().isInsideRange(nextCoordinate) && amount < 7) {
            if (get(nextCoordinate).getLetter() != null) break;
            amount++;
            nextCoordinate = nextCoordinate.getNextCoordinate(direction);
            if (!getDimension().isInsideRange(nextCoordinate)) amount++;
        }
        return Math.min(amount, 7);
    }

    public void calculatePossibleCharacters(Inventory inventory, SearchTree searchTree) {
        char[] lettersToCheck = inventory.getSetOfPossibleLetters();
        for (Coordinate coordinate : coordinatesOfAdjacentFields) {
            AdjacentSearchField searchField = (AdjacentSearchField) get(coordinate);
            for (Axis axis : Objects.requireNonNull(Axis.class.getEnumConstants())) {
                String prefix = searchField.getWord(axis.getDirectionNegative());
                SearchTreeNode nodePrefix = searchTree.search(prefix);
                if (nodePrefix == null) throw new IllegalStateException();
                for (char letter : lettersToCheck) {
                    String word = letter + searchField.getWord(axis.getDirectionPositive());
                    if (searchTree.isValidWord(nodePrefix, word)) {
                        searchField.add(axis, letter);
                    }
                }
            }
        }
    }


    @Override
    public SearchField get(Coordinate coordinate) {
        return (SearchField) super.get(coordinate);
    }

    protected void initialize() {
        for (Coordinate coordinate : getDimension()) {
            SearchField field = new SearchField(coordinate);
            set(coordinate, field);
        }
    }

    private String getWord(Direction direction, Coordinate coordinate) {
        StringBuilder stringBuilder = new StringBuilder();

        while (getDimension().isInsideRange(coordinate)) {
            coordinate = coordinate.getNextCoordinate(direction);
            Letter letter = get(coordinate).getLetter();
            if (letter == null) break;
            if (direction.isNegativeDirection())
                stringBuilder.insert(0, letter.getValue());
            else
                stringBuilder.append(letter.getValue());
        }
        return stringBuilder.toString();
    }
}
