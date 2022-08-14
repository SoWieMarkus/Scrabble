package markus.wieland.scrabble.board.word_managment.solver.words;

import java.util.HashMap;

public class SuffixPattern extends Pattern {

    private final HashMap<Integer, Character> concreteCharacters;

    public SuffixPattern(){
        this.concreteCharacters = new HashMap<>();
    }

    public void addConcreteLetter(int index, char letter) {
        this.concreteCharacters.put(index, letter);
    }

    public Character getConcreteLetter(int index) {
        return concreteCharacters.get(index);
    }

}
