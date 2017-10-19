package s.practice.imageselect;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

/**
 *
 * @author edz
 * @date 2017/10/19
 */

public class MyReceiptDiff extends DiffUtil.Callback {
    private ArrayList<Image> olddatas;
    private ArrayList<Image> newDatas;

    public MyReceiptDiff(ArrayList<Image>  olddatas, ArrayList<Image>  newDatas) {
        this.olddatas = olddatas;
        this.newDatas = newDatas;
    }

    @Override
    public int getOldListSize() {
        return olddatas.size();
    }

    @Override
    public int getNewListSize() {
        return newDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return olddatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return olddatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
    }
}
