package markus.wieland.scrabble.new_version;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.R;
import markus.wieland.scrabble.new_version.board.BoardMatrix;
import markus.wieland.scrabble.new_version.board.Field;
import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.game.SpecialBlockType;
import markus.wieland.scrabble.board.layout.BoardLayout;
import markus.wieland.scrabble.new_version.config.LetterGenerator;
import markus.wieland.scrabble.helper.Coordinate;
import markus.wieland.scrabble.helper.Matrix;
import markus.wieland.scrabble.new_version.views.LetterView;
import markus.wieland.scrabble.new_version.views.SpecialBlockView;
import markus.wieland.scrabble.new_version.word_finder.ScrabbleWordFinder;
import markus.wieland.scrabble.old_versiob.validation.IllegalConnectionCheck;
import markus.wieland.scrabble.old_versiob.validation.IllegalMultipleRowColumnCheck;
import markus.wieland.scrabble.old_versiob.validation.IllegalWordCheck;
import markus.wieland.scrabble.old_versiob.validation.Validation;
import markus.wieland.scrabble.old_versiob.validation.ValidationResult;

@Getter
public class Scrabble {

    private final Matrix<SpecialBlockView> specialBlockViews;
    private final Matrix<LetterView> letterViews;

    private final BoardMatrix boardMatrix;
    private final Activity activity;

    private final Dictionary dictionary;

    private final LetterCollection letterCollection;

    public Scrabble(Activity activity, BoardLayout boardLayout, Matrix<SpecialBlockView> specialBlockViews, Matrix<LetterView> letterViews) {
        this.activity = activity;
        this.boardMatrix = new BoardMatrix(boardLayout);
        this.dictionary = new Dictionary();

        this.letterCollection = new LetterCollection(LetterGenerator.generate(activity));

        this.specialBlockViews = specialBlockViews;
        this.letterViews = letterViews;

        for (Field field : boardMatrix) {
            SpecialBlockType specialBlock = field.getSpecialBlock();
            if (specialBlock == null) continue;
            specialBlockViews.get(field.getCoordinate()).set(specialBlock);
        }
    }

    public boolean setLetter(Coordinate coordinate, Letter letter) {
        if (!boardMatrix.setLetter(coordinate, letter)) return false;

        letterViews.get(coordinate).setLetter(letter);
        return true;
    }

    public ValidationResult validate() {
        List<Word> newWords = dictionary.getNewWords(boardMatrix);
        if (newWords.isEmpty()) return new ValidationResult.NonValidResult(R.string.error_message_no_new_word);

        Validation[] validations = new Validation[]{
                new IllegalConnectionCheck(boardMatrix, boardMatrix.getStartCoordinate()),
                new IllegalWordCheck(activity, newWords),
                new IllegalMultipleRowColumnCheck(boardMatrix.getNonConcreteLetters())
        };

        for (Validation validation : validations) {
            long millis = System.currentTimeMillis();
            ValidationResult validationResult = validation.validate();
            Log.e(Scrabble.class.getSimpleName(), (System.currentTimeMillis()-millis) + "ms");
            if (validationResult.isValid()) continue;
            return validationResult;
        }

        Inventory inventory = new Inventory();
        List<Letter> letters = new ArrayList<>();
        letters.add(new Letter(1, 'H'));
        letters.add(new Letter(1, 'O'));
        letters.add(new Letter(1, 'F'));
        letters.add(new Letter(1, 'A'));
        letters.add(new Letter(1, 'R'));
        letters.add(new Letter(1, 'Z'));
        letters.add(new Letter(1, 'T'));
        inventory.add(letters);
        ScrabbleWordFinder.getPossibleMoves(activity, inventory.getCharacters(), boardMatrix);
//TODO +50 points
        return new ValidationResult();

    }

}
