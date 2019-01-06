package com.example.jahanveenarang.mjsongs;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MainViewHolder>

{

    Context context;
    public List<ModalClass> dataLists;

    LinearLayout linearLayout ;
    public ImageView imageView ;
    public MediaPlayer mediaPlayer;

    private int rowLayout;

    public AdapterClass(Context context, List<ModalClass> dataLists , int rowLayout) {
        this.context = context;
        this.dataLists = dataLists;
        this.rowLayout = rowLayout ;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_file, parent, false);
        return new MainViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        ;
        holder.songName.setText(dataLists.get(position).getNameOfSong());

       // Glide.with(context).load(dataLists.get(position).getThumbnail()).into(holder.s_img_dish);
        //for image method
        applyProfilePicture(holder,dataLists,position);

        //for song method
        playmusic(holder,dataLists,position);
    }

    //for song
    private void playmusic(MainViewHolder holder, List<ModalClass> dataList, int position) {

        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(dataList.get(position).getSong());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();
            }
        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                mediaPlayer.start();
//
//            }


     //   });

        // pause.setOnClickListener(new View.OnClickListener()

//        {
//            @Override
//            public void onClick (View view){
//                mediaPlayer.pause();
//            }
//        });
//        stop.setOnClickListener(new View.OnClickListener()
//
//        {
//            @Override
//            public void onClick (View view){
//                mediaPlayer.stop();
//            }
//        });
//    }
    }
    //for image
    private void applyProfilePicture(MainViewHolder holder, List<ModalClass> dataLists, final int position) {
        if (!TextUtils.isEmpty(dataLists.get(position).getImage())) {
            Glide.with(context).load(dataLists.get(position).getImage())
                    .thumbnail(0.5f)
                    .into(holder.imageView);

        } else {
            holder.imageView.setImageResource(R.drawable.image);

        }
    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView songName;
        ImageView imageView;

        public MainViewHolder(View itemView) {
            super(itemView);

            songName=itemView.findViewById(R.id.tv_songs);
            imageView=itemView.findViewById(R.id.imageview);
        }
    }
}
