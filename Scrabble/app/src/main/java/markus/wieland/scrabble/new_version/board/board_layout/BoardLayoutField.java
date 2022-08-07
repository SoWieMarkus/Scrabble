package markus.wieland.scrabble.new_version.board.board_layout;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.game.SpecialBlockType;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
public class BoardLayoutField {

    private SpecialBlockType type;
    private Coordinate coordinate;

    public SpecialBlockType getSpecialBlock(){
        return type;
    }

}
