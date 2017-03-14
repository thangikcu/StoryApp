package com.junior.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junior.myapplication.R;
import com.junior.myapplication.model.Database;
import com.junior.myapplication.model.entity.Stories;

import java.util.ArrayList;

/**
 * Created by Admin on 02/20/2017.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Stories> stories;
    private OnClickStoryListener onClickStoryListener;

    public StoryAdapter(Context context, ArrayList<Stories> stories) {
        inflater = LayoutInflater.from(context);
        this.stories = stories;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(stories.get(position).getName());
        holder.txtAuthor.setText(stories.get(position).getAuthor());

        if (stories.get(position).getFavorite() == 1) {
            holder.imgLike.setImageResource(R.drawable.ic_love);
        } else {
            holder.imgLike.setImageResource(R.drawable.ic_like);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (stories != null) ? stories.size() : 0;
    }

    public void setOnClickStoryListener(OnClickStoryListener onClickStoryListener) {
        this.onClickStoryListener = onClickStoryListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtAuthor;
        private ImageView imgLike;
        private ImageView btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtAuthor = (TextView) itemView.findViewById(R.id.txt_author);
            imgLike = (ImageView) itemView.findViewById(R.id.img_like);
            btnDelete = (ImageView) itemView.findViewById(R.id.btn_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickStoryListener.onClickStory(stories.get(getAdapterPosition()));
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Stories story = stories.get(getAdapterPosition());

                    Database database = new Database();
                    if (database.deleteStory(story.getId())) {
                        stories.remove(story);

                        notifyItemRemoved(getAdapterPosition());
                    }
                }
            });

            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Stories story = stories.get(getAdapterPosition());

                    Database database = new Database();
                    int favorite = (story.getFavorite() == 1) ? 0 : 1;

                    if (database.updateFavoriteStory(story.getId(), favorite)) {

                        story.setFavorite(favorite);

                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });

        }
    }

    public interface OnClickStoryListener {
        void onClickStory(Stories story);
    }

}
