package s.practice.newbag;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dagou on 2017/9/29.
 */

public class NormalAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "NormalAdapterWrapper";
    private View mHeaderView;
    private View mFooterView;

    enum ITEM_TYPE {
        HEADER,
        FOOTER,
        NORMAL
    }

    NormalAdpater normalAdpater;

    public NormalAdapterWrapper(NormalAdpater normalAdpater) {
        this.normalAdpater = normalAdpater;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER.ordinal();
        } else if (position == normalAdpater.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER.ordinal()) {
            Log.d(TAG, "onCreateViewHolder: HEADER");
            return new RecyclerView.ViewHolder(mHeaderView) {};
        } else if (viewType == ITEM_TYPE.FOOTER.ordinal()) {
            Log.d(TAG, "onCreateViewHolder: FOOTER");
            return new RecyclerView.ViewHolder(mFooterView) {};
        } else {
            Log.d(TAG, "onCreateViewHolder: NORMAL");
           return normalAdpater.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            return;
        } else if(position == normalAdpater.getItemCount() + 1){
            return;
        } else{
            Log.d(TAG, "onBindViewHolder: position==="+position);
            normalAdpater.onBindViewHolder((NormalAdpater.VH)holder, position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return normalAdpater.getItemCount() + 2;
    }

    public void addHeaderView(View view) {
        this.mHeaderView = view;
    }

    public void addFooterView(View view) {
        this.mFooterView = view;
    }
}
