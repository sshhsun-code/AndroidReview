package com.review.sunqi.iamss.androidreview.thread_test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;

import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * Created by sunqi on 2018/10/19.
 */

public class AsyncTaskTestActitivity extends Activity {

    TextView info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        info = findViewById(R.id.first_activity_info);
        findViewById(R.id.first_jump_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownLoadFile();
            }
        });
    }

    private void startDownLoadFile() {
        String[] params = new String[]{
                "11111111111",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222",
                "222222222222"
        };

        FileDownLoadTask task = new FileDownLoadTask();
        boolean i = task.isRunning;
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);//并行执行

        //task.execute(params);//串行执行
    }


    private class BitmapDownLoadTask extends AsyncTask<URL, Integer, Bitmap> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            return null;
        }
    }

    private class FileDownLoadTask extends AsyncTask<String, Integer, Long> {

        private volatile boolean isRunning = false;

        public boolean isRun() {
            return isRunning;
        }

        @Override
        protected void onPreExecute() {
            isRunning = true;
            super.onPreExecute();
            info.setText("下载开始 ");
            Log.e("sunqi_log","onPreExecute " + Thread.currentThread().getName() + this.toString());
        }



        @Override
        protected void onProgressUpdate(Integer... values) {
            isRunning = true;
            super.onProgressUpdate(values);
            info.setText("下载进度 ： " + values[0] + "%");
//            Log.e("sunqi_log","onProgressUpdate " + Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            info.setText("下载完成！！！");
            Log.e("sunqi_log","onPostExecute " + Thread.currentThread().getName() + this.toString());
            isRunning = false;
        }

        @Override
        protected Long doInBackground(String... urls) {
            isRunning = true;
            Log.e("sunqi_log","doInBackground " + Thread.currentThread().getName() + this.toString());
//            int count = urls.length;
//            long totalSize = 0;
//
//            Log.e("sunqi_log","doInBackground " + Thread.currentThread().getName());
//            for(int i = 0; i < count; i++){
//                try{
//                    Thread.sleep(1000);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//                totalSize += 500;
//                publishProgress(((i+1) / count) * 100);
//            }
//
//            return totalSize;
            int count = 0;
            int length = 1;
            while (count<99) {

                count += length;
                // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                publishProgress(count);
                // 模拟耗时任务
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
