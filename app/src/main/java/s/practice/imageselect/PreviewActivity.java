package s.practice.imageselect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import s.practice.R;
import s.practice.newbag.NormalAdpater;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "PreviewActivity";

    @InjectView(R.id.preview_viewpager)
    PreviewViewpager previewViewpager;
    @InjectView(R.id.back)
    TextView back;
    @InjectView(R.id.click)
    TextView click;
    @InjectView(R.id.delete)
    TextView delete;
    private ArrayList<Image> allList;
    private int currentPosition;
    protected int mPreviousPos = -1;
    private PreviewPagerAdapter previewPagerAdapter;
    private ArrayList<PreviewImageFragment> FragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceipt_preview);
        ButterKnife.inject(this);
        Bundle bundleExtra = getIntent().getBundleExtra(NormalAdpater.EXTRA_BUNDLE);
        allList = bundleExtra.getParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA);
        FragmentList  = new ArrayList<>();
        for (Image image : allList) {
            Log.d(TAG, "onCreate: "+image.position);
            FragmentList.add(PreviewImageFragment.newInstance(image));
        }
        currentPosition = bundleExtra.getInt(NormalAdpater.EXTRA_CURRENT_POSITION);
        mPreviousPos = currentPosition;
        if (allList.get(currentPosition).isSelected) {
            click.setText("已选中");
        } else {
            click.setText("未选中");
        }
        previewPagerAdapter = new PreviewPagerAdapter(getSupportFragmentManager(),FragmentList);
        previewViewpager.setAdapter(previewPagerAdapter);
        previewViewpager.setCurrentItem(currentPosition);
        previewViewpager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PreviewPagerAdapter adapter = (PreviewPagerAdapter) previewViewpager.getAdapter();
        if (mPreviousPos != -1 && mPreviousPos != position) {
            ((PreviewImageFragment) adapter.instantiateItem(previewViewpager, mPreviousPos)).resetView();
        }
        mPreviousPos = position;
        if (allList.get(position).isSelected) {
            click.setText("已选中");
        } else {
            click.setText("未选中");
        }
        Log.d(TAG, "onPageSelected: mPreviousPos" + mPreviousPos);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.back, R.id.click, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA, allList);
                intent.putExtra(NormalAdpater.EXTRA_BUNDLE, bundle);
                setResult(MyReceiptActivity.RESULT_CODE_BACK, intent);
                Log.d(TAG, "onViewClicked: allList.get(mPreviousPos).isSelected"+allList.get(mPreviousPos).isSelected);
                finish();
                break;
            case R.id.click:
                if (click.getText().equals("已选中")) {
                    click.setText("未选中");
                    allList.get(mPreviousPos).isSelected = false;
                } else if (click.getText().equals("未选中")) {
                    click.setText("已选中");
                    allList.get(mPreviousPos).isSelected = true;
                }

                Log.d(TAG, "onViewClicked:    allList.get(mPreviousPos).isSelected ----" + allList.get(mPreviousPos).isSelected);
                break;
            case R.id.delete:
                Log.d(TAG, "onViewClicked: mPreviousPos"+mPreviousPos);
                allList.remove(mPreviousPos);
                previewPagerAdapter.remove(mPreviousPos);
                break;
            default:
                break;
        }
    }
}
