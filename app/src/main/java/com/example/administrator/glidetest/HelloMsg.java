package com.example.administrator.glidetest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by moge on 2018/6/14.
 */

public class HelloMsg implements Parcelable {

    private String msg;

    private int pid;

    public HelloMsg(String msg){
        this.msg=msg;
    }

    protected HelloMsg(Parcel in) {
        msg = in.readString();
        pid = in.readInt();
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public static final Creator<HelloMsg> CREATOR = new Creator<HelloMsg>() {
        @Override
        public HelloMsg createFromParcel(Parcel in) {
            return new HelloMsg(in);
        }

        @Override
        public HelloMsg[] newArray(int size) {
            return new HelloMsg[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeInt(pid);
    }
}
