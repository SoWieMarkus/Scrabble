package markus.wieland.scrabble.game;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Players extends ArrayList<Player> {

    private int currentIndex;

    public Players(){
        super();
        currentIndex = 0;
    }

    public Player getNextPlayer(){
        currentIndex++;
        if (size() <= currentIndex){
            currentIndex = 0;
        }
        return getCurrentPlayer();
    }

    public Player getCurrentPlayer(){
        return isEmpty() ? null : get(currentIndex);
    }
}
