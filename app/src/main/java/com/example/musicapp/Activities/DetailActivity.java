package com.example.musicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
//import com.example.musicapp.Adapters.ArtistListAdapter;
//import com.example.musicapp.Adapters.CategoryEachSongListAdapter;
//import com.example.musicapp.Domain.FilmItem;
import com.example.musicapp.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, songRateTxt, songTimeTxt, songSummaryinfo, songArtistInfo;
    private  int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterArtistList, adapterCategory;
    private  RecyclerView recyclerViewArtist, recyclerViewCategory;
    private NestedScrollView scrollView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        idFilm=getIntent().getIntExtra("id", 0);
        initView();
//        sendRequest();
    }

//    private void sendRequest() {
//        mRequestQueue= Volley.newRequestQueue(this);
//        progressBar.setVisibility(View.VISIBLE);
//        scrollView.setVisibility(View.GONE);
//
//        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson=new Gson();
//                progressBar.setVisibility(View.GONE);
//                scrollView.setVisibility(View.GONE);
//
//                FilmItem item = gson.fromJson(response,FilmItem.class);
//
//                Glide.with(DetailActivity.this)
//                        .load(item.getPoster())
//                        .into(pic2);
//
//                titleTxt.setText(item.getTitle());
//                songRateTxt.setText(item.getImdbRating());
//                songTimeTxt.setText(item.getRuntime());
//                songSummaryinfo.setText(item.getPlot());
//                songArtistInfo.setText(item.getActors());
//                if(item.getImages()!=null){
//                    adapterArtistList=new ArtistListAdapter(item.getImages());
//                    recyclerViewArtist.setAdapter(adapterArtistList);
//                }
//                if(item.getGenres() != null) {
//                    adapterCategory= new CategoryEachSongListAdapter(item.getGenres());
//                    recyclerViewCategory.setAdapter(adapterCategory);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//        mRequestQueue.add(mStringRequest);
//
//    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        titleTxt=findViewById(R.id.songNameTxt);
        progressBar = findViewById(R.id.progressBarDetail);
        scrollView = findViewById(R.id.scrollView2);
        pic2=findViewById(R.id.picDetail);
        songRateTxt=findViewById(R.id.songStar);
        songTimeTxt =findViewById(R.id.songTime);
        songSummaryinfo=findViewById(R.id.songSummery);
        songArtistInfo=findViewById(R.id.songArtistInfo);
        backImg=findViewById(R.id.backlmg);
        recyclerViewCategory=findViewById(R.id.genreView);
        recyclerViewArtist=findViewById(R.id.imagesRecycler);
        recyclerViewArtist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}