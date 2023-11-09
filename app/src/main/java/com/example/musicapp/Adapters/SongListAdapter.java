package com.example.musicapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicapp.Activities.DetailActivity;
import com.example.musicapp.Activities.MainListSong;
import com.example.musicapp.Domain.ListFilm;
import com.example.musicapp.R;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    // Adapter này được thiết kế để hiển thị một danh sách các bài hát hoặc bộ phim trong một RecyclerView,
    // mỗi mục có tiêu đề và một hình ảnh tương ứng. Khi một mục được nhấp, nó mở một DetailActivity hiển thị thông tin chi tiết về mục đã chọn.
    ListFilm items;
    Context context;

    public SongListAdapter(ListFilm items) {
        this.items = items;
    }//Constructor SongListAdapter(ListFilm items) được sử dụng để khởi tạo adapter với nguồn dữ liệu.

    @NonNull
    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_song, parent, false);
        return new ViewHolder(inflate);
        //chịu trách nhiệm để khởi tạo bố cục cho từng mục trong RecyclerView. Nó inflate bố cục "viewholder_song" và trả về một thể hiện ViewHolder mới.
    }

    @Override
    public void onBindViewHolder(@NonNull SongListAdapter.ViewHolder holder, int position) {
        //được gọi cho mỗi mục trong RecyclerView và chịu trách nhiệm gắn dữ liệu vào ViewHolder.
        // Nó đặt tiêu đề và hình ảnh cho mục hiện tại bằng cách sử dụng Glide để tải hình ảnh.
    holder.titleTxt.setText(items.getData().get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop(), new RoundedCorners(30));

        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            // Nó cũng thiết lập một OnClickListener cho mỗi mục để mở một DetailActivity khi được nhấp vào, và truyền id của mục đã chọn như một tham số thêm.
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("id",items.getData().get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.getData().size();
        // trả về số lượng các mục trong nguồn dữ liệu items.
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
