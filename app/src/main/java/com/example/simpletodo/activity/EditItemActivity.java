package com.example.simpletodo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodo.R;
import com.example.simpletodo.db.DBController;
import com.example.simpletodo.db.bean.Task;

public class EditItemActivity extends AppCompatActivity {

    private static final String TASK_ID ="task_id";
    private String mRecordId;
    private Task mTask;

    private EditText edi;

    public static void actionStart(Context context, String tastID) {
        Intent intent = new Intent(context, EditItemActivity.class);
        intent.putExtra(TASK_ID,tastID);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mRecordId = getIntent().getStringExtra(TASK_ID);

        mTask = DBController.getInstance().getTaskDao().getTaskByTaskId(mRecordId);


    }

}
