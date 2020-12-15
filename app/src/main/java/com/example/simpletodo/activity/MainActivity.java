package com.example.simpletodo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simpletodo.R;
import com.example.simpletodo.adapter.MyAdapter;
import com.example.simpletodo.db.DBController;
import com.example.simpletodo.db.bean.Task;
import com.example.simpletodo.tools.CommonTools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mainList;
    private MyAdapter mAdapter;
    private Button mBtnAddItem;
    private EditText etInput;

    private List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainList = findViewById(R.id.lvTaskList);
        mBtnAddItem = findViewById(R.id.btnAddItem);
        etInput = findViewById(R.id.etInputTask);

        mTasks = DBController.getInstance().getTaskDao().queryTaskList();
        mAdapter = new MyAdapter(mTasks,MainActivity.this);
        mainList.setAdapter(mAdapter);


        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditItemActivity.actionStart(MainActivity.this,mTasks.get(position).getmID());
            }
        });

        mainList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

              if(DBController.getInstance().deleteTask(mTasks.get(position).getmID())){
                   mTasks.remove(position);
                  mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this,"Delete failed!",Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        mBtnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etInput.getText().toString();
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(MainActivity.this,"Task content is empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                Task ta = new Task();
                ta.setmContent(content);
                ta.setmID(CommonTools.getRandomId());
                ta.setmDate(Calendar.getInstance().getTime().toString());

                if(DBController.getInstance().addTask(ta)){
                    mTasks.add(ta);
                    mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this,"Add failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTasks = DBController.getInstance().getTaskDao().queryTaskList();
        mAdapter.setmData(mTasks);
        mAdapter.notifyDataSetChanged();

    }
}