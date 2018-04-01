package com.example.administrator.glidetest.bean;

/**
 * Created by Administrator on 2018/1/27.
 */

public class GameBean {

    private String img;

    private String tv;

    boolean isChecked;

    private int state;

    private int progress;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getImg() {
        return img;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
