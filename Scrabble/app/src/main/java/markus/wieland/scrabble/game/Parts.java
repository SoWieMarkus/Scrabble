package markus.wieland.scrabble.game;

import java.util.HashMap;
import markus.wieland.scrabble.new_version.board.Letter;

public class Parts {

    private final HashMap<String, Integer> parts;

    public Parts(String word) {
        this.parts = new HashMap<>();

        char[] letters = word.toCharArray();
        for (char letter : letters) {
            parts.putIfAbsent(letter + "", 0);
            int amount = parts.get(letter + "") + 1;
            parts.put(letter + "", amount);
        }
    }

    public int checkLetter(String letter, int amount) {
        int amountAvailable = parts.get(letter) == null ? 0 : parts.get(letter);
        return Math.min(amountAvailable - amount, 0);
    }

    public boolean isPossible(Parts partsFromInventory) {
        int score = partsFromInventory.getJoker();
        for (String key : this.parts.keySet()) {
            int amount = this.parts.get(key);
            score += partsFromInventory.checkLetter(key, amount);
            if (score < 0) return false;
        }
        return score >= 0;
    }

    public int getJoker() {
        return this.parts.get(Letter.JOKER) != null ? this.parts.get(Letter.JOKER) : 0;
    }


}
