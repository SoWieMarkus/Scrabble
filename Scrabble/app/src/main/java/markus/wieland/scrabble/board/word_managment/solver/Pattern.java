package markus.wieland.scrabble.board.word_managment.solver;

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

    public Set<Character> get(int index) {
        return possibleCharactersAtPosition.get(index);
    }

    public boolean canLetterBePlaced(int index){
        Set<Character> characters = possibleCharactersAtPosition.get(index);
        if (characters == null) return true;
        return !characters.isEmpty();
    }
}
