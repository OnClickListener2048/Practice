package s.practice.newbag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import s.practice.R;

public class Main3Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.ItemDecoration mDividerItemDecoration;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<ObjectModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
//        mDividerItemDecoration = new s.practice.newbag.DividerItemDecoration(this, s.practice.newbag.DividerItemDecoration.VERTICAL_LIST);
//        mLinearLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mRecyclerView.addItemDecoration(mDividerItemDecoration);
//
//        NormalAdpater noHeaderAdapter = new NormalAdpater(mData = initData());
//        NormalAdapterWrapper normalAdapterWrapper = new NormalAdapterWrapper(noHeaderAdapter);

        View headerView = LayoutInflater.from(this).inflate(R.layout.recycler_header, mRecyclerView, false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.recycler_header, mRecyclerView, false);

//        normalAdapterWrapper.addHeaderView(headerView);
//        normalAdapterWrapper.addFooterView(footerView);
//        mRecyclerView.setAdapter(normalAdapterWrapper);
    }

    private ArrayList<ObjectModel> initData() {
        ArrayList<ObjectModel> arrayList = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            ObjectModel objectModel = new ObjectModel();
            objectModel.number = i+1;
            arrayList.add(objectModel);
        }
        return arrayList;
    }
}
