package com.example.musicapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musicapp.Adapters.CategoryListAdapter;
import com.example.musicapp.Adapters.SliderAdapters;
import com.example.musicapp.Adapters.SongListAdapter;
import com.example.musicapp.Domain.GenresItem;
import com.example.musicapp.Domain.ListFilm;
import com.example.musicapp.Domain.SliderItems;
import com.example.musicapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterBestMusics, adapterUpcoming, adapterCategory;
    private RecyclerView recyclerViewBestMusics, recyclerViewUpcoming, recyclerViewCategory;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2, mStringRequest3;
    private ProgressBar loading1, loading2, loading3;


    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        
        initView();
        banners();
        sendRequestBestSongs();
        sendRequestUpComming();
        sendRequestCategory();
    }

    private void sendRequestBestSongs() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                loading1.setVisibility(View.GONE);
                ListFilm items=gson.fromJson(response,ListFilm.class);
                adapterBestMusics=new SongListAdapter(items);
                recyclerViewBestMusics.setAdapter(adapterBestMusics);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading1.setVisibility(View.GONE);
                Log.i("UiLover", "onErrorResponse" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequestUpComming() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mStringRequest3=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                loading3.setVisibility(View.GONE);
                ListFilm items=gson.fromJson(response,ListFilm.class);
                adapterUpcoming=new SongListAdapter(items);
                recyclerViewUpcoming.setAdapter(adapterUpcoming);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading3.setVisibility(View.GONE);
                Log.i("UiLover", "onErrorResponse" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest3);
    }

    private void sendRequestCategory() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                loading2.setVisibility(View.GONE);
                ArrayList<GenresItem> catList=gson.fromJson(response, new TypeToken<ArrayList<GenresItem>>(){

                }.getType());
                adapterCategory=new CategoryListAdapter(catList);
                recyclerViewCategory.setAdapter(adapterCategory);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading2.setVisibility(View.GONE);
                Log.i("UiLover", "onErrorResponse" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void banners() {
        List<SliderItems> sliderItems = new ArrayList<>();
        //Tạo một danh sách sliderItems chứa các đối tượng SliderItems, mỗi đối tượng đại diện cho một hình ảnh trong thanh trượt.
        sliderItems.add(new SliderItems(R.drawable.wide));
        sliderItems.add(new SliderItems(R.drawable.wide1));
        sliderItems.add(new SliderItems(R.drawable.wide3));

        viewPager2.setAdapter(new SliderAdapters(sliderItems, viewPager2));
        //Đặt Adapter của ViewPager2 bằng cách sử dụng SliderAdapters và truyền vào danh sách sliderItems và ViewPager2.
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode((RecyclerView.OVER_SCROLL_NEVER));// tắt hiệu ứng over-scrolling của ViewPager2

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        //Sử dụng CompositePageTransformer để áp dụng hiệu ứng trượt cho các hình ảnh trong ViewPager2.
        // Hiệu ứng này bao gồm việc thay đổi kích thước và vị trí của các hình ảnh để tạo hiệu ứng trượt đẹp mắt.
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                //tự động chuyển trang trong ViewPager2 sau một khoảng thời gian cố định.
            }
        });
    }

    private  Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 2000);
    }

    private void initView() {

        viewPager2 = findViewById(R.id.viewpagerSlider);
        recyclerViewBestMusics=findViewById(R.id.view1);
        recyclerViewBestMusics.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpcoming=findViewById(R.id.view3);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory=findViewById(R.id.view2);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);
        loading3=findViewById(R.id.progressBar3);
    }


}