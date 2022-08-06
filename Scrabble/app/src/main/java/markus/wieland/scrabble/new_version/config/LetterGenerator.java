package markus.wieland.scrabble.new_version.config;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.new_version.board.Letter;
import markus.wieland.scrabble.new_version.helper.FileReader;

public class LetterGenerator {

    private static final String FILE_NAME_LETTERS_CONFIG = "letters_config.json";

    private LetterGenerator() {
    }

    public static List<Letter> generate(Activity activity) {
        List<Letter> letters = new ArrayList<>();

        FileReader fileReader = new FileReader(activity);
        RawLetter[] rawLetters = fileReader.read(FILE_NAME_LETTERS_CONFIG, RawLetter[].class);

        for (RawLetter rawLetter : rawLetters) {
            for (int i = 0; i < rawLetter.getAmount(); i++) {
                letters.add(rawLetter.getLetter());
            }
        }
        return letters;
    }

}
