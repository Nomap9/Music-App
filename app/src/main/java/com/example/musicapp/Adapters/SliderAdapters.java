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

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {
    //dùng để kết nối dữ liệu (danh sách sliderItems) với giao diện người dùng để hiển thị các mục trong ViewPager2.
    private List<SliderItems> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapters(List<SliderItems> sliderItems, ViewPager2 viewPager2) {
        //Constructor này được sử dụng để khởi tạo Adapter và truyền vào danh sách sliderItems cùng với đối tượng ViewPager2 để thực hiện kết nối dữ liệu.
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Được gọi khi RecyclerView cần tạo một ViewHolder mới.
        //Trong phương thức này, bạn gán giao diện từ tệp layout slide_item_container cho mỗi mục trong ViewPager2.
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container, parent, false
        ));

    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        //Được gọi khi RecyclerView cần nạp dữ liệu vào ViewHolder (giao diện item) tại vị trí cụ thể.
    holder.setImage(sliderItems.get(position));
    if(position==sliderItems.size() - 2) {
        viewPager2.post(runnable);
    }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    } //Trả về số lượng mục trong danh sách sliderItems, quyết định kích thước của ViewPager2.

    public class SliderViewHolder extends  RecyclerView.ViewHolder{
        //Là một lớp trong lớp Adapter để đại diện cho ViewHolder (giao diện item) trong RecyclerView.
        //Có một ImageView để hiển thị hình ảnh.
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            //Là một lớp trong lớp Adapter để đại diện cho ViewHolder (giao diện item) trong RecyclerView.
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(SliderItems sliderItems){
            //Là một phương thức của lớp SliderViewHolder để thiết lập hình ảnh vào ImageView trong ViewHolder.
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
            //lặp lại danh sách sliderItems khi đang hiển thị ở vị trí cuối cùng. Điều này giúp tạo hiệu ứng vô hạn trong thanh trượt hình ảnh.
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
