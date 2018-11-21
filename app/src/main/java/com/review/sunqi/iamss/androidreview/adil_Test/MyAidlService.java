package com.review.sunqi.iamss.androidreview.adil_Test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.review.sunqi.iamss.androidreview.adil_Test.bean.IMyAidl;
import com.review.sunqi.iamss.androidreview.adil_Test.bean.Person;
import com.review.sunqi.iamss.androidreview.book_test.Book;
import com.review.sunqi.iamss.androidreview.book_test.IOnNewBookArrivedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunqi on 2018/5/23.
 */

public class MyAidlService extends Service {

    private ArrayList<Person> mPersons;

    private ArrayList<IOnNewBookArrivedListener> listeners = new ArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> listenerList = new RemoteCallbackList<>();

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

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            Log.e("sunqi_log", "Service 注册 listener 实体: " + listener);
            Log.e("sunqi_log", "Service 注册 listener : " + listener.asBinder());
            listenerList.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            Log.e("sunqi_log", "Service 反注册 listener : " + listener.asBinder());
            listenerList.unregister(listener);
        }
    };

    private class NewBook implements Runnable {

        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(2000);
                    int size = listenerList.beginBroadcast();

                    for (int i = 0; i < size; i ++) {
                        IOnNewBookArrivedListener listener = listenerList.getBroadcastItem(i);
                        listener.onNewBookArrived(new Book(1, "" + System.currentTimeMillis()));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                listenerList.finishBroadcast();
            }
        }
    }


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

        NewBook newBook = new NewBook();
        new Thread(newBook).start();
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
