package markus.wieland.scrabble.new_version.board;

import androidx.annotation.DrawableRes;

import lombok.Getter;
import markus.wieland.scrabble.R;

@Getter
public enum SpecialBlockType {

    WORD_TIMES_THREE(R.drawable.word_times_three),
    WORD_TIMES_TWO(R.drawable.word_times_two),
    START(R.drawable.start),
    DEFAULT(R.drawable.empty),
    LETTER_TIMES_THREE(R.drawable.letter_times_three),
    LETTER_TIMES_TWO(R.drawable.letter_times_two);

    @DrawableRes
    private final int drawable;

    SpecialBlockType(int drawable) {
        this.drawable = drawable;
    }
}
