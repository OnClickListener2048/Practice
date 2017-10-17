package s.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    private List list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.inject(this);

        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        MyAdapter myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setHeaderView(LayoutInflater.from(this).inflate(R.layout.recycler_header,recyclerView));



        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

    }

    private void initData() {
        list = new ArrayList();
        for (int i = 0; i <= 20; i++) {
            list.add(i);
        }
    }
}
