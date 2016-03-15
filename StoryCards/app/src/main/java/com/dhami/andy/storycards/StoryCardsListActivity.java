package com.dhami.andy.storycards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class StoryCardsListActivity extends AppCompatActivity{
    RecyclerView mRecyclerViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_cards_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerViewList = (RecyclerView) findViewById(R.id.story_cards_recycler_view);

        setSupportActionBar(toolbar);

        mRecyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewList.setItemAnimator(new DefaultItemAnimator());

    }


}
