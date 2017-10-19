package s.practice.imageselect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Image> images = new ArrayList<>();
    ArrayList<PreviewImageFragment> arrayList;

    public PreviewPagerAdapter(FragmentManager fm, ArrayList<PreviewImageFragment> fragmentList) {
        super(fm);
        arrayList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

    }

    public void addAll(ArrayList<Image> items) {
        images.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void remove(int mPreviousPos) {
        if (arrayList != null && arrayList.size() != 0) {
            arrayList.remove(mPreviousPos);
            notifyDataSetChanged();
        }


    }
}
