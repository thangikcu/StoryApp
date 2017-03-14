package com.junior.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.junior.myapplication.R;
import com.junior.myapplication.fragment.StoryListFragment;
import com.junior.myapplication.interfaces.OnFinishGetDatasListener;
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
    private Database database;
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
        database = new Database();

        txtName = (TextView) findViewById(R.id.txt_name);
        txtAuthor = (TextView) findViewById(R.id.txt_author);
        txtContent = (TextView) findViewById(R.id.txt_content);
        btnAddStory = (FloatingActionButton) findViewById(R.id.btn_add_story);

        txtContent.setMovementMethod(new ScrollingMovementMethod());
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
                Log.d(TAG, "onClick: ");
                String name = (String) txtName.getText();
                String author = (String) txtAuthor.getText();
                String content = txtContent.getText().toString();
                stories = new Stories(name, author, content, 0);

                if (database.insertStory(stories)) {
                    Intent intent = new Intent(StoryListFragment.ADD_STORY_ACTION);
                    intent.putExtra(StoryListFragment.STORY_ID, stories.getId());

                    sendBroadcast(intent);

                    Toast.makeText(this, "Đã thêm vào tủ chuyện", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

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
