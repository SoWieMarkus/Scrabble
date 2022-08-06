package markus.wieland.scrabble.new_version.board.board_layout;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board_layout.BoardSpecialFieldType;
import markus.wieland.scrabble.new_version.board.SpecialBlock;
import markus.wieland.scrabble.new_version.board.SpecialBlockType;
import markus.wieland.scrabble.new_version.helper.Coordinate;

@Getter
@Setter
public class BoardLayoutField {

    private SpecialBlockType type;
    private Coordinate coordinate;

    public SpecialBlockType getSpecialBlock(){
        return type;
    }

}
