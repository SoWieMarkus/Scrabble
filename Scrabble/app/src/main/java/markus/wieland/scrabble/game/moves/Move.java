package markus.wieland.scrabble.game.moves;

import markus.wieland.scrabble.game.Player;
import markus.wieland.scrabble.game.Scrabble;

public interface Move {

    void execute(Player player, Scrabble scrabble);
}
