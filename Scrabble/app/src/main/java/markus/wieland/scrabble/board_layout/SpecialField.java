package markus.wieland.scrabble.board_layout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialField {

    private BoardSpecialFieldType type;
    private boolean used;

}
