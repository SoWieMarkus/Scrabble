package markus.wieland.scrabble.old_versiob.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ScrabbleHandAdapter extends BaseAdapter {

    private final List<markus.wieland.scrabble.new_version.views.LetterView> letters;

    public ScrabbleHandAdapter(List<markus.wieland.scrabble.new_version.views.LetterView> letters) {
        this.letters = letters;
    }

    @Override
    public int getCount() {
        return letters.size();
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
        return letters.get(i);
    }
}
