package markus.wieland.scrabble.validation;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import markus.wieland.scrabble.R;
import markus.wieland.scrabble.new_version.board.Field;

@AllArgsConstructor
public class IllegalMultipleRowColumnCheck implements Validation {

    private final List<Field> newLetters;

    @Override
    public ValidationResult validate() {
        List<Integer> positionsVertical = new ArrayList<>();
        List<Integer> positionsHorizontal = new ArrayList<>();

        for (Field field : newLetters) {
            positionsHorizontal.add(field.getCoordinate().getX());
            positionsVertical.add(field.getCoordinate().getY());
        }

        boolean isInOneRow = hasListOnlyIdenticalValues(positionsHorizontal);
        boolean isInOneColumn = hasListOnlyIdenticalValues(positionsVertical);

        return isInOneRow || isInOneColumn
                ? new ValidationResult()
                : new ValidationResult.NonValidResult(R.string.error_message_not_in_one_line);
    }

    private boolean hasListOnlyIdenticalValues(List<Integer> positions) {
        if (positions.isEmpty()) return false;
        if (positions.size() == 1) return true;

        int x = positions.get(0);
        for (Integer integer : positions) {
            if (x != integer) return false;
        }
        return true;
    }
}
