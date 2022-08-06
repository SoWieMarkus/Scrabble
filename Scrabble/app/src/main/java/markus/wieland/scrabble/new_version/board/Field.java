package markus.wieland.scrabble.new_version.board;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.new_version.helper.Coordinate;

@Getter
@Setter
public class Field {

    private Letter letter;
    private SpecialBlockType specialBlock;
    private boolean isConcrete;
    private Coordinate coordinate;

    public Field(){
        isConcrete = false;
    }

}
