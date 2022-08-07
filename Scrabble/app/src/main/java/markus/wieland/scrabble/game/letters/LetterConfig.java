package markus.wieland.scrabble.game.letters;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.game.Letter;

@Getter
@Setter
public class LetterConfig {

        private char value;
        private int score;
        private int amount;

        public Letter getLetter() {
            return new Letter(score, value);
        }
}
