package markus.wieland.scrabble.game.letters;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import markus.wieland.scrabble.game.Letter;
import markus.wieland.scrabble.helper.FileReader;

@Getter
public class Letters {

    private static final char[] ALL_POSSIBLE_LETTERS = new char[29];
    private static final String LETTERS_CONFIG_FILE = "letters_config.json";
    private static final List<LetterConfig> LETTERS_CONFIG = new ArrayList<>();

    private final List<Letter> listOfLetters;

    public Letters(Activity activity) {
        this.listOfLetters = new ArrayList<>();
        if (LETTERS_CONFIG.isEmpty()) initialize(activity);
        for (LetterConfig letterConfig : LETTERS_CONFIG) {
            for (int i = 0; i < letterConfig.getAmount(); i++) {
                this.listOfLetters.add(letterConfig.getLetter());
            }
        }
        Collections.shuffle(this.listOfLetters);
    }

    public static void initialize(Activity activity) {
        FileReader fileReader = new FileReader(activity);
        LETTERS_CONFIG.clear();
        LETTERS_CONFIG.addAll(Arrays.asList(fileReader.read(LETTERS_CONFIG_FILE, LetterConfig[].class)));
        int index = 0;
        for (char i = 'A'; i <= 'Z'; i++) {
            ALL_POSSIBLE_LETTERS[index] = i;
        }
        ALL_POSSIBLE_LETTERS[26] = 'Ä';
        ALL_POSSIBLE_LETTERS[27] = 'Ö';
        ALL_POSSIBLE_LETTERS[28] = 'Ü';
    }

}
