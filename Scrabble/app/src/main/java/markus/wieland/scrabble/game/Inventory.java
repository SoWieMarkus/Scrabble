package markus.wieland.scrabble.game;

import java.util.List;

import markus.wieland.scrabble.game.letters.Letters;

public class Inventory {

    private final int MAX_LETTERS_IN_INVENTORY = 7;
    private final Letter[] letters;

    public Inventory(){
        letters = new Letter[MAX_LETTERS_IN_INVENTORY];
    }

    public void add(List<Letter> lettersToAdd) {
        if (lettersToAdd.size() > MAX_LETTERS_IN_INVENTORY) throw new IllegalStateException();
        for (int i = 0; i < MAX_LETTERS_IN_INVENTORY; i++) {
            if (letters[i] != null) continue;
            if (lettersToAdd.isEmpty()) break;
            letters[i] = lettersToAdd.remove(0);
        }
        //TODO ordentlich amchen
        //calculateAllPossibleCombinations();
        if (!lettersToAdd.isEmpty()) throw new AssertionError();
    }

    public char[] getCharArray(){
        int amountOfLetters = 0;
        for (Letter letter : letters) {
            if (letter == null) continue;
            if (letter.isJoker()) return Letters.ALL_POSSIBLE_LETTERS;
            amountOfLetters++;
        }
        char[] currentLetters = new char[amountOfLetters];
        int index = 0;
        for (Letter letter : letters) {
            if (letter == null) continue;
            currentLetters[index] = letter.getValue();
            index++;
            if (index == amountOfLetters) break;
        }
        return currentLetters;
    }


}
