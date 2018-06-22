package com.review.sunqi.iamss.androidreview.adil_Test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.review.sunqi.iamss.androidreview.adil_Test.bean.IMyAidl;
import com.review.sunqi.iamss.androidreview.adil_Test.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunqi on 2018/5/23.
 */

public class MyAidlService extends Service {

    private ArrayList<Person> mPersons;

    private IBinder mIBinder = new IMyAidl.Stub() {

        @Override
        public void addPerson(Person person) throws RemoteException {
            Log.e("sunqi_log", "Service addPerson :" + person);
            Log.e("sunqi_log", "Service Person Class :" + person.getClass());
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }

        @Override
        public Person getPerson() throws RemoteException {
            return null;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        Log.e("sunqi_log", "MyAidlService onBind");
        return mIBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("sunqi_log", "MyAidlService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("sunqi_log", "MyAidlService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("sunqi_log", "MyAidlService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("sunqi_log", "MyAidlService onDestroy");
        super.onDestroy();
    }
}
