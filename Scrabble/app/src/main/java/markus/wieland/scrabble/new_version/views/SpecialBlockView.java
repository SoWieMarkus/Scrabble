package markus.wieland.scrabble.new_version.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import markus.wieland.scrabble.R;
import markus.wieland.scrabble.game.SpecialBlockType;

public class SpecialBlockView extends View {

    private SpecialBlockType specialBlockType;

    public SpecialBlockView(Context context) {
        this(context, null);
    }

    public SpecialBlockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpecialBlockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        this.specialBlockType = SpecialBlockType.DEFAULT;
    }

    public void set(SpecialBlockType specialBlockType) {
        this.specialBlockType = specialBlockType;
        invalidate();
    }

    @Override
    protected void onMeasure(int width, int height) {
        //noinspection SuspiciousNameCombination
        super.onMeasure(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (specialBlockType == null) throw new IllegalStateException();

        draw(specialBlockType.getDrawable(), canvas);
        draw(R.drawable.border, canvas);
    }

    private void draw(@DrawableRes int drawableRes, Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableRes);
        assert drawable != null;
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

}
