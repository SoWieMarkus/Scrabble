package markus.wieland.scrabble.validation;

import android.content.Context;

import androidx.annotation.StringRes;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import markus.wieland.scrabble.new_version.Word;

@Getter
public class ValidationResult {

    public boolean isValid() {
        return !(this instanceof NonValidResult);
    }

    public String getErrorMessage(Context context) {
        return null;
    }

    @Getter
    public static class NonValidResult extends ValidationResult {

        @StringRes
        private final int errorMessage;

        public NonValidResult(int errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public String getErrorMessage(Context context) {
            return context.getString(getErrorMessage());
        }
    }

    @Getter
    public static class ValidationResultWithWordContext extends NonValidResult {

        private final List<Word> words;

        ValidationResultWithWordContext(int errorMessage, List<Word> words) {
            super(errorMessage);
            this.words = words;
        }

        @Override
        public String getErrorMessage(Context context) {
            return super.getErrorMessage(context) + ": " + String.join(", ", words.stream()
                    .map(Word::toString)
                    .collect(Collectors.joining(", ")));
        }
    }

}
