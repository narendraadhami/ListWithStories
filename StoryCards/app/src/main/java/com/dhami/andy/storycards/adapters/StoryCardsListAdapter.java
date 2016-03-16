package com.dhami.andy.storycards.adapters;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import com.dhami.andy.storycards.R;
import com.dhami.andy.storycards.constants.AppConstants;
import com.dhami.andy.storycards.listeners.FollowStateListener;
import com.dhami.andy.storycards.models.StoryListData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by andy on 15/03/16.
 */
public class StoryCardsListAdapter extends BaseAdapter {

    private   ArrayList<StoryListData> storyList = new ArrayList<>();
    private   ArrayList<StoryListData> authorList = new ArrayList<>();
    private int rowLayout;
    private Activity mActivity;
    FollowStateListener mFollowStateListener;
    boolean flagForFollowState=false;
    private ListView mListView;


    public StoryCardsListAdapter(ArrayList<StoryListData> storyList, ArrayList<StoryListData> authorList,
                                 int rowLayout, Activity activity, FollowStateListener followStateListener,ListView listView) {
        this.storyList = storyList;
        this.authorList=authorList;
        this.rowLayout = rowLayout;
        this.mActivity = activity;
        mFollowStateListener=followStateListener;
        this.mListView=listView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(rowLayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTitle=(TextView)convertView.findViewById(R.id.title);
            viewHolder.mAuthorName=(TextView)convertView.findViewById(R.id.author_name);
            viewHolder.mDescription=(TextView)convertView.findViewById(R.id.description);
            viewHolder.mFollowBtn=(Button)convertView.findViewById(R.id.follow_btn);
            viewHolder.mBackgroundImage=(ImageView)convertView.findViewById(R.id.background_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
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
//                // //update list without notifyDataSetChanged
                int first = mListView.getFirstVisiblePosition();
                int last = mListView.getLastVisiblePosition();
                for (StoryListData author:authorList){
                    if(author.getId().equals(storyList.get(position).getDb())) {
                        if(author.is_following())
                            author.setIs_following(false);
                        else
                            author.setIs_following(true);
                    }
                }

                if (position > first || position < last) {
                    if(viewHolder.mFollowBtn.getText().toString().equals(AppConstants.follow)) {
                        viewHolder.mFollowBtn.setText(R.string.following);
                        viewHolder.mFollowBtn.setTextColor(mActivity.getResources().getColor(android.R.color.holo_green_dark));
                    } else {
                        viewHolder.mFollowBtn.setText(R.string.follow);
                        viewHolder.mFollowBtn.setTextColor(mActivity.getResources().getColor(android.R.color.holo_red_dark));
                    }
                }

                //update list with notifyDataSetChanged
              //  mFollowStateListener.followClick(storyList.get(position).getDb());
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return storyList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        ImageView mBackgroundImage;
        TextView mTitle,mAuthorName,mDescription;
        Button mFollowBtn;
    }
}