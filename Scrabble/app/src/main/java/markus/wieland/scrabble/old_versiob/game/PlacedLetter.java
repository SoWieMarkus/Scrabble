package markus.wieland.scrabble.old_versiob.game;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.helper.Coordinate;

@Getter
@Setter
public class PlacedLetter {

    private Letter letter;
    private String playerId;
    private boolean concrete;
    private Coordinate coordinate;

}
