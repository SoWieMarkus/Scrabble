package markus.wieland.scrabble.new_version.board;

import androidx.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Letter {

    public static final char JOKER = '?';

    private int score;
    private char value;

    @NonNull
    @Override
    public String toString() {
        if (isJoker()) return "";
        return String.valueOf(value);
    }

    public boolean isJoker() {
        return value == JOKER;
    }

}
