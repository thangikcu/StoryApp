package com.junior.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.junior.myapplication.interfaces.OnFinishGetDatasListener;
import com.junior.myapplication.R;
import com.junior.myapplication.adapter.StoryAdapter;
import com.junior.myapplication.model.ContentManager;
import com.junior.myapplication.model.Database;
import com.junior.myapplication.model.entity.Stories;

/**
 * Created by Admin on 02/28/2017.
 */

public class StoryActivityOnline extends AppCompatActivity implements View.OnClickListener, OnFinishGetDatasListener {
    private static final String TAG = "StoryActivityOnline";

    private TextView txtName;
    private FloatingActionButton btnAddStory;
    private TextView txtAuthor;
    private TextView txtContent;
    private ContentManager contentManager;
    private Stories stories;
    private StoryAdapter storyAdapter;
    String url = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_online);
/*        IntentFilter filter = new IntentFilter();
        filter.addAction("action_stories_loaded_content");

        contentReceiver = new ContentReceiver();
        registerReceiver(contentReceiver, filter);*/
        initlizeComponent();
    }

    private void initlizeComponent() {
        txtName = (TextView) findViewById(R.id.txt_name);
        txtAuthor = (TextView) findViewById(R.id.txt_author);
        txtContent = (TextView) findViewById(R.id.txt_content);
        btnAddStory = (FloatingActionButton) findViewById(R.id.btn_add_story);

        btnAddStory.setOnClickListener(this);

        Intent intent = getIntent();
        txtName.setText(intent.getStringExtra("name"));
        txtAuthor.setText(intent.getStringExtra("author"));
        url = intent.getStringExtra("url");

        contentManager = new ContentManager(this);
        contentManager.setOnFinishGetDatasListener(this);

        contentManager.parseStories(url);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_story:
                Database database = new Database();
                String name = (String) txtName.getText();
                String author = (String) txtAuthor.getText();
                String content = (String) txtContent.getText();
                stories = new Stories(name, author, content, 0);
                database.insertStory(stories);
                storyAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã tải truyện", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess() {
        txtContent.setText(contentManager.getContent());
    }

    @Override
    public void onFail() {
        Toast.makeText(this, "Tải dữ liệu thất bại !", Toast.LENGTH_SHORT).show();
    }

/*    private class ContentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "action_stories_loaded_content":
                    txtContent.setText(intent.getStringExtra("content"));
                    System.out.println(intent.getStringExtra("content"));
                    break;
            }
        }
    }*/
}
