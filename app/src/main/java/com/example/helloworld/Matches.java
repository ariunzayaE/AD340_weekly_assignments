package com.example.helloworld;

public class Matches {
    private String name;
    //private String description;
    private boolean liked;
    private String imageUrl;
    private String lat;
    private String longitude;
    private String uid;

    public Matches(){
    }

    /*
    public Matches(String name, String description, boolean liked, String imageUrl) {
        this.name = name;
        this.description = description;
        this.liked = liked;
        this.imageUrl = imageUrl;
    }

     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     */

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}