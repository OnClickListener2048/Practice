package s.practice.imageselect;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewViewpager extends ViewPager {
    public PreviewViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ImageViewTouch) {
            return ((ImageViewTouch) v).canScroll(dx) || super.canScroll(v, checkV, dx, x, y);
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}
