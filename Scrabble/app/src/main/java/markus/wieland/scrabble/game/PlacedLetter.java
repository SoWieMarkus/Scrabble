package markus.wieland.scrabble.game;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.new_version.helper.Coordinate;

@Getter
@Setter
public class PlacedLetter {

    private Letter letter;
    private String playerId;
    private boolean concrete;
    private Coordinate coordinate;

}
