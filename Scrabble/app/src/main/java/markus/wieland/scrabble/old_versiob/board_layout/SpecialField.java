package markus.wieland.scrabble.old_versiob.board_layout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialField {

    private BoardSpecialFieldType type;
    private boolean used;

}
