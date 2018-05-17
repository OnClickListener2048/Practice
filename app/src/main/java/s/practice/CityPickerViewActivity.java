package s.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

/**
 * Created by edz on 2017/12/18.
 */

public class CityPickerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker);


        Button btn = (Button) findViewById(R.id.showCityPickerView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityPickerView.getInstance().showCityPicker(CityPickerViewActivity.this);
            }
        });



        CityConfig cityConfig = new CityConfig.Builder(CityPickerViewActivity.this).title("选择城市")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜色
                .titleBackgroundColor("#E9E9E9")//标题栏背景色
                .confirTextColor("#585858")//确认按钮文字颜色
                .confirmText("确定")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#585858")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(4)//显示item的数量
                .province("天津")//默认显示的省份
                .city("天津")//默认显示省份下面的城市
                .district("和平区")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .build();//自定义item布局里面的textViewid

//设置自定义的属性配置
        CityPickerView.getInstance().setConfig(cityConfig);
    }
}
