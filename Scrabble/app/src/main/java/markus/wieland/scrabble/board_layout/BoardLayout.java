package markus.wieland.scrabble.board_layout;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.new_version.board.board_layout.BoardLayoutField;
import markus.wieland.scrabble.new_version.helper.Dimension;

@Getter
@Setter
public class BoardLayout {

    private Dimension dimensions;
    private List<BoardLayoutField> specialFields;

}
