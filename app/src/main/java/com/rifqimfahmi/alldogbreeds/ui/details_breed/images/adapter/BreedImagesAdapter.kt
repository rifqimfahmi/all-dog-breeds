package com.rifqimfahmi.alldogbreeds.ui.details_breed.images.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.helper.RecyclerViewActionListener
import com.rifqimfahmi.alldogbreeds.ui.base.helper.RecyclerViewActionListenerWithAnimation
import com.rifqimfahmi.alldogbreeds.ui.details_breed.images.BreedImages
import kotlinx.android.synthetic.main.item_dog_images.view.*

/*
 * Created by Rifqi Mulya Fahmi on 22/02/18.
 */
 
class BreedImagesAdapter(listener: BreedImages) : RecyclerView.Adapter<BreedImagesAdapter.ItemBreedImage>() {

    val mImagesLink: ArrayList<String> = ArrayList()
    val mActionListener: RecyclerViewActionListener<String> = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBreedImage {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog_images, parent, false)
        return ItemBreedImage(view)
    }

    override fun getItemCount(): Int {
        return mImagesLink.size
    }

    override fun onBindViewHolder(holder: ItemBreedImage, position: Int) {
        holder?.onBind(mImagesLink[position], mActionListener)

        ViewCompat.setTransitionName(holder.itemView, "${position}_dog_image")
    }

    fun addData(imagesLink: ArrayList<String>) {
        mImagesLink.addAll(imagesLink)
        notifyDataSetChanged()
    }


    class ItemBreedImage(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(image: String, listener: RecyclerViewActionListener<String>) {
            Glide.with(itemView)
                    .load(image)
                    .apply(RequestOptions().placeholder(R.drawable.loading_image_breed))
                    .into(itemView.image_photo)
            itemView.setOnClickListener {
                listener.onItemClick(image)
            }
        }
    }
}