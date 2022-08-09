package markus.wieland.scrabble.board.word_managment.word_finder;

import java.util.HashMap;
import java.util.Set;

public class Pattern {

    private final HashMap<Integer, Set<Character>> possibleCharactersAtPosition;

    public Pattern() {
        this.possibleCharactersAtPosition = new HashMap<>();
    }

    public void add(int index, Set<Character> characters) {
        this.possibleCharactersAtPosition.put(index, characters);
    }

    public boolean canLetterBePlaced(int index){
        Set<Character> characters = possibleCharactersAtPosition.get(index);
        if (characters == null) return true;
        return !characters.isEmpty();
    }
}
