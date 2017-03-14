package com.junior.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.junior.myapplication.model.Database;
import com.junior.myapplication.R;
import com.junior.myapplication.model.entity.Stories;
import com.junior.myapplication.adapter.StoryAdapter;
import com.junior.myapplication.activity.StoryActivity;

import java.util.ArrayList;

public class StoryListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView livStoryList;
    private StoryAdapter storyAdapter;
    private ArrayList<Stories> stories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        livStoryList = (ListView) view.findViewById(R.id.liv_story_list);
        initlizeComponent();
        return view;

    }

    private void initlizeComponent() {
        Database database = new Database();
        database.copyDataBase();
        stories = database.getStories();
        livStoryList.setOnItemClickListener(this);

        storyAdapter = new StoryAdapter(getContext(), stories);
        livStoryList.setAdapter(storyAdapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getContext(), StoryActivity.class);
        intent.putExtra("name", stories.get(position).getName());
        intent.putExtra("author", stories.get(position).getAuthor());
        intent.putExtra("content", stories.get(position).getContent());
        startActivity(intent);

    }
}