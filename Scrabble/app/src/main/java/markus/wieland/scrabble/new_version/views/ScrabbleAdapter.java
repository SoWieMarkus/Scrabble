package markus.wieland.scrabble.new_version.views;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import markus.wieland.scrabble.new_version.helper.Matrix;

public class ScrabbleAdapter<T extends View> extends BaseAdapter {

    private final Matrix<T> matrix;

    public ScrabbleAdapter(Matrix<T> matrix) {
        this.matrix = matrix;
    }

    @Override
    public int getCount() {
        return matrix.getHeight() * matrix.getWidth();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return matrix.get(i);
    }
}