package com.dhami.andy.storycards.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhami.andy.storycards.R;
import com.dhami.andy.storycards.constants.AppConstants;
import com.dhami.andy.storycards.listeners.FollowStateListener;
import com.squareup.picasso.Picasso;

/**
 * Created by andy on 16/03/16.
 */
public class StoryDetailsFragment extends Fragment {
    TextView mTitle,mDescription,mAuthorName;
    Button mFollowBtn;
    ImageView mStoryImage;
    FollowStateListener mListener;

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String AUTHOR_NAME = "authorName";
    private static final String FOLLOW_STATE = "followState";
    private static final String IMAGE_URL = "imageUrl";
    private static final String ID = "id";


    private String title,description,authorName,followState,imageUrl,id;

    public static StoryDetailsFragment newInstance(String title, String description, String authorName, String imageUrl, String followState,String id) {
        StoryDetailsFragment fragment = new StoryDetailsFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(DESCRIPTION, description);
        args.putString(AUTHOR_NAME, authorName);
        args.putString(IMAGE_URL, imageUrl);
        args.putString(FOLLOW_STATE, followState);
        args.putString(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public StoryDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //extracting the values
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            description = getArguments().getString(DESCRIPTION);
            authorName = getArguments().getString(AUTHOR_NAME);
            imageUrl = getArguments().getString(IMAGE_URL);
            followState = getArguments().getString(FOLLOW_STATE);
            id = getArguments().getString(ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mRootView = inflater.inflate(R.layout.fragment_story_details, container, false);

        //initialize views
        mTitle = (TextView) mRootView.findViewById(R.id.title);
        mDescription = (TextView)mRootView. findViewById(R.id.description);
        mAuthorName = (TextView)mRootView. findViewById(R.id.author_name);
        mStoryImage = (ImageView)mRootView. findViewById(R.id.story_img);
        mFollowBtn = (Button)mRootView. findViewById(R.id.follow_btn);

        mFollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFollowBtn.getText().toString().equals(AppConstants.follow)) {
                    mFollowBtn.setText(R.string.following);
                    mFollowBtn.setTextColor(StoryDetailsFragment.this.getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    mFollowBtn.setText(R.string.follow);
                    mFollowBtn.setTextColor(StoryDetailsFragment.this.getResources().getColor(android.R.color.holo_red_dark));
                }
                mListener.followClick(id);
            }
        });

        setValues();
        return  mRootView;
    }


    //initializing the followstateListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FollowStateListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FollowStateListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //setting the values
    private  void setValues()
    {
        mTitle.setText(title);
        mDescription.setText(description);
        mAuthorName.setText(authorName);

        if(followState.equals("true")) {
            mFollowBtn.setText(R.string.following);
            mFollowBtn.setTextColor(this.getResources().getColor(android.R.color.holo_green_dark));
        }

        Picasso.with(getActivity()).load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(mStoryImage);
    }
}
