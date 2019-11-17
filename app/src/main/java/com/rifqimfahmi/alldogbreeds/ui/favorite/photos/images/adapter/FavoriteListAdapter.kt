package com.rifqimfahmi.alldogbreeds.ui.favorite.photos.images.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.db.model.Dog
import com.rifqimfahmi.alldogbreeds.ui.favorite.photos.images.FavoriteList
import kotlinx.android.synthetic.main.item_dog_images.view.*
import kotlinx.android.synthetic.main.item_section_title.view.*

/*
 * Created by Rifqi Mulya Fahmi on 01/03/18.
 */

class FavoriteListAdapter (favoriteList: FavoriteList) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mDatas: ArrayList<Any> = ArrayList()
    var mOneBreed: ArrayList<Dog> = ArrayList()
    var mFavoriteDogClickListener: ItemImage.ActionListener

    val VT_IMAGE = 0
    val VT_TITLE = 1

    init {
        mFavoriteDogClickListener = favoriteList as ItemImage.ActionListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VT_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog_images, parent, false)
                return ItemImage(view)
            }
            VT_TITLE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section_title, parent, false)
                return ItemTitle(view)
            }
        }

        throw IllegalAccessError("The data must contain Dog class and String only")
    }

    override fun getItemCount(): Int = mDatas.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VT_IMAGE -> {
                (holder as ItemImage).bind(mDatas[position] as Dog, mDatas, mFavoriteDogClickListener)
            }
            VT_TITLE -> {
                (holder as ItemTitle).bind(mDatas[position] as String)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDatas[position] is Dog) {
            VT_IMAGE
        } else {
            VT_TITLE
        }
    }

    fun addData(sectioned: ArrayList<Any>) {
        mDatas = sectioned
        notifyDataSetChanged()
    }

    class ItemImage(itemView: View) : RecyclerView.ViewHolder(itemView) {

        interface ActionListener {
            fun onFavoriteDogClicked(dog: Dog, breeds: ArrayList<Any>)
        }

        fun bind(dog: Dog, oneBreed: ArrayList<Any>, mFavoriteDogClickListener: ActionListener) {
            Glide.with(itemView)
                    .load(dog.link)
                    .apply(RequestOptions().placeholder(R.drawable.loading_image_breed))
                    .into(itemView.image_photo)
            itemView.setOnClickListener {
                mFavoriteDogClickListener.onFavoriteDogClicked(dog, oneBreed)
            }
        }
    }

    class ItemTitle(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: String) {
            itemView.tv_title.text = title
        }
    }
}