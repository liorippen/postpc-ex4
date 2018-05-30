package com.example.uzer1.myimagegallery;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;


/**
 * Created by uzer1 on 28/05/2018.
 */

public class MyImageViewHolder extends RecyclerView.ViewHolder{
    ImageView imgView;
    public MyImageViewHolder(View itemView) {
        super(itemView);
        imgView = (ImageView) itemView.findViewById(R.id.img);
    }

    public void bindViewHolder(MyImage img) {
        if (! TextUtils.isEmpty(img.getImg_url())) {
            Picasso.with(imgView.getContext()).load(img.getImg_url()).into(imgView);
        }
    }
}
