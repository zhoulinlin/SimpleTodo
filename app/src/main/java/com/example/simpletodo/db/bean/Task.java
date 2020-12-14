package com.example.simpletodo.db.bean;

public class Task {

    private String mID;
    private String mContent;
    private String mDate;

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "mID='" + mID + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }
}
