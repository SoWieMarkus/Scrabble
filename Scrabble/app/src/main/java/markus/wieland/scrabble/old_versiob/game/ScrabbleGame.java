package markus.wieland.scrabble.old_versiob.game;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.Dictionary;
import markus.wieland.scrabble.new_version.LetterCollection;
import markus.wieland.scrabble.old_versiob.validation.ValidationResult;

@Getter
public class ScrabbleGame {

    private final List<Player> players;
    private LetterCollection letterCollection;

    private final Activity activity;

    private final Dictionary dictionary;

    private PlacedLetterMatrix placedLetters;
    private SpecialFieldsMatrix boardSpecialFieldTypes;

    public ScrabbleGame(Activity activity) {
        this.activity = activity;
        this.players = new ArrayList<>();
        this.dictionary = new Dictionary();
    }

    public void registerPlayer(Player player) {
        this.players.add(player);
    }

    public void executeMove() {

        ValidationResult validationResult = validate();
        if (!validationResult.isValid()) {
            Log.e("ERROR", validationResult.getErrorMessage(activity));
            return;
        }

        for (PlacedLetter placedLetter : placedLetters) {
            placedLetter.setConcrete(true);
        }
    }

    public ValidationResult validate() {
        /*Validation[] validations = new Validation[]{
                new IllegalConnectionCheck(placedLetters, boardSpecialFieldTypes.getCoordinateOfFieldType(BoardSpecialFieldType.START, true).get(0)),
                new IllegalWordCheck(activity, dictionary.getNewWords(placedLetters)),
                new IllegalMultipleRowColumnCheck(placedLetters.getNonConcreteLetters())
        };

        for (Validation validation : validations) {
            ValidationResult validationResult = validation.validate();
            if (validationResult.isValid()) continue;
            return validationResult;
        }*/

        return new ValidationResult();
    }

}
