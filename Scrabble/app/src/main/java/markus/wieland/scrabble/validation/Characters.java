package markus.wieland.scrabble.validation;

import java.util.HashMap;
import java.util.Map;

import markus.wieland.scrabble.new_version.board.Letter;

public class Characters {

    private final HashMap<Character, Integer> charactersOfWord;

    public Characters(String word) {
        this(word.toCharArray());
    }

    public Characters(){
        this(new char[0]);
    }

    public Characters(char[] characters) {
        charactersOfWord = new HashMap<>();
        for (char letter : characters) {
            add(letter);
        }
    }

    public void add(char letter){
        Integer amount = charactersOfWord.get(letter);
        if (amount == null) {
            charactersOfWord.put(letter, 1);
            return;
        }
        charactersOfWord.put(letter, amount + 1);
    }

    public int checkLetter(char letter, int amount) {
        Integer amountAvailable = charactersOfWord.get(letter);
        amountAvailable = amountAvailable == null ? 0 : amountAvailable;
        return Math.min(amountAvailable - amount, 0);
    }

    public boolean isPossible(Characters charactersFromInventory){
        int score = charactersFromInventory.getJoker();
        for (Map.Entry<Character, Integer> letter : charactersOfWord.entrySet()) {
            score+= charactersFromInventory.checkLetter(letter.getKey(), letter.getValue());
            if (score < 0) return false;
        }
        return true;
    }

    public int getJoker(){
        Integer amountJoker = this.charactersOfWord.get(Letter.JOKER);
        return amountJoker != null ? amountJoker : 0;
    }


}
