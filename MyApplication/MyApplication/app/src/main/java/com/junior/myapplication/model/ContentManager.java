package com.junior.myapplication.model;

import android.content.Context;
import android.content.Intent;

import com.junior.myapplication.interfaces.OnFinishGetDatasListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ContentManager {
    private static final String TAG = "ContentManager";

    private Context context;
    private String elements;

    private OnFinishGetDatasListener onFinishGetDatasListener;
    private String content;

    public ContentManager(Context context) {
        this.context = context;
    }

    public void parseStories(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    content = document.select("div.entry-content").text();

                    onFinishGetDatasListener.onSuccess();
                    //sendStoriesLoadedRequest();
                } catch (IOException e) {
                    onFinishGetDatasListener.onFail();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void parseStory(final String url) {
        // TODO
    }

    public void sendStoriesLoadedRequest() {
        Intent intent = new Intent();
        intent.putExtra("content",elements);

        intent.setAction("action_stories_loaded_content");
        context.sendBroadcast(intent);
    }


    public void setOnFinishGetDatasListener(OnFinishGetDatasListener onFinishGetDatasListener) {
        this.onFinishGetDatasListener = onFinishGetDatasListener;
    }

    public String getContent() {
        return content;
    }
}