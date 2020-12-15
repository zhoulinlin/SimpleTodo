package com.example.simpletodo.db;

import android.content.Context;

import com.example.simpletodo.db.bean.Task;
import com.example.simpletodo.tools.AppGlobal;

public class DBController {

    private static DBController sInstance;

    private TaskDao mTaskDao;
    

    private DBController() {
        Context applicationContext = AppGlobal.getInstance().getApplicationContext();
        mTaskDao = new TaskDao(applicationContext);
    }

    public static DBController getInstance() {
        if (sInstance == null) {
            synchronized (DBController.class) {
                if (sInstance == null) {
                    sInstance = new DBController();
                }
            }
        }
        return sInstance;
    }


    public boolean deleteTask(String taskID){
        return mTaskDao.deleteTaskByTaskId(taskID);
    }

    public boolean addTask(Task ta){
        return mTaskDao.insertTask(ta);
    }

    public boolean updateTask(Task ta){
        return mTaskDao.update(ta);
    }

    public TaskDao getTaskDao() {
        return mTaskDao;
    }

}
