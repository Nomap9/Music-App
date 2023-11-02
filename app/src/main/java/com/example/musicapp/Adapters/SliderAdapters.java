package com.example.musicapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicapp.Domain.SliderItems;
import com.example.musicapp.R;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHodlder> {
    private List<SliderItems> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapters(List<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapters.SliderViewHodlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new SliderViewHodlder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container, parent, false
        ));

    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapters.SliderViewHodlder holder, int position) {
    holder.setImage(sliderItems.get(position));
    if(position==sliderItems.size() - 2) {
        viewPager2.post(runnable);
    }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderViewHodlder extends  RecyclerView.ViewHolder{
        private ImageView imageView;
        public SliderViewHodlder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(SliderItems sliderItems){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));

            Glide.with(context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
