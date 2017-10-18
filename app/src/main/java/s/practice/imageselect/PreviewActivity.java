package s.practice.imageselect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import s.practice.R;
import s.practice.newbag.NormalAdpater;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.preview_viewpager)
    PreviewViewpager previewViewpager;
    private ArrayList<Image> allList;
    private int currentPosition;
    protected int mPreviousPos = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceipt_preview);
        ButterKnife.inject(this);
        Bundle bundleExtra = getIntent().getBundleExtra(NormalAdpater.EXTRA_BUNDLE);
        allList = bundleExtra.getParcelableArrayList(NormalAdpater.EXTRA_ALL_DATA);
        currentPosition = bundleExtra.getInt(NormalAdpater.EXTRA_CURRENT_POSITION);
        PreviewPagerAdapter previewPagerAdapter = new PreviewPagerAdapter(getSupportFragmentManager());
        previewPagerAdapter.addAll(allList);
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
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
