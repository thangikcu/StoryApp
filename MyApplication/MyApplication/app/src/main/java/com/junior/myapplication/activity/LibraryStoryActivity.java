package com.junior.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.junior.myapplication.model.Database;
import com.junior.myapplication.R;
import com.junior.myapplication.model.entity.Stories;
import com.junior.myapplication.adapter.StoryAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 02/20/2017.
 */

public class LibraryStoryActivity extends AppCompatActivity {
    private ListView livStory;
    private StoryAdapter storyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_stories);
        initializeComponent();
    }

    private void initializeComponent() {
        livStory = (ListView) findViewById(R.id.liv_lbr_story);

        Database database = new Database();
        database.copyDataBase();

        ArrayList<Stories> stories = database.getStories();

        storyAdapter = new StoryAdapter(this, stories);
        livStory.setAdapter(storyAdapter);
    }
}
