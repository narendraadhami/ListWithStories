package com.dhami.andy.storycards.activities;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhami.andy.storycards.R;
import com.dhami.andy.storycards.adapters.StoryCardsListAdapter;
import com.dhami.andy.storycards.models.ParcelableModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryDetailsActivity extends AppCompatActivity {
    ArrayList<ParcelableModel> detailsData = new ArrayList<ParcelableModel>();
    TextView mTitle,mDescription,mAuthorName;
    Button mFollowBtn;
    ImageView mStoryImage;
    StoryCardsListAdapter.FollowStateListener followStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize views
        mTitle=(TextView)findViewById(R.id.title);
        mDescription=(TextView)findViewById(R.id.description);
        mAuthorName=(TextView)findViewById(R.id.author_name);
        mStoryImage=(ImageView)findViewById(R.id.story_img);
        mFollowBtn=(Button)findViewById(R.id.follow_btn);

        setValues();

    }

//    public void followOnClick(View v)
//    {
//        followStateListener.followClick("");
//    }

    //setting the values
    private  void setValues()
    {
        detailsData= getIntent().getParcelableArrayListExtra("detailsData");
        mTitle.setText(detailsData.get(0).title);
        mDescription.setText(detailsData.get(0).description);
        mAuthorName.setText(detailsData.get(0).authorName);

        if(detailsData.get(0).followState.equals("true")) {
            mFollowBtn.setText(R.string.following);
            mFollowBtn.setTextColor(this.getResources().getColor(android.R.color.holo_green_dark));
        }

        Picasso.with(this).load(detailsData.get(0).imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(mStoryImage);
    }
}