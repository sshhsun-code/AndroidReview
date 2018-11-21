package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

import android.os.RemoteException;

import com.review.sunqi.iamss.androidreview.book_test.IOnNewBookArrivedListener;

/**
 * Created by sunqi on 2018/6/2.
 */

public class IBookImpl extends IBook.Stub{
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String getBook(int num) throws RemoteException {
        return "书籍： <<数据结构与算法>>";
    }

    @Override
    public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {

    }

    @Override
    public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

    }
}
