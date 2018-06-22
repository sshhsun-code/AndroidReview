package com.review.sunqi.iamss.androidreview.ashmem_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.review.sunqi.iamss.androidreview.adil_Test.bean.IMyAidl;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * Created by sunqi on 2018/6/22.
 */

public class AshmemService extends Service {

    MemoryFile memoryFile;

    private IBinder mIBinder = new IMemoryService.Stub() {
        @Override
        public ParcelFileDescriptor getFileDescriptor() throws RemoteException {

            ParcelFileDescriptor pfd = null;

            try {

                Method method = MemoryFile.class.getDeclaredMethod("getFileDescriptor");
                FileDescriptor fd = (FileDescriptor) method.invoke(memoryFile);
                pfd = ParcelFileDescriptor.dup(fd);
            } catch(Exception ex) {
                ex.printStackTrace();
            }

            return pfd;
        }

        @Override
        public void setValue(int val) throws RemoteException {

        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            memoryFile = new MemoryFile("shared_file", 20 * 1024);
            OutputStream outputStream = memoryFile.getOutputStream();
            String text ="1024";
            try {
                byte[] bytes = text.getBytes("UTF-8");
                Log.e("sunqi_log", "length : " + bytes.length);
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
