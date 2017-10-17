package s.practice;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dagou on 2017/9/28.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = MyAdapter.class.getSimpleName();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;
    private List list;
    private View mHeaderView;
    private OnItemClickListener mListener;

    public MyAdapter(List list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
//        View view = View.inflate(parent.getContext(), R.layout.recycler_item, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(1, "1");
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
//            mHeaderView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "onClick: I am header");
//                }
//            });
            return;
        }
        final int pos = getRealPosition(holder);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).textView.setText(String.valueOf(list.get(pos)));
        }

        if (mListener == null) return;
        Log.d(TAG, "onBindViewHolder: ");
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: ");
//                if (mListener != null) {
//                    mListener.onItemClick(position, String.valueOf(list.get(pos)));
//                    Log.d(TAG, "onClick: ");
//                }
//            }
//        });
//        ((ViewHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: ");
//                if (mListener != null) {
//                    mListener.onItemClick(position, String.valueOf(list.get(pos)));
//                    Log.d(TAG, "onClick: ");
//                }
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? list.size() : list.size() + 1;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_ITEM;
        if (position == 0) return TYPE_HEADER;
        return TYPE_ITEM;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            textView = (TextView) itemView.findViewById(R.id.textview);
            textView.setTextSize(30);
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.onItemClick(1, "1");
//                }
//            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "onClick: ");
//                }
//            });
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, String data);
    }
}
