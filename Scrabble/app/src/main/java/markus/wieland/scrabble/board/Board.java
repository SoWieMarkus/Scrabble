package markus.wieland.scrabble.board;

import markus.wieland.scrabble.board.layout.BoardLayoutField;
import markus.wieland.scrabble.board.word_managment.solver.AdjacentSearchField;
import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.game.SpecialBlockType;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Dimension;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.board.layout.BoardLayout;

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
}
