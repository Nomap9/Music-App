//package com.example.musicapp.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.musicapp.Domain.GenresItem;
//import com.example.musicapp.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryEachSongListAdapter extends RecyclerView.Adapter<CategoryEachSongListAdapter.ViewHolder> {
//    List<String> items;
//    Context context;
//
//    public CategoryEachSongListAdapter(List<String> items) {
//        this.items = items;
//    }
//
//    @NonNull
//    @Override
//    public CategoryEachSongListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context=parent.getContext();
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CategoryEachSongListAdapter.ViewHolder holder, int position) {
//    holder.titleTxt.setText(items.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public class ViewHolder extends  RecyclerView.ViewHolder{
//
//        TextView titleTxt;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTxt=itemView.findViewById(R.id.TitleTxt);
//
//        }
//    }
//}
