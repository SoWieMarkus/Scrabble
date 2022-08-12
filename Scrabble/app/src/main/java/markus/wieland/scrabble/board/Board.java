package markus.wieland.scrabble.board;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.board.layout.BoardLayoutField;
import markus.wieland.scrabble.board.word_managment.solver.board.AdjacentSearchField;
import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.game.SpecialBlockType;
import markus.wieland.scrabble.game.Word;
import markus.wieland.scrabble.helper.Axis;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Dimension;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.board.layout.BoardLayout;
import markus.wieland.scrabble.helper.Range;

public class Board extends Matrix<Field> {

    public Board(Dimension dimension) {
        super(dimension);
        initialize();
    }

    public Board(BoardLayout boardLayout) {
        this(boardLayout.getDimensions());

        for (BoardLayoutField boardLayoutField : boardLayout.getSpecialFields()) {
            get(boardLayoutField.getCoordinate()).setSpecialBlockType(boardLayoutField.getSpecialBlock());
        }

        //TODO
    }

    protected void initialize() {
        for (Coordinate coordinate : getDimension()) {
            Field field = new Field(coordinate);
            set(coordinate, field);
        }
    }

    public void print(){
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < getHeight(); i++) {

            for (int j = 0; j < getWidth(); j++) {
                if (get(i,j) instanceof AdjacentSearchField){
                    row.append(((AdjacentSearchField) get(i,j)).getStepsLeft());
                    continue;
                }
                Letter letter = get(i,j).getLetter();
                row.append(letter == null ? ".": letter.getValue());
            }
            row.append("\n");
        }
        System.out.println(row);
    }

    public Coordinate getStartCoordinate() {
        for (Field field : this) {
            if (field.getSpecialBlockType() == null) continue;
            if (field.getSpecialBlockType() == SpecialBlockType.START) return field.getCoordinate();
        }
        throw new IllegalStateException();
    }

    public void clearNonConcreteLetters(){
        for (Field field : this) {
            if (!field.isConcrete() && field.getLetter() != null) {
                field.setLetter(null);
            }
        }
    }

    public List<Word> getWords(){
        List<Word> extractedWords = new ArrayList<>();

        Word horizontal = new Word();
        Word vertical = new Word();

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {

                Field verticalLetter = get(new Coordinate(j, i));
                Field horizontalLetter = get(new Coordinate(i, j));

                if (verticalLetter.getLetter() == null) {
                    if (vertical.getLength() > 1) {
                        extractedWords.add(vertical);
                    }
                    vertical = new Word();
                } else {
                    vertical.add(verticalLetter);
                }

                if (horizontalLetter.getLetter() == null) {
                    if (horizontal.getLength() > 1) {
                        extractedWords.add(horizontal);
                    }
                    horizontal = new Word();
                } else {
                    horizontal.add(horizontalLetter);
                }
            }
        }

        return extractedWords;
    }

    public List<Word> getWords(Range range) {
        List<Word> words = new ArrayList<>();

        Axis axis = range.getAxis();
        Word word = getWord(range.getStartCoordinate(), axis);
        if (word != null) words.add(word);
        for (Coordinate coordinate : range.getListOfCoordinates()) {
            Field field = get(coordinate);
            if (field.isConcrete()) continue;
            Word adjacentWord = getWord(coordinate, axis.getOtherAxis());
            if (adjacentWord != null) words.add(adjacentWord);
        }
        return words;
    }

    public Word getWord(Coordinate coordinate, Axis axis) {
        Coordinate currentCoordinate = coordinate;
        while (getDimension().isInsideRange(currentCoordinate) && get(currentCoordinate).getLetter() != null) {
            currentCoordinate = currentCoordinate.getNextCoordinate(axis.getDirectionNegative());
        }

        Word word = new Word();
        currentCoordinate = currentCoordinate.getNextCoordinate(axis.getDirectionPositive());
        while (getDimension().isInsideRange(currentCoordinate) && get(currentCoordinate).getLetter() != null) {
            word.add(get(currentCoordinate));
            currentCoordinate = currentCoordinate.getNextCoordinate(axis.getDirectionPositive());
        }

        if (word.getLength() > 1) return word;
        return null;
    }




}
