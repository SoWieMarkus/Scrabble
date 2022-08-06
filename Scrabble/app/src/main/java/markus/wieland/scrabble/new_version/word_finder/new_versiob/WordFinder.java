package markus.wieland.scrabble.new_version.word_finder.new_versiob;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import markus.wieland.scrabble.new_version.Inventory;
import markus.wieland.scrabble.new_version.board.BoardMatrix;
import markus.wieland.scrabble.new_version.board.Field;
import markus.wieland.scrabble.new_version.helper.Coordinate;
import markus.wieland.scrabble.new_version.helper.Dimension;
import markus.wieland.scrabble.new_version.helper.Direction;
import markus.wieland.scrabble.new_version.helper.FileReader;

public class WordFinder {

    private final SearchField[][] searchFields;
    private static final SearchTree searchTree = new SearchTree();
    private final List<Coordinate> coordinatesOfAdjacentFields;
    private final Dimension dimension;
    private final Inventory inventory;

    public WordFinder(Activity activity, BoardMatrix boardMatrix, Inventory inventory) {
        if (!searchTree.isInitialized()) initializeSearchTree(activity);

        dimension = boardMatrix.getDimension();
        coordinatesOfAdjacentFields = new ArrayList<>();
        this.inventory = inventory;

        searchFields = new SearchField[boardMatrix.getHeight()][boardMatrix.getWidth()];

        prepare(boardMatrix);
        print();
        calculatePrefix();
        test();
    }

    private void calculateAdditionalInfo() {
        for (Coordinate coordinate : coordinatesOfAdjacentFields) {
            SearchField searchField = searchFields[coordinate.getX()][coordinate.getY()];
            if (!searchField.isAdjacent()) continue;
            searchField.setStepsLeft(getAmountOfFreeFields(coordinate, Direction.LEFT));
            searchField.setStepsUp(getAmountOfFreeFields(coordinate, Direction.UP));

            if (searchField.containsHorizontal()) {
                for (char character : inventory.getAsCharArray()) {
                    String word = getWord(Direction.LEFT, coordinate, character);
                    if (searchTree.isValidAndTerminal(word)) {
                        searchField.getValidCharactersHorizontal().add(character);
                    }
                }
            }

            if (searchField.containsVertical()) {
                for (char character : inventory.getAsCharArray()) {
                    String word = getWord(Direction.UP, coordinate, character);
                    if (searchTree.isValidAndTerminal(word)) {
                        searchField.getValidCharactersVertical().add(character);
                    }
                }
            }
        }
    }

    public void calculatePrefix() {
        int progress = 0;
        for (Coordinate coordinate : coordinatesOfAdjacentFields) {
            SearchField searchField = searchFields[coordinate.getX()][coordinate.getY()];
            Coordinate coordinateRight = coordinate.getNextCoordinate(Direction.RIGHT);
            Coordinate coordinateDOWN = coordinate.getNextCoordinate(Direction.DOWN);
            String wordRight = getWord(coordinateRight, Direction.RIGHT);
            String wordDown = getWord(coordinateDOWN, Direction.DOWN);

            Pattern patternUp = getPattern(coordinate, Direction.UP, searchField.getStepsUp());
            Pattern patternLeft = getPattern(coordinate, Direction.LEFT, searchField.getStepsLeft());
            for (String prefix : inventory.getAllPossibleCombinations(searchField.getStepsUp())) {
                if (!patternUp.isLongEnough(prefix.length())) continue;
                if (prefix.matches(patternUp.toString(prefix.length()))) {
                    SearchTreeNode searchTreeNode = searchTree.search(prefix + wordDown);
                    if (searchTreeNode == null) continue;
                    if (wordDown.isEmpty() && !searchTreeNode.isTerminal()) continue;
                    searchField.getValidPrefixVertical().add(new Prefix(prefix, prefix + wordDown, searchTreeNode));
                }
            }
            for (String prefix : inventory.getAllPossibleCombinations(searchField.getStepsLeft())) {
                if (!patternLeft.isLongEnough(prefix.length())) continue;
                if (prefix.matches(patternLeft.toString(prefix.length()))) {
                    SearchTreeNode searchTreeNode = searchTree.search(prefix + wordRight);
                    if (searchTreeNode == null) continue;
                    if (wordRight.isEmpty() && !searchTreeNode.isTerminal()) continue;
                    searchField.getValidPrefixHorizontal().add(new Prefix(prefix, prefix + wordRight, searchTreeNode));
                }
            }
            progress++;
            Log.e("TEST", progress + "/" + coordinatesOfAdjacentFields.size());
        }
    }

    public void prepare(BoardMatrix boardMatrix) {
        this.coordinatesOfAdjacentFields.clear();
        for (Field field : boardMatrix) {
            if (field == null) throw new IllegalArgumentException();
            searchFields[field.getCoordinate().getX()][field.getCoordinate().getY()] = new SearchField(field);
        }

        for (int i = 0; i < boardMatrix.getHeight(); i++) {
            for (int j = 0; j < boardMatrix.getWidth(); j++) {
                Coordinate currentCoordinate = new Coordinate(i, j);
                SearchField searchField = searchFields[i][j];
                if (searchField.isLetter()) {
                    for (Direction direction : Objects.requireNonNull(Direction.class.getEnumConstants())) {
                        Coordinate coordinate = currentCoordinate.getNextCoordinate(direction);
                        SearchField currentSearchField = searchFields[coordinate.getX()][coordinate.getY()];
                        if (!boardMatrix.isInsideMatrix(coordinate) || !currentSearchField.setAdjacent(direction))
                            continue;
                        coordinatesOfAdjacentFields.add(coordinate);
                    }
                }
            }
        }

        calculateAdditionalInfo();
    }

    public void test() {
        searchTree.searchWithJoker("?ALL?");
    }

    public void print() {
        for (int i = 0; i < dimension.getHeight(); i++) {
            StringBuilder lol = new StringBuilder();
            for (int j = 0; j < dimension.getHeight(); j++) {
                if (searchFields[i][j].isLetter()) lol.append(searchFields[i][j].getLetter());
                else lol.append(searchFields[i][j].getStepsLeft());
            }
            System.out.println(lol.toString());
        }
    }


    public static void initializeSearchTree(Activity activity) {
        FileReader fileReader = new FileReader(activity);
        String[] words = fileReader.read("words.txt").split("\r\n");
        for (String word : words) {
            searchTree.add(word);
        }
    }

    private Pattern getPattern(Coordinate coordinate, Direction direction, int steps) {
        Pattern pattern = new Pattern();
        int currentStep = 0;
        while (dimension.isInsideRange(coordinate) && currentStep < steps) {
            String patternOfField = searchFields[coordinate.getX()][coordinate.getY()].getPattern();
            if (patternOfField.equals(SearchField.BREAK_PATTERN)) break;
            pattern.add(patternOfField);
            currentStep++;
            coordinate = coordinate.getNextCoordinate(direction);
        }
        return pattern;
    }

    private String getWord(Coordinate coordinate, Direction direction) {
        if (!searchFields[coordinate.getX()][coordinate.getY()].isLetter()) return "";
        StringBuilder current = new StringBuilder();
        Coordinate currentCoordinate = coordinate;
        while (dimension.isInsideRange(currentCoordinate)) {
            SearchField searchField = searchFields[currentCoordinate.getX()][currentCoordinate.getY()];
            if (!searchField.isLetter()) break;
            current.append(searchField.getLetter());
            currentCoordinate = currentCoordinate.getNextCoordinate(direction);
        }

        return current.toString();
    }

    private int getAmountOfFreeFields(Coordinate coordinate, Direction direction) {
        int amountOfFreeFields = 0;
        do {
            Coordinate nextCoordinate = coordinate.getNextCoordinate(direction);
            if (!dimension.isInsideRange(nextCoordinate)) {
                amountOfFreeFields++;
                break;
            }
            SearchField searchField = searchFields[nextCoordinate.getX()][nextCoordinate.getY()];
            if (searchField.isLetter()) break;
            coordinate = nextCoordinate;
            amountOfFreeFields++;

        } while (amountOfFreeFields < 7);
        return amountOfFreeFields;
    }

    private String getWord(Direction direction, Coordinate coordinate, char character) {
        Direction directionFirst = direction.isHorizontal() ? Direction.LEFT : Direction.UP;
        Direction directionSecond = direction.isHorizontal() ? Direction.RIGHT : Direction.DOWN;

        StringBuilder current = new StringBuilder(String.valueOf(character));
        Coordinate currentCoordinate = coordinate.getNextCoordinate(directionFirst);
        while (dimension.isInsideRange(currentCoordinate)) {
            SearchField searchField = searchFields[currentCoordinate.getX()][currentCoordinate.getY()];
            if (!searchField.isLetter()) break;
            current.insert(0, searchField.getLetter());
            currentCoordinate = currentCoordinate.getNextCoordinate(directionFirst);
        }

        currentCoordinate = coordinate.getNextCoordinate(directionSecond);
        while (dimension.isInsideRange(currentCoordinate)) {
            SearchField searchField = searchFields[currentCoordinate.getX()][currentCoordinate.getY()];
            if (!searchField.isLetter()) break;
            current.append(searchField.getLetter());
            currentCoordinate = currentCoordinate.getNextCoordinate(directionSecond);
        }

        return current.toString();
    }


}
