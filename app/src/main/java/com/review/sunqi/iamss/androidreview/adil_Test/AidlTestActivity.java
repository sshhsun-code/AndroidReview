package com.review.sunqi.iamss.androidreview.adil_Test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;
import com.review.sunqi.iamss.androidreview.adil_Test.bean.IMyAidl;
import com.review.sunqi.iamss.androidreview.adil_Test.bean.Person;
import com.review.sunqi.iamss.androidreview.book_test.Book;
import com.review.sunqi.iamss.androidreview.book_test.IOnNewBookArrivedListener;

import java.util.ArrayList;

/**
 * Created by sunqi on 2018/5/22.
 */

public class AidlTestActivity extends Activity {

    private IMyAidl mAidl;
    private Button addPersonBtn;
    private TextView showPersonText;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidl.Stub.asInterface(service);
            Log.e("sunqi_log", "MyAidlService onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
            Log.e("sunqi_log", "MyAidlService onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);
        bindMyService();
        showPersonText = findViewById(R.id.text_show_person);
        findViewById(R.id.btn_add_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAidl != null) {
                    try {
                        Person new_person = new Person("张三", 18);
                        mAidl.addPerson(new_person);
                        Log.e("sunqi_log", "Activity Person Class :" + new_person.getClass());
                        ArrayList<Person> list = (ArrayList<Person>) mAidl.getPersonList();
                        for (Person person : list) {
                            showPersonText.append("\n" + person);
                        }
                        Log.e("sunqi_log", "Activity 注册 listener : " + listener.asBinder());
                        mAidl.registerListener(listener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.e("sunqi_log", newBook.toString());
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            Log.e("sunqi_log", "Activity 反注册 listener : " + listener.asBinder());
            mAidl.unRegisterListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unBindMyService();
        listener = null;
        mAidl = null;
    }

    private void bindMyService() {
        Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
        bindService(intent1, mConnection, BIND_AUTO_CREATE);
    }

    private void unBindMyService() {
        unbindService(mConnection);
    }
}
