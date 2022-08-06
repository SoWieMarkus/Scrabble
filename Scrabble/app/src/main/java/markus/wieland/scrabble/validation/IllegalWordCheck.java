package markus.wieland.scrabble.validation;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import markus.wieland.scrabble.R;
import markus.wieland.scrabble.new_version.Word;


@AllArgsConstructor
public class IllegalWordCheck implements Validation {

    private final Activity activity;
    private final List<Word> words;

    @Override
    public ValidationResult validate() {

        List<Word> illegalWords = new ArrayList<>();
        for (Word word : words) {
            if (!WordValidator.validate(activity, word.toString()))
                illegalWords.add(word);
        }

        return illegalWords.isEmpty()
                ? new ValidationResult()
                : new ValidationResult.ValidationResultWithWordContext(R.string.error_message_invalid_word, words);
    }
}