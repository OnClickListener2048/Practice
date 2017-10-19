package s.practice.imageselect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import s.practice.R;
import s.practice.newbag.NormalAdpater;

/**
 *
 * @author dagou
 * @date 2017/10/17
 */

public class MyReceiptActivity extends AppCompatActivity implements NormalAdpater.OnImageSelectListener, NormalAdpater.OnImageClickListener {
    private static final String TAG = "MyReceiptActivity";

    public static final int REQUEST_CODE = 1000;
    //    public static final int RESULT_CODE_OK = 2000;
    public static final int RESULT_CODE_BACK = 2000;
//    public static final int RESULT_CODE_DELETE = 2000;

    @InjectView(R.id.confirm)
    Button confirm;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int mImageResize;
    private ArrayList<Image> arrayList;
    private NormalAdpater normal;
    private ArrayList<Image> images;

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
        Log.d(TAG, "setUpData:   private void setUpData(ArrayList<model.ResultsBean> body) {");
        images = new ArrayList<>();
        for (model.ResultsBean result : body) {
            Log.d(TAG, "setUpData:  for (model.ResultsBean result : body) {");
            Image image = new Image();
            image.isSelected = false;
            image.name = result.get_id();
            image.path = result.getUrl();
            image.position = -1;
            images.add(image);
        }
        Log.d(TAG, "setUpData: images.size"+ images.size());
        normal = new NormalAdpater(images, getImageResize(this));
        Log.d(TAG, "setUpData:  normal = new NormalAdpater(images, getImageResize(this));");
        normal.setOnImageSelectListener(this);
        normal.setOnImageClickListener(this);
        recyclerview.setAdapter(normal);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (arrayList != null && arrayList.size() != 0) {
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

    @Override
    public void onImageClick(ArrayList<Image> allItemList, int position) {
        Image image = allItemList.get(position);
        Intent intent = new Intent(this, PreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA, allItemList);
        bundle.putInt(NormalAdpater.EXTRA_CURRENT_POSITION, image.position);
        intent.putExtra(NormalAdpater.EXTRA_BUNDLE, bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_CODE_BACK:
                    Log.d(TAG, "onActivityResult: previewactivity back to myreceiptactivity");
                    Bundle bundleExtra = data.getBundleExtra(NormalAdpater.EXTRA_BUNDLE);
                    ArrayList<Image> images = bundleExtra.getParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA);
                    NormalAdpater normalAdpater = (NormalAdpater) recyclerview.getAdapter();
                    normalAdpater.refresh(images);
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyReceiptDiff(this.images, images), true);
                    diffResult.dispatchUpdatesTo(normalAdpater);
                    break;

                default:
                    break;
            }
        }
    }
}
