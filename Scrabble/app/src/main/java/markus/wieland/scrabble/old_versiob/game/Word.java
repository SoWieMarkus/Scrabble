package markus.wieland.scrabble.old_versiob.game;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private final List<PlacedLetter> letters;

    public Word(){
        this.letters = new ArrayList<>();
    }

    public void add(PlacedLetter letter) {
        this.letters.add(letter);
    }

    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for (PlacedLetter letter : letters) {
            word.append(letter.getLetter().toString());
        }
        return word.toString();
    }

    public int getLength(){
        return letters.size();
    }

    public String getId(){
        StringBuilder word = new StringBuilder();
        for (PlacedLetter letter : letters) {
            word.append(letter.getLetter().toString())
                    .append(letter.getCoordinate().getX())
                    .append(letter.getCoordinate().getY());
        }
        return word.toString();
    }




}
