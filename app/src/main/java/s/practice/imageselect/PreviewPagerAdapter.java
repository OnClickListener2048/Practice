package s.practice.imageselect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "PreviewPagerAdapter";
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
    public long getItemId(int position) {
        if (arrayList.size() == position) {
            return arrayList.get(arrayList.size() - 1).hashCode();
        }
        int hashcode = arrayList.get(position).hashCode();
        return hashcode;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }



    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public void remove(int mPreviousPos) {
        if (arrayList != null && arrayList.size() != 0) {
            arrayList.remove(arrayList.get(mPreviousPos));
            Log.d(TAG, "remove: mPreviousPos"+mPreviousPos);
            notifyDataSetChanged();
        }


    }
}
