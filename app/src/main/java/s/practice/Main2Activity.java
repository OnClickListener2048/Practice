package s.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = Main2Activity.class.getSimpleName();
    private ArrayList list = null;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(list);
        setHeader(mRecyclerView);
        mAdapter.setOnItemClickListener(new Main2Activity.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(Main2Activity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        list = new ArrayList();
        for (int i = 0; i <= 20; i++) {
            list.add(i);
        }
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.recycler_header, view, false);
        mAdapter.setHeaderView(header);
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_NORMAL = 1;

        private ArrayList<String> mDatas = new ArrayList<>();

        private View mHeaderView;

        private OnItemClickListener mListener;

        public void setOnItemClickListener(OnItemClickListener li) {
            mListener = li;
        }

        public void setHeaderView(View headerView) {
            mHeaderView = headerView;
            notifyItemInserted(0);
        }

        public View getHeaderView() {
            return mHeaderView;
        }

        public void addDatas(ArrayList<String> datas) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            if (mHeaderView == null) return TYPE_NORMAL;
            if (position == 0) return TYPE_HEADER;
            return TYPE_NORMAL;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new Holder(layout);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_HEADER) return;

            final int pos = getRealPosition(viewHolder);
            final String data = mDatas.get(pos);
            if (viewHolder instanceof Holder) {
                ((Holder) viewHolder).text.setText(data);
                if (mListener == null) return;
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(pos, data);
                    }
                });
            }
        }

        public int getRealPosition(RecyclerView.ViewHolder holder) {
            int position = holder.getLayoutPosition();
            return mHeaderView == null ? position : position - 1;
        }

        @Override
        public int getItemCount() {
            return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
        }

        class Holder extends RecyclerView.ViewHolder {

            TextView text;

            public Holder(View itemView) {
                super(itemView);
                if (itemView == mHeaderView) return;
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }

    }
    interface OnItemClickListener {
        void onItemClick(int position, String data);
    }
}
