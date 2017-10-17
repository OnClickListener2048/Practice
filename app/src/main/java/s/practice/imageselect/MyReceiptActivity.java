package s.practice.imageselect;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import s.practice.R;
import s.practice.newbag.NormalAdpater;

/**
 * Created by dagou on 2017/10/17.
 */

public class MyReceiptActivity extends AppCompatActivity {
    private static final String TAG = "MyReceiptActivity";
    @InjectView(R.id.confirm)
    Button confirm;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int mImageResize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceipt);
        ButterKnife.inject(this);
        GridLayoutManager grid = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        recyclerview.addItemDecoration(new GridInset(3,spacing,false));


        OkGo.<model>get("http://gank.io/api/data/福利/10/1").execute(new JsonCallBack<model>(model.class) {
            @Override
            public void onSuccess(Response<model> response) {
                model body = response.body();
                if (!body.isError()) {
                    setUpData(body.getResults());
                }
            }
        });

    }

    private int getImageResize(Context context) {

        if (mImageResize == 0) {
            RecyclerView.LayoutManager lm = recyclerview.getLayoutManager();
            int spanCount = ((GridLayoutManager) lm).getSpanCount();
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int availableWidth = screenWidth - context.getResources().getDimensionPixelSize(
                    R.dimen.spacing) * (spanCount - 1);
            mImageResize = availableWidth / spanCount;
            mImageResize = (int) (mImageResize * 0.5);
        }
        Log.d(TAG, "getImageResize: "+mImageResize);
        return mImageResize;
    }

    private void setUpData(List<model.ResultsBean> body) {
        NormalAdpater normal = new NormalAdpater(body,getImageResize(this));
        recyclerview.setAdapter(normal);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
    }
}
