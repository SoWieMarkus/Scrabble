package markus.wieland.scrabble.new_version.board;

import androidx.annotation.DrawableRes;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.game.SpecialBlockType;

@Getter
@Setter
public class SpecialBlock {

    private boolean used;
    private SpecialBlockType specialBlockType;

    public SpecialBlock(SpecialBlockType specialBlockType) {
        this.specialBlockType = specialBlockType;
    }

    @DrawableRes
    public int getDrawable() {
        return specialBlockType.getDrawable();
    }
}
