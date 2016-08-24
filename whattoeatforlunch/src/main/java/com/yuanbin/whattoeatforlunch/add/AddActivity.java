package com.yuanbin.whattoeatforlunch.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanbin.whattoeatforlunch.R;
import com.yuanbin.whattoeatforlunch.utils.L;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;

    private String[] items = new String[]{"黄焖鸡","排骨饭","汉堡","鸡腿饭","拉面","不吃了"};

    private ItemAdapter itemAdapter;
    private EditText et_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        findViewById(R.id.btn_confirm_add).setOnClickListener(this);
        et_item = (EditText) findViewById(R.id.et_item);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter = new ItemAdapter());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm_add:
                //
                String name = et_item.getText().toString();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(this,"不能为空啊",Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<String> list = new ArrayList<>();
                for (String item:items) {
                    if (item.equals(name)){
                        Toast.makeText(this,"已经加过了",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    list.add(item);
                }
                list.add(name);
                items = list.toArray(items);
                for (String item:items) {
                    L.e(this,"现在的：" + item);
                }
                itemAdapter.notifyDataSetChanged();
                break;
        }
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

        @Override
        public ItemAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            ItemAdapter.ItemHolder itemHolder= new ItemHolder(View.inflate(AddActivity.this,R.layout.layout_item,parent));
            ItemAdapter.ItemHolder itemHolder= new ItemHolder(LayoutInflater.from(AddActivity.this).inflate(R.layout.layout_item,parent,false));
            return itemHolder;
        }

        @Override
        public void onBindViewHolder(ItemAdapter.ItemHolder holder, int position) {
            holder.tv.setText(items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }
        class ItemHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public ItemHolder(View view){
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
