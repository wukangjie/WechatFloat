package com.me.wechatfloat;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomHolder> {

    private List<Drawable> mDrawables;

    public MainAdapter(List<Drawable> drawables) {
        mDrawables = drawables;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.main_item, null);
        CustomHolder holder = new CustomHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        holder.mImageView.setImageDrawable(mDrawables.get(position));
    }

    @Override
    public int getItemCount() {
        return mDrawables.size();
    }

     static class CustomHolder extends RecyclerView.ViewHolder{
        private  ImageView mImageView;
        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.main_image);

        }
    }
}
