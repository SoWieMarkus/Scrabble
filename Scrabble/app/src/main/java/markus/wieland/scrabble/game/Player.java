package markus.wieland.scrabble.game;

import lombok.Getter;

@Getter
public class Player {

    private long id;
    private String name;
    private Inventory inventory;
    private int currentSkipStreak;

}
