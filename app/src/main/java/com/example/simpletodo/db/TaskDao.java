package com.example.simpletodo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.simpletodo.db.bean.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskDao extends DBHelper{

    private static final String TAG = TaskDao.class.getSimpleName();

    public final static String TABLE_NAME = "tasks";

    public static final class TaskColumns implements BaseColumns {

        public static final String DATE = "date";
        public static final String CONTENT = "content";
        public static final String TASK_ID = "Task_id";
    }

    public final static String[] FIELD_NAMES = {
            TaskColumns.CONTENT,
            TaskColumns.TASK_ID,
            TaskColumns.DATE,
    };

    public final static String[] FIELD_TYPES = {"TEXT","TEXT","TEXT"};

    public TaskDao(Context context) {
        super(context, TABLE_NAME, FIELD_NAMES, FIELD_TYPES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }


    public boolean insertTask(Task task){

        if(task == null){
            return false;
        }

        HashMap<String, Object> data  = new HashMap<String, Object>();

        data.put(TaskColumns.CONTENT, task.getmContent());
        data.put(TaskColumns.TASK_ID, task.getmID());
        data.put(TaskColumns.DATE, task.getmDate());
  

        int rows = (int) this.insert(data);
        return rows > 0;
    }


    public ArrayList<Task> queryTaskList(){

        ArrayList<Task> taskList = new ArrayList<Task>();
        Task task;
        Cursor cursor = this.query();

        if(cursor == null){
            return null;
        }

        try {
            while (cursor.moveToNext()) {
                task = getTaskBeanFromCursor(cursor);
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        Log.d(TAG,"queryTaskList size:"+ taskList.size());

        return taskList;
    }


    public boolean deleteTaskByTaskId(String recorId) {
        int result = delete(TaskColumns.TASK_ID + "=?",
                new String[]{recorId});

        return result > 0;
    }


    public Task getTaskByTaskId(String TaskId) {

        Task Task = null;
        String[] param = new String[1];
        param[0] = TaskId;
        String sql = "select * from "+TABLE_NAME+" where "+ TaskColumns.TASK_ID+" = ? ";
        Cursor cursor = this.query(sql, param);

        if (cursor == null) {
            return null;
        }

        if (cursor.moveToNext()) {
            Task = getTaskBeanFromCursor(cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
        return Task;
    }

    public Task getTaskByAutoId(int autoId) {

        Task Task = null;
        String[] param = new String[1];
        param[0] = autoId+"";
        String sql = "select * from "+TABLE_NAME+" where _id = ? ";
        Cursor cursor = this.query(sql, param);

        if (cursor == null) {
            return null;
        }

        if (cursor.moveToNext()) {
            Task = getTaskBeanFromCursor(cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
        return Task;
    }



    public Task getTaskBeanFromCursor(Cursor cursor) {
        Task Task = new Task();

        Task.setmContent(getString(cursor,TaskColumns.CONTENT));
        Task.setmDate(getString(cursor,TaskColumns.DATE));
        Task.setmID(getString(cursor,TaskColumns.TASK_ID));

        return Task;
    }


    public boolean update(Task task){

        if(task == null){
            return false;
        }

        HashMap<String, Object> data  = new HashMap<String, Object>();

        data.put(TaskColumns.CONTENT, task.getmContent());
        data.put(TaskColumns.TASK_ID, task.getmID());
        data.put(TaskColumns.DATE, task.getmDate());

        long index = update(getContentValues(task), TaskColumns.TASK_ID
                + " = ?", new String[]{task.getmID()});
        return index > 0;
    }



    public ContentValues getContentValues(Task Task) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(TaskColumns.CONTENT, Task.getmContent());
        contentValues.put(TaskColumns.DATE, Task.getmDate());
        contentValues.put(TaskColumns.TASK_ID, Task.getmID());

        return contentValues;
    }
}
