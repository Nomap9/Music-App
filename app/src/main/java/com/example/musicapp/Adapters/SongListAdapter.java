package com.example.musicapp.Adapters;

import android.content.Context;
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
import com.example.musicapp.Domain.ListFilm;
import com.example.musicapp.R;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    ListFilm items;
    Context context;

    public SongListAdapter(ListFilm items) {
        this.items = items;
    }

    @NonNull
    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_song, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SongListAdapter.ViewHolder holder, int position) {
    holder.titleTxt.setText(items.getData().get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop(), new RoundedCorners(30));

        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.getData().size();
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
