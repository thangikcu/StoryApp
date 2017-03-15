package com.junior.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.junior.myapplication.R;
import com.junior.myapplication.adapter.PagerAdapter;
import com.junior.myapplication.fragment.StoryListFragment;
import com.junior.myapplication.fragment.StoryListFragmentOline;
import com.junior.myapplication.model.entity.Topic;

import java.util.ArrayList;

import static android.support.design.R.styleable.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ListView livListStory;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ArrayList<Topic> topics;
    private String url;

    private StoryListFragmentOline storyListFragmentOline;
    private StoryListFragment storyListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponent();
    }

    private void initializeComponent() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_story);
        viewPager = (ViewPager) findViewById(R.id.vpg_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        storyListFragmentOline = new StoryListFragmentOline();
        storyListFragment = new StoryListFragment();

        Fragment[] fragments = new Fragment[2];
        fragments[0] = storyListFragmentOline;
        fragments[1] = storyListFragment;
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
/*
        livListStory = (ListView) findViewById(R.id.liv_list_stories);
        livListStory.setAdapter(new TopicAdapter(this));

        livListStory.setOnItemClickListener(this);*/

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //fill data

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_co_tich_tg:
                url = "http://truyencotich.vn/truyen-co-tich/co-tich-the-gioi";
                storyListFragmentOline.loadData(url);
                break;
            case R.id.nav_co_tich_vn:
                url = "http://truyencotich.vn/truyen-co-tich/co-tich-viet-nam";
                storyListFragmentOline.loadData(url);
                break;
            case R.id.nav_cuoi:
                url = "http://truyencotich.vn/truyen-cuoi";
                storyListFragmentOline.loadData(url);
                break;
            case R.id.nav_dan_gian:
                url = "http://truyencotich.vn/truyen-dan-gian";
                storyListFragmentOline.loadData(url);
                break;
            case R.id.nav_ngu_ngon:
                url = "http://truyencotich.vn/truyen-ngu-ngon";
                storyListFragmentOline.loadData(url);
                break;
            case R.id.nav_qua_tang_cs:
                url = "http://truyencotich.vn/qua-tang-cuoc-song";
                storyListFragmentOline.loadData(url);
                break;
            case R.id.nav_song_ngu:
                url = "http://truyencotich.vn/truyen-co-tich-song-ngu";
                storyListFragmentOline.loadData(url);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

/*    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                url = "http://truyencotich.vn/truyen-co-tich/co-tich-viet-nam";
                storyListFragmentOline.loadData(url);
                break;

            case 2:
                url = "http://truyencotich.vn/truyen-co-tich/co-tich-the-gioi";
                storyListFragmentOline.loadData(url);
                break;
            case 3:
                url = "http://truyencotich.vn/truyen-dan-gian";
                storyListFragmentOline.loadData(url);
                break;
            case 4:
                url = "http://truyencotich.vn/truyen-ngu-ngon";
                storyListFragmentOline.loadData(url);
                break;
            case 5:
                url = "http://truyencotich.vn/truyen-cuoi";
                storyListFragmentOline.loadData(url);
                break;
            case 6:
                url = "http://truyencotich.vn/qua-tang-cuoc-song";
                storyListFragmentOline.loadData(url);
                break;
            case 7:
                url = "http://truyencotich.vn/truyen-co-tich-song-ngu";
                storyListFragmentOline.loadData(url);
                break;

            default:
                break;
        }

        drawerLayout.closeDrawers();
        Intent intent = new Intent();
        intent.setAction("url");
        intent.putExtra("url", url);
        sendBroadcast(intent);

    }*/


}
