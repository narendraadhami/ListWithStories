package com.dhami.andy.storycards.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.dhami.andy.storycards.R;
import com.dhami.andy.storycards.adapters.StoryCardsListAdapter;
import com.dhami.andy.storycards.constants.AppConstants;
import com.dhami.andy.storycards.listeners.FollowStateListener;
import com.dhami.andy.storycards.models.StoryListData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoryCardsListActivity extends AppCompatActivity implements FollowStateListener {
    RecyclerView mRecyclerViewList;
    StoryCardsListAdapter mStoryCardsListAdapter;
    ArrayList<StoryListData> mData=new ArrayList<>();
    ArrayList<StoryListData> mStories=new ArrayList<>();
    ArrayList<StoryListData> mAuthors=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_cards_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerViewList = (RecyclerView) findViewById(R.id.story_cards_recycler_view);
        setSupportActionBar(toolbar);

        mRecyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewList.setItemAnimator(new DefaultItemAnimator());

        mData=readJsonData();

        for (StoryListData storyOrAuthor : mData) {
            if (storyOrAuthor.getUserName()!=null) {
                mAuthors.add(storyOrAuthor);
            }
            else
                mStories.add(storyOrAuthor);
        }

        //set adapter to recycler view
        mStoryCardsListAdapter = new StoryCardsListAdapter(mStories, mAuthors,R.layout.story_cards_list_row, this,this);
        mRecyclerViewList.setAdapter(mStoryCardsListAdapter);
    }

    //read json data from file and parse using GSON
    private  ArrayList<StoryListData>  readJsonData()
    {
        Gson gson = new Gson();
        String str;

        StringBuilder buffer=new StringBuilder();
        InputStream json= null;
        try {
            AssetManager assetManager = getResources().getAssets();
            json = assetManager.open(AppConstants.jsonFilePath);

            BufferedReader in=
                    null;
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while ((str = in.readLine()) != null) {
                buffer.append(str);
            }
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        final Type DATA_TYPE = new TypeToken<ArrayList<StoryListData>>() {
        }.getType();

        String data  = new String(buffer);
        ArrayList<StoryListData> response = gson.fromJson(data
                , DATA_TYPE);
        return response;
    }

    //implement FollowStateListener
    @Override
    public void followClick(String id) {
        for (StoryListData author:mAuthors){
            if(author.getId().equals(id)) {
                if(author.is_following())
                    author.setIs_following(false);
                else
                    author.setIs_following(true);
                break;
            }
        }
        mStoryCardsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
