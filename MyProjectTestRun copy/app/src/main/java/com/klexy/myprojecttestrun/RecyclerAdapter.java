package com.klexy.myprojecttestrun;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ImageViewHolder>{

    private int[] images;

    public RecyclerAdapter(int[] images1){
        this.images = images1;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.album_layout,viewGroup,false);

        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {

        // if we need to add text header to the image if available, then take [i = pos]


int image_id = images[i];
        viewHolder.Album.setImageResource(image_id);
        //viewHolder.AlbumTitle.setText("image :" + i);


    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView Album;

        public ImageViewHolder(View view){
            super(view);

            Album = view.findViewById(R.id.albumView);

        }
    }
}

