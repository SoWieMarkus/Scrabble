package markus.wieland.scrabble.game;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.board.Field;

public class Word {

    private final List<Field> fields;

    public Word() {
        this.fields = new ArrayList<>();
    }

    public void add(Field field) {
        if (fields.isEmpty()) {
            fields.add(field);
            return;
        }

        Field firstField = fields.get(0);
        boolean append = (field.getCoordinate().getX() >= firstField.getCoordinate().getX()) &&
                (field.getCoordinate().getX() != firstField.getCoordinate().getX() || field.getCoordinate().getY() >= firstField.getCoordinate().getY());

        if (append) {
            fields.add(field);
            return;
        }
        fields.add(0, field);
    }

    public int getScore() {
        int score = 0;
        int amountTripleWord = 0;
        int amountDoubleWord = 0;
        for (Field field : fields) {
            int scoreOfField = field.getLetter().getScore();
            switch (field.getSpecialBlockTypeIfNotConcrete()) {
                case LETTER_TIMES_THREE:
                    scoreOfField *= 3;
                    break;
                case LETTER_TIMES_TWO:
                    scoreOfField *= 2;
                    break;
                case WORD_TIMES_TWO:
                    amountDoubleWord++;
                    break;
                case WORD_TIMES_THREE:
                    amountTripleWord++;
                    break;
            }
            score += scoreOfField;
        }
        if (amountDoubleWord > 0) score *= (amountDoubleWord * 2);
        if (amountTripleWord > 0) score *= (amountTripleWord * 3);
        return score;

    }

}
