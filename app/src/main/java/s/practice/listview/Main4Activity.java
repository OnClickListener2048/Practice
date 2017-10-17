package s.practice.listview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import s.practice.R;
import s.practice.Sha1;

public class Main4Activity extends AppCompatActivity {
    public static final String ACTION_UPDATE_PROGRESS = "action_update_progress";
    public static final String KEY_POSITION = "key_position";
    public static final String KEY_PROGRESS = "key_progress";
    private ListView lv;
    private ArrayList<Job> mData;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int position = intent.getIntExtra(KEY_POSITION, -1);
            int progress = intent.getIntExtra(KEY_PROGRESS, -1);
            if (position != -1 && -1 != progress) {
                Job job = mData.get(position);
                job.progress = progress;
                downLoadAdapter.notifyItemChanged(lv,position);
            }
        }
    };
    private DownLoadAdapter downLoadAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        String s = Sha1.sHA1(this);
        Log.d("main4   ", "onCreate: "+s);

        lv = (ListView) findViewById(R.id.lv);
        downLoadAdapter = new DownLoadAdapter(initData(), this);
        lv.setAdapter(downLoadAdapter);

        IntentFilter intentFilter = new IntentFilter(ACTION_UPDATE_PROGRESS);
        registerReceiver(broadcastReceiver,intentFilter);
        downLoadAdapter.notifyDataSetChanged();
    }

    List<Job> initData() {
        String[] urls = this.getResources().getStringArray(R.array.download_array);
        mData = new ArrayList<>();
        for (String url : urls) {
            Job job = new Job();
            job.name = url.substring(0, 10);
            job.progress = 0;
            job.url = url;
            mData.add(job);
        }
        return mData;
    }
}
