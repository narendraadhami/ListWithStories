package com.dhami.andy.storycards.adapters;

import android.app.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhami.andy.storycards.R;

import com.dhami.andy.storycards.models.StoryListData;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by andy on 15/03/16.
 */
public class StoryCardsListAdapter extends RecyclerView.Adapter<StoryCardsListAdapter.ViewHolder> {

    private   ArrayList<StoryListData> storyList = new ArrayList<>();
    private   ArrayList<StoryListData> authorList = new ArrayList<>();
    private int rowLayout;
    private Activity mActivity;
    FollowStateListener mFollowStateListener;
    boolean flagForFollowState=false;

    public StoryCardsListAdapter(ArrayList<StoryListData> storyList, ArrayList<StoryListData> authorList,
                                 int rowLayout, Activity activity, FollowStateListener followStateListener) {
        this.storyList = storyList;
        this.authorList=authorList;
        this.rowLayout = rowLayout;
        this.mActivity = activity;
        mFollowStateListener=followStateListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mTitle.setText(storyList.get(position).getTitle());
        viewHolder.mDescription.setText(storyList.get(position).getDescription());

        for (StoryListData author:authorList){
            if(author.getId().equals(storyList.get(position).getDb())) {
                viewHolder.mAuthorName.setText(author.getUserName());
                if (author.is_following())
                    flagForFollowState = true;
                break;
            }
        }
        if(flagForFollowState) {
            viewHolder.mFollowBtn.setText(R.string.following);
            viewHolder.mFollowBtn.setTextColor(mActivity.getResources().getColor(android.R.color.holo_green_dark));
            flagForFollowState=false;
        }
        else {
            viewHolder.mFollowBtn.setText(R.string.follow);
            viewHolder.mFollowBtn.setTextColor(mActivity.getResources().getColor(android.R.color.holo_red_dark));
        }

        //download and set the story images
        Picasso.with(mActivity).load(storyList.get(position).getSi())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(viewHolder.mBackgroundImage);


        viewHolder.mFollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFollowStateListener.followClick(storyList.get(position).getDb());
            }
        });

    }

    @Override
    public int getItemCount() {
        return storyList == null ? 0 : storyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mBackgroundImage;
        TextView mTitle,mAuthorName,mDescription;
        Button mFollowBtn;
        RelativeLayout mCompleteRow;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle=(TextView)itemView.findViewById(R.id.title);
            mAuthorName=(TextView)itemView.findViewById(R.id.author_name);
            mDescription=(TextView)itemView.findViewById(R.id.description);
            mFollowBtn=(Button)itemView.findViewById(R.id.follow_btn);
            mBackgroundImage=(ImageView)itemView.findViewById(R.id.background_img);
            mCompleteRow=(RelativeLayout)itemView.findViewById(R.id.background_rel);
        }
    }

    //create listener for follow click
    public interface FollowStateListener {
        // TODO: Update argument type and name
        void followClick(String id);
    }
}
