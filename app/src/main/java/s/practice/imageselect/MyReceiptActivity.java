package s.practice.imageselect;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import s.practice.R;
import s.practice.newbag.NormalAdpater;

/**
 * Created by dagou on 2017/10/17.
 */

public class MyReceiptActivity extends AppCompatActivity implements NormalAdpater.OnImageSelectListener {
    private static final String TAG = "MyReceiptActivity";
    @InjectView(R.id.confirm)
    Button confirm;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int mImageResize;
    private ArrayList<Image> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceipt);
        ButterKnife.inject(this);
        arrayList = new ArrayList<>();
        GridLayoutManager grid = new GridLayoutManager(this, 3);
        recyclerview.setLayoutManager(grid);
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing);
        recyclerview.addItemDecoration(new GridInset(3, spacing, false));


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
        Log.d(TAG, "getImageResize: " + mImageResize);
        return mImageResize;
    }

    private void setUpData(ArrayList<model.ResultsBean> body) {
        NormalAdpater normal = new NormalAdpater(body, getImageResize(this));
        normal.setOnImageSelectListener(this);
        recyclerview.setAdapter(normal);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (arrayList != null) {
            Log.d(TAG, "onViewClicked: "+arrayList.get(0).toString());
            Toast.makeText(this, arrayList.get(0).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onImageSelect(Image image) {
        Log.d(TAG, "onImageSelect: ");
        if (arrayList != null) {
            Log.d(TAG, "onImageSelect:arrayList != null ");
            if (image.isSelected) {
                Log.d(TAG, "onImageSelect: add");
                arrayList.add(image);
            } else {
                if (arrayList.contains(image)) {

                    arrayList.remove(image);
                    Log.d(TAG, "onImageSelect: remove");
                }
            }
        }
    }
}
