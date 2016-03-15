package com.dhami.andy.storycards.models;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andy on 15/03/16.
 */

public class StoryListData {
    @SerializedName("about")
    private String about;
    @SerializedName("id")
    private  String id;

    @SerializedName("username")
    private  String userName;

    @SerializedName("followers")
    private  long followers;

    @SerializedName("following")
    private  long following;

    @SerializedName("image")
    private  String image;

    @SerializedName("url")
    private  String url;

    @SerializedName("handle")
    private  String handle;

    @SerializedName("is_following")
    private  boolean is_following;


    @SerializedName("createdOn")
    private  long createdOn;

    @SerializedName("description")
    private  String description;

    @SerializedName("verb")
    private  String  verb;

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    @SerializedName("si")
    private  String si;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public boolean is_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLike_flag() {
        return like_flag;
    }

    public void setLike_flag(boolean like_flag) {
        this.like_flag = like_flag;
    }

    public long getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(long likes_count) {
        this.likes_count = likes_count;
    }

    public long getComment_count() {
        return comment_count;
    }

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    @SerializedName("title")
    private  String title;

    @SerializedName("type")
    private  String type;

    @SerializedName("like_flag")
    private  boolean like_flag;

    @SerializedName("likes_count")
    private  long likes_count;

    @SerializedName("comment_count")
    private  long comment_count;


    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    @SerializedName("db")
    private  String db;


}
