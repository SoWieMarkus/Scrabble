package markus.wieland.scrabble.game;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.scrabble.board.Field;

public class Word {

    private final List<Field> fields;

    public Word() {
        this.fields = new ArrayList<>();
    }

    public void add(Field field) {
        int index = 0;
        if (!fields.isEmpty()) {
            Field firstField = fields.get(0);
            if ((field.getCoordinate().getX() >= firstField.getCoordinate().getX()) &&
                    (field.getCoordinate().getX() != firstField.getCoordinate().getX() || field.getCoordinate().getY() >= firstField.getCoordinate().getY())) {
                index = fields.size();
            }
        }
        fields.add(index, field);
    }

    public int getLength() {
        return this.fields.size();
    }

    public int getScore() {
        int score = 0;
        int amountTripleWord = 0;
        int amountDoubleWord = 0;
        for (Field field : fields) {
            int scoreOfField = field.getLetter().getScore();
            if (field.getSpecialBlockTypeIfNotConcrete() != null) {
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
            }
            score += scoreOfField;
        }
        if (amountDoubleWord > 0) score *= (amountDoubleWord * 2);
        if (amountTripleWord > 0) score *= (amountTripleWord * 3);
        return score;

    }

    private String buildId() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields) {
            stringBuilder.append(field.getLetter().getValue())
                    .append("/")
                    .append(field.getCoordinate().getX())
                    .append("/")
                    .append(field.getCoordinate().getY());
        }
        return stringBuilder.toString();
    }

    public String getId() {
        return buildId();
    }

    @Override
    @NonNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields) {
            stringBuilder.append(field.getLetter().getValue());
        }
        return stringBuilder.toString();
    }

}
