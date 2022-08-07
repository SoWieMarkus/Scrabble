package markus.wieland.scrabble.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Letter {

    public static final char JOKER = '?';

    private final int score;
    private final char value;

    public boolean isJoker() {
        return value == JOKER;
    }

}
