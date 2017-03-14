package com.junior.myapplication.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.junior.myapplication.interfaces.OnFinishGetDatasListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ContentManager {
    private static final String TAG = "ContentManager";
    private final ProgressDialog progressDialog;

    private Context context;
    private String elements;

    private OnFinishGetDatasListener onFinishGetDatasListener;
    private String content;

    public ContentManager(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Đợi tý...");
    }

    public void parseStories(final String url) {
        new GetContentAsyncTask().execute(url);
    }

    private class GetContentAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                onFinishGetDatasListener.onSuccess();

            } else {
                onFinishGetDatasListener.onFail();
            }
            progressDialog.dismiss();
            super.onPostExecute(aBoolean);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            delay(500);
            try {
                Document document = Jsoup.connect(strings[0]).get();
                content = document.select("div.entry-content").text();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setOnFinishGetDatasListener(OnFinishGetDatasListener onFinishGetDatasListener) {
        this.onFinishGetDatasListener = onFinishGetDatasListener;
    }

    public String getContent() {
        return content;
    }
}