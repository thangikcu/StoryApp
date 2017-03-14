package com.junior.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.junior.myapplication.model.Database;
import com.junior.myapplication.R;
import com.junior.myapplication.model.entity.Stories;

import java.util.ArrayList;

/**
 * Created by Admin on 02/20/2017.
 */

public class StoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Stories> stories;

    public StoryAdapter(Context context, ArrayList<Stories> stories) {
        inflater = LayoutInflater.from(context);
        this.stories = stories;

    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Stories getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_story, parent, false);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
            holder.txtAuthor = (TextView) convertView.findViewById(R.id.txt_author);
            holder.imgLike = (ImageView) convertView.findViewById(R.id.img_like);
            holder.btnDelete = (ImageView) convertView.findViewById(R.id.btn_delete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtName.setText(getItem(position).getName());
        holder.txtAuthor.setText(getItem(position).getAuthor());
        if (getItem(position).getFavorite() == 0) {
            holder.imgLike.setImageResource(R.drawable.ic_like);
        } else {
            holder.imgLike.setImageResource(R.drawable.ic_love);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database();
                database.deleteStory(position);
                notifyDataSetInvalidated();
            }
        });
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(position).getFavorite() == 0) {
                    holder.imgLike.setImageResource(R.drawable.ic_love);
                    getItem(position).setFavorite(1);
                } else {
                    holder.imgLike.setImageResource(R.drawable.ic_like);
                    getItem(position).setFavorite(0);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtAuthor;
        private ImageView imgLike;
        private ImageView btnDelete;
    }
}
