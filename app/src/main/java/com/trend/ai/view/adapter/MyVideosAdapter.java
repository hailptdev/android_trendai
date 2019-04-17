package com.trend.ai.view.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder;
import com.allattentionhere.autoplayvideos.AAH_VideosAdapter;
import com.squareup.picasso.Picasso;
import com.trend.ai.R;
import com.trend.ai.model.api.response.Media;

import java.util.List;


public class MyVideosAdapter extends AAH_VideosAdapter {

    private final List<Media> list;
    private final Picasso picasso;
    private static final int TYPE_VIDEO = 0, TYPE_TEXT = 1;

    public class MyViewHolder extends AAH_CustomViewHolder {
        final TextView tv,tvName,tvNameScreen,tvContent;
        final ImageView img_vol, img_playback;
        //to mute/un-mute video (optional)
        boolean isMuted;

        public MyViewHolder(View x) {
            super(x);
            tv = x.findViewById(R.id.tv);
            tvName = x.findViewById(R.id.tvName);
            tvNameScreen = x.findViewById(R.id.tvNameScreen);
            tvContent = x.findViewById(R.id.tvContent);
            img_vol =  x.findViewById(R.id.img_vol);
            img_playback = x.findViewById(R.id.img_playback);


        }

        //override this method to get callback when video starts to play
        @Override
        public void videoStarted() {
            super.videoStarted();
            img_playback.setImageResource(R.drawable.ic_pause);
            if (isMuted) {
                muteVideo();
                img_vol.setImageResource(R.drawable.ic_mute);
            } else {
                unmuteVideo();
                img_vol.setImageResource(R.drawable.ic_unmute);
            }
        }

        @Override
        public void pauseVideo() {
            super.pauseVideo();
            img_playback.setImageResource(R.drawable.ic_play);
        }
    }


    public class MyTextViewHolder extends AAH_CustomViewHolder {
        final TextView tv;

        public MyTextViewHolder(View x) {
            super(x);
            tv = x.findViewById(R.id.tv);
        }
    }

    public MyVideosAdapter(List<Media> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    @Override
    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_TEXT) {
            View itemView1 = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_text, parent, false);
            return new MyTextViewHolder(itemView1);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vh_video_player2, parent, false);
            return new MyViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(final AAH_CustomViewHolder holder, int position) {

        Media data = list.get(position);
        if (data.getEntities() == null && data.getEntities().getMedia() != null && data.getEntities().getMedia().get(0).getVideoInfo() != null && data.getEntities().getMedia().get(0).getVideoInfo().getVariants() != null) {
//            ((MyTextViewHolder) holder).tv.setText(list.get(position).getName());
        } else {
            ((MyViewHolder) holder).tv.setText("");

            String urlVideo = data.getEntities().getMedia().get(0).getVideoInfo().getVariants().get(1).getUrl();
            String urlImage = data.getEntities().getMedia().get(0).getMediaUrl();
            //todo
            holder.setImageUrl(urlImage);
            holder.setVideoUrl(urlVideo);

            //load image into imageview
            picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());

            holder.setLooping(true); //optional - true by default

            ((MyViewHolder) holder).tvName.setText(data.getUser().getName());
            ((MyViewHolder) holder).tvNameScreen.setText(data.getUser().getScreenName());
            ((MyViewHolder) holder).tvContent.setText(data.getText());

//            Glide.with(context).load(data.user!!.profileImageUrl).into(imvAva)


            //to play pause videos manually (optional)
            ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.isPlaying()) {
                        holder.pauseVideo();
                        holder.setPaused(true);
                    } else {
                        holder.playVideo();
                        holder.setPaused(false);
                    }
                }
            });

            //to mute/un-mute video (optional)
            ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((MyViewHolder) holder).isMuted) {
                        holder.unmuteVideo();
                        ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_unmute);
                    } else {
                        holder.muteVideo();
                        ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_mute);
                    }
                    ((MyViewHolder) holder).isMuted = !((MyViewHolder) holder).isMuted;
                }
            });

            ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (1==2) {
            return TYPE_TEXT;
        } else return TYPE_VIDEO;
    }


}