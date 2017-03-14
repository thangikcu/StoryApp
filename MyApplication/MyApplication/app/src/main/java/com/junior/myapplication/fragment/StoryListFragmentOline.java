package com.junior.myapplication.fragment;

/**
 * Created by Admin on 02/28/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.junior.myapplication.interfaces.OnFinishGetDatasListener;
import com.junior.myapplication.R;
import com.junior.myapplication.adapter.StoryAdapterOline;
import com.junior.myapplication.model.StoryManager;
import com.junior.myapplication.model.entity.Story;

import java.util.ArrayList;

public class StoryListFragmentOline extends Fragment implements OnFinishGetDatasListener {
    private View view;
    private ListView livStoryList;

    private StoryAdapterOline storyAdapter;
    private ArrayList<Story> stories;
    private String url = "http://truyencotich.vn/truyen-ngan";

    private StoryManager storyManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_story_online, container, false);
        livStoryList = (ListView) view.findViewById(R.id.liv_story_list);
   /*        initlizeComponent();
     IntentFilter filter = new IntentFilter();
        filter.addAction("action_stories_loaded");
        filter.addAction("url");

        storyReceiver = new StoryReceiver();
        getActivity().registerReceiver(storyReceiver, filter);*/

        storyManager = new StoryManager(getContext());
        storyManager.setOnFinishGetDatasListener(this);

        storyAdapter = new StoryAdapterOline(getContext(), storyManager.getStory());
        livStoryList.setAdapter(storyAdapter);

        loadData(url);
        return view;

    }


 /*   private void initlizeComponent() {
        loadData(url);
    }
*/
    public void loadData(String url) {
        storyManager.parseStories(url);
    }


    @Override
    public void onSuccess() {
        livStoryList.smoothScrollToPosition(0);
        storyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(), "Tải dữ liệu thất bại !", Toast.LENGTH_SHORT).show();
    }

/*    private class StoryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            storyAdapter = new StoryAdapterOline(getContext(), storyManager.getStory());
            livStoryList.setAdapter(storyAdapter);
            switch (intent.getAction()) {
                case "url":
                    url = intent.getStringExtra("url");
                    loadData(url);
                    break;
            }

        }
    }*/
}