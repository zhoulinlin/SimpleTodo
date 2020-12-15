package com.example.simpletodo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpletodo.R;
import com.example.simpletodo.db.DBController;
import com.example.simpletodo.db.bean.Task;

public class EditItemActivity extends AppCompatActivity {

    private static final String TASK_ID ="task_id";
    private String mRecordId;
    private Task mTask;

    private EditText etInput;
    private Button btnSave;

    public static void actionStart(Context context, String tastID) {
        Intent intent = new Intent(context, EditItemActivity.class);
        intent.putExtra(TASK_ID,tastID);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecordId = getIntent().getStringExtra(TASK_ID);
        mTask = DBController.getInstance().getTaskDao().getTaskByTaskId(mRecordId);

        etInput = findViewById(R.id.etTaskContent);
        etInput.setText(mTask.getmContent());


        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(etInput.getText().toString())){
                    if(etInput.getText().toString().equals(mTask.getmContent())){
                        Toast.makeText(EditItemActivity.this,"Update Success!",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        mTask.setmContent(etInput.getText().toString());
                        if(DBController.getInstance().updateTask(mTask)){
                            Toast.makeText(EditItemActivity.this,"Update Success!",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(EditItemActivity.this,"Update Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(EditItemActivity.this,"Task Content is Empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
