package com.junior.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.junior.myapplication.R;
import com.junior.myapplication.model.entity.Topic;

import java.util.ArrayList;

/**
 * Created by Admin on 01/18/2017.
 */

public class TopicAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Topic> topics;

        public TopicAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            topics=new ArrayList<>();
            topics.add(new Topic("Truyện cổ tích Việt Nam"));
            topics.add(new Topic("Truyện cổ tích Thế Giới"));
            topics.add(new Topic("Truyện dân gian"));
            topics.add(new Topic("Truyện ngụ ngôn"));
            topics.add(new Topic("Truyện cười"));
            topics.add(new Topic("Quà tặng cuộc sống"));
            topics.add(new Topic("Truyện song ngữ"));

        }

        @Override
        public int getCount() {
            return topics.size() + 1;
        }

        @Override
        public Topic getItem(int position) {
            if (position == 0) {
                return null;
            }

            return topics.get(position - 1);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (position == 0) {
                convertView = inflater.inflate(R.layout.item_topic, parent, false);
                return convertView;
            }

            convertView = inflater.inflate(R.layout.item_topicccccc, parent, false);
            TextView txtName = (TextView) convertView.findViewById(R.id.txt_topic);

            Topic topic = topics.get(position - 1);
            txtName.setText(topic.getName());

            return convertView;
        }

    }