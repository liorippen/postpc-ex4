package com.example.uzer1.myimagegallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient httpClient;
    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<MyImage> images;
    private static final String BASE_URL = "https://api.imgur.com";
    private static final String CLIENT_ID = "70d4f80e40e736d";
    private static final String ALBUM_ID = "1WSkwF3";

    private void fetchData() {
        httpClient = new OkHttpClient.Builder().build();
        builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient).build();
        ImgurAPI imgurAPI = retrofit.create(ImgurAPI.class);
        Call <MyResponse> c = imgurAPI.getAlbumImages(ALBUM_ID);
        c.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                MyResponse data = response.body();
                if (data != null) {
                    for (MyResponse.Image image: data.data.images) {
                        images.add(new MyImage(image.link));
                    }
                    myRecyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.imageview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(images);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        Button button = (Button) findViewById(R.id.getalbum);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          fetchData();
                                      }
                                  }
        );
    }


    public interface ImgurAPI{
        @Headers("Authorization: Client-ID " + CLIENT_ID)
        @GET("/3/album/{albumHash}")
        Call<MyResponse> getAlbumImages(@Path("albumHash") String albumHash);
    }
}
