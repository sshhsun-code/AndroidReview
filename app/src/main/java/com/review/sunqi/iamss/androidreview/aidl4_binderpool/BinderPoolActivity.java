package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/6/2.
 */

public class BinderPoolActivity extends Activity{

    BinderPool binderPool;
    TextView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);

        binderPool = BinderPool.getInstance(this);
        show = findViewById(R.id.binder_pool_text);

        findViewById(R.id.binder_pool_book_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IBinder bookBinder = binderPool.getIBinder(Constant.BINDER_CODE_BOOK);
                IBook bookImpl = IBookImpl.asInterface(bookBinder);
                try {
                    String book = bookImpl.getBook(0);
                    show.append("\n book = " + book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.binder_pool_student_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IBinder studentBinder = binderPool.getIBinder(Constant.BINDER_CODE_STUDENT);
                IStudent studentImpl = IStudentImpl.asInterface(studentBinder);
                try {
                    String student = studentImpl.getStudent(1);
                    show.append("\n student = " + student);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
