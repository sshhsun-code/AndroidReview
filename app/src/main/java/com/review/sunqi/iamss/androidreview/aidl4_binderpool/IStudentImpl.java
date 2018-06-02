package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

import android.os.RemoteException;

/**
 * Created by sunqi on 2018/6/2.
 */

public class IStudentImpl extends IStudent.Stub{
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public String getStudent(int index) throws RemoteException {
        return "学生：张三";
    }
}
