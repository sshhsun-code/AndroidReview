package com.review.sunqi.iamss.androidreview.ashmem_test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;
import com.review.sunqi.iamss.androidreview.adil_Test.MyAidlService;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

/**
 * Created by sunqi on 2018/6/22.
 */

public class AshmemActivity extends Activity {
    ParcelFileDescriptor fileDescriptor;
    IMemoryService memoryService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            memoryService = IMemoryService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ashmem_show);
        textView = findViewById(R.id.show_file_content);
        findViewById(R.id.get_file_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileContent();
            }
        });

        Intent intent1 = new Intent(getApplicationContext(), AshmemService.class);
        bindService(intent1, connection, BIND_AUTO_CREATE);
    }



    private void showFileContent() {
        if (memoryService != null) {
            try {
                byte[] cache = new byte[20 * 1024];
                fileDescriptor = memoryService.getFileDescriptor();
                FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
                inputStream.read(cache);
                String result = new String(cache, "UTF-8");
                inputStream.close();
                textView.setText(result);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
