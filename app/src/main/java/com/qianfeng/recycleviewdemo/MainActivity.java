package com.qianfeng.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class MainActivity extends AppCompatActivity {
    private List<String> data = new ArrayList<>();
    private RecyclerView recyclerView;
    int i = 0;
    private MyAdapter adapter;

    private void initData() {
        for (int i = 0; i < 5; i++) {
            data.add("志玲" + i);
        }
    }

    public void click(View view){
        data.add(0,"凤姐" + i++);

        adapter.notifyItemInserted(0);
        adapter.notifyItemRangeChanged(1, data.size() - 1);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        /*//创建线性布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);*/
        //参数二：网格的列数
        GridLayoutManager manager = new GridLayoutManager(this, 2);

       /* StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        */
        //设置布局管理器
        recyclerView.setLayoutManager(manager);
        //创建自定义的Adapter对象
        adapter = new MyAdapter();
        //设置Adapter
        recyclerView.setAdapter(adapter);

        //给RecyclerView的Item间添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //给Item的添加、删除、移动 设置动画效果
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemAnimator(new ScaleInAnimator());
        recyclerView.getItemAnimator().setAddDuration(1000);




    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        //创建ViewHolder并返回ViewHolder对象
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //加载Item布局。  这里使用的系统的Layout文件
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_layout, parent, false);
            //创建自定义的ViewHolder对象。并把Item布局添加传入。
            ViewHolder holder = new ViewHolder(view);
            //返回自定义ViewHolder对象
            return holder;
        }

        //给Adapter绑定ViewHolder.  给ViewHolder中的控件绑定数据
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //给布局文件中的TextView绑定数据
            holder.tv.setText(data.get(position));
        }

        //要展示的Itemt的数量
        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            //每个item的View对象
            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text1);

            }
        }
    }

}
