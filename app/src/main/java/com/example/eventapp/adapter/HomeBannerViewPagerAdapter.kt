package com.example.eventapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventapp.R
import com.example.eventapp.model.image

class HomeBannerViewPagerAdapter(
    private val context: Context,
    private val bannerList: ArrayList<image>
) : RecyclerView.Adapter<HomeBannerViewPagerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_image_slider, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (bannerList.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(bannerList[position].welcomeImage)
                .into(holder.ivImages)
        }
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        var ivImages: ImageView = itemView.findViewById(R.id.ivImages)
    }

    override fun getItemCount(): Int {
        return  bannerList.size
    }
}