package markus.wieland.scrabble.old_versiob.game;

import androidx.annotation.NonNull;

import lombok.Getter;

@Getter
public class Letter {

    public static final String JOKER = "?";

    private final String value;
    private final int points;

    public Letter(String value, int points) {
        this.value = value.toUpperCase();
        this.points = points;
    }

    @NonNull
    @Override
    public String toString() {
        if (value.equals(JOKER)) return "";
        return value;
    }

}
