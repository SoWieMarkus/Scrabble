package markus.wieland.scrabble.new_version.board;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.game.SpecialBlockType;
import markus.wieland.scrabble.new_version.Word;
import markus.wieland.scrabble.new_version.board.board_layout.BoardLayout;
import markus.wieland.scrabble.new_version.board.board_layout.BoardLayoutField;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Matrix;


public class BoardMatrix extends Matrix<Field> {

    public BoardMatrix(BoardLayout boardLayout) {
        super(boardLayout.getDimensions());

        for (int x = 0; x < boardLayout.getDimensions().getHeight(); x++) {
            for (int y = 0; y < boardLayout.getDimensions().getWidth(); y++) {
                Field field = new Field();
                field.setCoordinate(new Coordinate(x, y));
                set(x, y, field);
            }
        }

        for (BoardLayoutField boardLayoutField : boardLayout) {
            get(boardLayoutField.getCoordinate()).setSpecialBlock(boardLayoutField.getSpecialBlock());
        }

    }

    public List<Field> getNonConcreteLetters() {
        List<Field> nonConcreteLetters = new ArrayList<>();
        for (Field field : this) {
            if (field.isConcrete() || field.getLetter() == null) continue;
            nonConcreteLetters.add(field);
        }
        return nonConcreteLetters;
    }

    public List<Word> extractWords() {
        List<Word> extractedWords = new ArrayList<>();

        Word horizontal = new Word();
        Word vertical = new Word();

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {

                Coordinate verticalCoordinate = new Coordinate(j, i);
                Coordinate horizontalCoordinate = new Coordinate(i, j);

                Letter verticalLetter = get(verticalCoordinate).getLetter();
                Letter horizontalLetter = get(horizontalCoordinate).getLetter();

                if (verticalLetter == null) {
                    if (vertical.getLength() > 1) {
                        extractedWords.add(vertical);
                    }
                    vertical = new Word();
                } else {
                    vertical.add(verticalLetter, verticalCoordinate);
                }

                if (horizontalLetter == null) {
                    if (horizontal.getLength() > 1) {
                        extractedWords.add(horizontal);
                    }
                    horizontal = new Word();
                } else {
                    horizontal.add(horizontalLetter, horizontalCoordinate);
                }
            }
        }

        return extractedWords;
    }

    public int getScoreOfWord(Word word) {
        int amountTimesTwo = 0;
        int amountTimesThree = 0;
        int totalScore = 0;

        for (Coordinate coordinate : word.getRange().getListOfCoordinates()) {
            Field field = get(coordinate);
            Letter letter = field.getLetter();
            SpecialBlockType specialBlock = field.getSpecialBlock();
            if (letter == null || specialBlock == null) throw new IllegalStateException();

            int scoreOfLetter = letter.getScore();

            if (field.isConcrete()) {
                totalScore += scoreOfLetter;
                continue;
            }

            switch (specialBlock) {
                case WORD_TIMES_TWO:
                case START:
                    amountTimesTwo++;
                    break;
                case LETTER_TIMES_TWO:
                    scoreOfLetter *= 2;
                    break;
                case WORD_TIMES_THREE:
                    amountTimesThree++;
                    break;
                case LETTER_TIMES_THREE:
                    scoreOfLetter *= 3;
                    break;
                default:
                    break;
            }

            totalScore += scoreOfLetter;
        }

        return totalScore * (amountTimesThree * 3) * (amountTimesTwo * 2);
    }

    public boolean setLetter(Coordinate coordinate, Letter letter) {
        Field field = get(coordinate);
        if (field.isConcrete() || field.getLetter() != null) return false;
        field.setLetter(letter);
        return true;
    }

    public List<Field> getAllLetters(){
        List<Field> allLetters = new ArrayList<>();
        for (Field field : this) {
            if (field.getLetter() == null) continue;
            allLetters.add(field);
        }
        return allLetters;
    }

    public Coordinate getStartCoordinate() {
        for (Field field : this) {
            if (field.getSpecialBlock() == null) continue;
            if (field.getSpecialBlock() == SpecialBlockType.START) return field.getCoordinate();
        }
        throw new IllegalStateException();
    }



}
