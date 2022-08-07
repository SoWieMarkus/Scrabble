package markus.wieland.scrabble.board.word_managment.word_finder;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.scrabble.board.Field;
import markus.wieland.scrabble.helper.Axis;

@Getter
@Setter
public class AdjacentSearchField extends SearchField {

    private final ValidLetters validLetters;
    private int stepsUp;
    private int stepsLeft;

    public AdjacentSearchField(Field field) {
        super(field.getCoordinate());
        validLetters = new ValidLetters();
        this.stepsLeft = 0;
        this.stepsUp = 0;
    }

    public void update(Field field) {
        setSpecialBlockType(field.getSpecialBlockType());
    }



    public void add(Axis axis, char letter) {
        validLetters.add(axis, letter);
    }

    public boolean canBePlaced(Axis axis, char letter) {
        return validLetters.contains(axis.getOtherAxis(), letter);
    }

}
