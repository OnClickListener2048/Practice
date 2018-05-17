package s.practice.imageselect;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.lljjcoder.style.citypickerview.CityPickerView;
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
        Utils.init(this);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        CityPickerView.getInstance().init(this);

        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("1182180517253756#pilipa");//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId("54837");//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”

        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)){
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);
        //后面可以设置其他属性
    }
}
