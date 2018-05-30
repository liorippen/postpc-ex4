package com.example.uzer1.myimagegallery;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by uzer1 on 30/05/2018.
 */

public class MyResponse {
    @SerializedName("data")
    public Data data;
    public Boolean success;
    public Integer status;

    public static class Data {

        public String id;
        public String title;
        public Object description;
        public Integer datetime;
        public String cover;
        public String accountUrl;
        public Integer accountId;
        public String privacy;
        public String layout;
        public Integer views;
        public String link;
        public Integer imagesCount;
        public List<Image> images = null;

    }


    public static class Image {

        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public Object title;
        @SerializedName("description")
        public Object description;
        @SerializedName("datetime")
        public Integer datetime;
        @SerializedName("type")
        public String type;
        @SerializedName("animated")
        public Boolean animated;
        @SerializedName("width")
        public Integer width;
        @SerializedName("height")
        public Integer height;
        @SerializedName("size")
        public Integer size;
        @SerializedName("views")
        public Integer views;
        @SerializedName("bandwidth")
        public Integer bandwidth;
        @SerializedName("link")
        public String link;

    }
}
