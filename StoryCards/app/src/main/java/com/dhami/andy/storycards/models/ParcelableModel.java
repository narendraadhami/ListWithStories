package com.dhami.andy.storycards.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by andy on 15/03/16.
 */
public class ParcelableModel implements Parcelable {
    public String title,description,imageUrl,authorName,followState;

    public ParcelableModel(String title, String description, String authorName, String imageUrl, String followState){
        this.title = title;
        this.description = description;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
        this.followState=followState;
    }
    public static final Creator<ParcelableModel> CREATOR
            = new Creator<ParcelableModel>()
    {
        public ParcelableModel createFromParcel(Parcel in)
        {
            return new ParcelableModel(in);
        }

        public ParcelableModel[] newArray(int size)
        {
            return new ParcelableModel[size];
        }
    };

    private ParcelableModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        authorName = in.readString();
        imageUrl = in.readString();
        followState = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(authorName);
        dest.writeString(imageUrl);
        dest.writeString(followState);
    }
}
