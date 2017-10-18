package s.practice.imageselect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/18.
 */

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Image> images = new ArrayList<>();

    public PreviewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PreviewImageFragment.newInstance(images.get(position));
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
}
