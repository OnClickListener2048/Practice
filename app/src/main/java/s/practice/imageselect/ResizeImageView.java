package s.practice.imageselect;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by dagou on 2017/10/17.
 */

public class ResizeImageView extends ImageView {

    public ResizeImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
