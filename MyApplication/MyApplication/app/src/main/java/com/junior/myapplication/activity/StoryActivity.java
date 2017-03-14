package com.junior.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.junior.myapplication.R;

/**
 * Created by Admin on 02/28/2017.
 */

public class StoryActivity extends AppCompatActivity {
    private TextView txtName;
    private TextView txtAuthor;
    private TextView txtContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initlizeComponent();
    }

    private void initlizeComponent() {
        txtName= (TextView) findViewById(R.id.txt_name);
        txtAuthor= (TextView) findViewById(R.id.txt_author);
        txtContent= (TextView) findViewById(R.id.txt_content);

        txtContent.setMovementMethod(new ScrollingMovementMethod());

        Intent intent=getIntent();
        txtName.setText(intent.getStringExtra("name"));
        txtAuthor.setText(intent.getStringExtra("author"));
        txtContent.setText(intent.getStringExtra("content"));
    }
}
