package s.practice.imageselect;

import android.app.Application;

import com.lzy.okgo.OkGo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by dagou on 2017/10/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

    }
}
