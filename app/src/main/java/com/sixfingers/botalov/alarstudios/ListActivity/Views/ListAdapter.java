package com.sixfingers.botalov.alarstudios.ListActivity.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sixfingers.botalov.alarstudios.App;
import com.sixfingers.botalov.alarstudios.MapView.Views.MapActivity;
import com.sixfingers.botalov.alarstudios.R;
import com.sixfingers.botalov.alarstudios.ListActivity.Models.Data;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    private ArrayList<Data> data;

    ListAdapter(){
        data = new ArrayList<>();
    }

    public void setData(ArrayList<Data> data){
        this.data = data;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.item_view_holder, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if(data != null) {
            return data.size();
        }
        return 0;
    }

    public void appendData(List<Data> data) {
        this.data.addAll(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTextView;
        private Data data;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            imageView = itemView.findViewById(R.id.image_view);

            Glide.with(App.getContext()).load(App.getContext().getString(R.string.random_image_url))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

        void bind(Data dataEntity){
            this.data = dataEntity;
            nameTextView.setText(dataEntity.getName());
        }

        @Override
        public void onClick(View view) {
            Bundle args = new Bundle();
            args.putSerializable(MapActivity.DATA_ARG, data);

            Intent intent = new Intent(App.getContext(), MapActivity.class);
            intent.putExtras(args);
            App.getContext().startActivity(intent);
        }
    }
}
