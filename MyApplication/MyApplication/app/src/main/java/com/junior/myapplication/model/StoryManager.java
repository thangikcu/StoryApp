package com.junior.myapplication.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.junior.myapplication.interfaces.OnFinishGetDatasListener;
import com.junior.myapplication.model.entity.Story;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class StoryManager {
    private static final String TAG = "StoryManager";

    private OnFinishGetDatasListener onFinishGetDatasListener;

    private Context context;
    private ArrayList<Story> stories;
    private ProgressDialog progressDialog;

    public StoryManager(Context context) {
        stories = new ArrayList<>();
        this.context = context;

        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Đợi tý...");
    }

    public void parseStories(String url) {

        new GetDatasAsyncTask().execute(url);

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    Elements elements = document.select("div.clear").first().select("article");

                    stories.clear();
                    for (int i = 0; i < elements.size(); i++) {
                        Element element = elements.get(i);
                        String imageUrl = element.select("img").attr("src");
                        String name = element.select("img").attr("title");
                        String content = element.select("p.post-excerpt").text();
                        String url = element.select("div.post-thumb").select("a").attr("href");
                        String author = "Truyện Dân Gian";
                        Story story = new Story(imageUrl, name, author, content, url);
                        stories.add(story);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }

    private class GetDatasAsyncTask extends AsyncTask<String, Void, Boolean> {
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
                Elements elements = document.select("div.clear").first().select("article");

                stories.clear();
                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    String imageUrl = element.select("img").attr("src");
                    String name = element.select("img").attr("title");
                    String content = element.select("p.post-excerpt").text();
                    String url = element.select("div.post-thumb").select("a").attr("href");
                    String author = "Truyện Dân Gian";
                    Story story = new Story(imageUrl, name, author, content, url);
                    stories.add(story);
                }

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

    public void parseStory(final String url) {
        // TODO
    }

    public void sendStoriesLoadedRequest() {
        Intent intent = new Intent();
        intent.setAction("action_stories_loaded");
        context.sendBroadcast(intent);
    }

    public ArrayList<Story> getStory() {
        return stories;
    }

    public void setOnFinishGetDatasListener(OnFinishGetDatasListener onFinishGetDatasListener) {
        this.onFinishGetDatasListener = onFinishGetDatasListener;
    }


}