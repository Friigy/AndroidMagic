package com.example.myapplication.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class HandlerTasksRandom extends HandlerThread {
    private Handler mWorkerHandler;

    public HandlerTasksRandom(String name) {
        super(name);
    }

    @Override
    public void onLooperPrepared() {
        mWorkerHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
            }
        };
    }

    public void postTask(Runnable task) {
        mWorkerHandler.post(task);
    }
}
