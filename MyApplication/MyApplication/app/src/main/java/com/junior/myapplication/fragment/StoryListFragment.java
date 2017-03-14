package com.junior.myapplication.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junior.myapplication.R;
import com.junior.myapplication.activity.StoryActivity;
import com.junior.myapplication.adapter.StoryAdapter;
import com.junior.myapplication.model.Database;
import com.junior.myapplication.model.entity.Stories;

import java.util.ArrayList;

public class StoryListFragment extends Fragment implements StoryAdapter.OnClickStoryListener {
    public static final String ADD_STORY_ACTION = "ADD_STORY_ACTION";

    public static final String STORY_ID = "STORY_ID";

    private Database database;
    private RecyclerView livStoryList;
    private StoryAdapter storyAdapter;
    private ArrayList<Stories> stories;

    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ADD_STORY_ACTION:
                    Log.d("thangggggg", "onReceive: ");
                    Stories story = database.getStoryById(intent.getLongExtra(STORY_ID, 0));

                    if (story != null) {
                        stories.add(0, story);
                        storyAdapter.notifyItemInserted(0);
                    }
                    break;
                default:
                    break;
            }

        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        livStoryList = (RecyclerView) view.findViewById(R.id.recycler_list_story);
        initlizeComponent();
        return view;

    }



    @Override
    public void onResume() {
        getContext().registerReceiver(broadcastReceiver, intentFilter);
        super.onResume();
    }

    private void initlizeComponent() {
        database = new Database();
        database.copyDataBase();
        stories = database.getStories();

        storyAdapter = new StoryAdapter(getContext(), stories);
        storyAdapter.setOnClickStoryListener(this);

        livStoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        livStoryList.setAdapter(storyAdapter);

        intentFilter = new IntentFilter();
        intentFilter.addAction(ADD_STORY_ACTION);
    }


    @Override
    public void onClickStory(Stories story) {
        Intent intent = new Intent(getContext(), StoryActivity.class);
        intent.putExtra("name", story.getName());
        intent.putExtra("author", story.getAuthor());
        intent.putExtra("content", story.getContent());
        startActivity(intent);
    }
}