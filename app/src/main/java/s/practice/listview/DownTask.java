package s.practice.listview;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dagou on 2017/9/30.
 */

public class DownTask extends AsyncTask<String, Integer, Integer> {
    private Context mContext;
    private int position;

    public DownTask(Context mContext, int position) {
        this.mContext = mContext;
        this.position = position;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        URL url = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            int totalLength = httpURLConnection.getContentLength();
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            int currentLength = 0;
            while ((length = inputStream.read(buffer))!= -1){
                byteArrayOutputStream.write(buffer,0,length);
                currentLength+=length;
                int progress = (int)((((double)currentLength) / totalLength) * 100);
                publishProgress(progress);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 100;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Intent intent = new Intent();
        intent.putExtra(Main4Activity.KEY_POSITION, position);
        intent.putExtra(Main4Activity.KEY_PROGRESS, values[0]);
        intent.setAction(Main4Activity.ACTION_UPDATE_PROGRESS);
        mContext.sendBroadcast(intent);
    }
}
