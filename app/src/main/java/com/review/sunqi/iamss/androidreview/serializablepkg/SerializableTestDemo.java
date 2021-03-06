package com.review.sunqi.iamss.androidreview.serializablepkg;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.review.sunqi.iamss.androidreview.R;
import com.review.sunqi.iamss.androidreview.parcelable.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sunqi on 2018/5/16.
 */

public class SerializableTestDemo extends Activity {
        File cacheFile;
        TestReceiver mReceiver;
        IntentFilter mIntentFilter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializa_test);
        initData();
        cacheFile = new File("/sdcard/cache.txt");
        if (cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        findViewById(R.id.serializable_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSerialUserInfo();
                printSerialCacheInfo();
            }
        });
        findViewById(R.id.parcelable_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(18, "张三");
                Intent intent = new Intent("action_test_receiver");
                intent.putExtra("student", student);
                SerializableTestDemo.this.sendBroadcast(intent);
            }
        });
    }

    private void initData() {
        mReceiver = new TestReceiver();
        mIntentFilter = new IntentFilter("action_test_receiver");
        this.registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mReceiver);
    }

    private void saveSerialUserInfo() {
        User user = new User(26, "李四");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile));
            out.writeObject(user);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSerialCacheInfo() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(cacheFile));
            User user1 = (User) inputStream.readObject();
            inputStream.close();
            Toast.makeText(SerializableTestDemo.this, "useInfo = " + user1, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    class TestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("action_test_receiver")) {
                Student student = intent.getParcelableExtra("student");
                Toast.makeText(context, "student = " + student.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
