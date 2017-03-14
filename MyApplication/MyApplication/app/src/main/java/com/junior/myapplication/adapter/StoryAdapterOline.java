package com.junior.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.junior.myapplication.R;
import com.junior.myapplication.model.entity.Story;
import com.junior.myapplication.activity.StoryActivityOnline;

import java.util.ArrayList;

public class StoryAdapterOline extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Story> stories;

    public StoryAdapterOline(Context context, ArrayList<Story> stories) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.stories = stories;
    }

    @Override
    public int getCount() {
        return (stories != null) ? stories.size() : 0;
    }

    @Override
    public Story getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.storyonline, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.img_image);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.txtContent = (TextView) convertView.findViewById(R.id.txt_cc);
            viewHolder.btnLoad = (Button) convertView.findViewById(R.id.btn_load);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Story story = stories.get(position);

        Glide.with(context)
                .load(story.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.imgImage);

        viewHolder.txtName.setText(story.getName());
        viewHolder.txtContent.setText(story.getContent());


        viewHolder.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryActivityOnline.class);
                intent.putExtra("name", stories.get(position).getName());
                intent.putExtra("author", stories.get(position).getAuthor());
                intent.putExtra("url", stories.get(position).getUrl());
                context.startActivity(intent);

            }
        });


        return convertView;
    }


    private class ViewHolder {
        private ImageView imgImage;
        private TextView txtName;
        private TextView txtContent;
        private Button btnLoad;
        private Button btnDownload;
    }

}