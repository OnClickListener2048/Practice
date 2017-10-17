package s.practice.listview;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.List;

import s.practice.R;

/**
 * Created by dagou on 2017/9/30.
 */

public class DownLoadAdapter extends BaseAdapter {
    private List<Job> list;
    private Context mContext;

    public DownLoadAdapter(List<Job> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        VH vh = null;
        final Job job = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.download_item, null);
            vh = new VH();
            vh.btn = view.findViewById(R.id.btn);
            vh.numberProgressBar = view.findViewById(R.id.npb);
            view.setTag(vh);
        } else vh = (VH) view.getTag();
        vh.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownTask(view.getContext(), i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, job.url);
            }
        });
        if (job.progress == 100) {
            vh.btn.setText("完成");
        } else {
            vh.btn.setText("下载");
        }
        vh.numberProgressBar.setProgress(job.progress);
        return view;
    }

    public void notifyItemChanged(ListView listView, int position) {
        int firstpos = listView.getFirstVisiblePosition();
        int lastpos = listView.getLastVisiblePosition();
        Job job = list.get(position);
        if (position >= firstpos && position <= lastpos) {
            View view = listView.getChildAt(position - firstpos);
            DownLoadAdapter.VH vh = (VH) view.getTag();
            vh.numberProgressBar.setProgress(job.progress);
            if (job.progress == 100) {
                vh.btn.setText("完成");
            }
        }
    }

    static class VH{
        Button btn;
        NumberProgressBar numberProgressBar;
    }
}
