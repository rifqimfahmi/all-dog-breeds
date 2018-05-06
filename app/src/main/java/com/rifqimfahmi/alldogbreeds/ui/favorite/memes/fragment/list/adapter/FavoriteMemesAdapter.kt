package com.rifqimfahmi.alldogbreeds.ui.favorite.memes.fragment.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.data.db.model.DogMeme
import kotlinx.android.synthetic.main.item_dog_images.view.*

/*
 * Created by Rifqi Mulya Fahmi on 08/03/18.
 */
 
class FavoriteMemesAdapter(actionListener: MemeVH.ActionListener) : RecyclerView.Adapter<FavoriteMemesAdapter.MemeVH>() {

    var mActionListener = actionListener
    var mData: ArrayList<DogMeme> = ArrayList()
        set(value) {
            mData.clear()
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog_images, parent, false)
        return MemeVH(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MemeVH, position: Int) {
        holder.bind(mData[position], mData, mActionListener)
    }

    class MemeVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        interface ActionListener {
            fun onMemeClicked(selectedMeme: DogMeme, memes: ArrayList<DogMeme>)
        }

        fun bind(dogMeme : DogMeme, memes: ArrayList<DogMeme>, actionListener: ActionListener) {
            Glide.with(itemView.context)
                    .load(dogMeme.preview_gif)
                    .into(itemView.image_photo)
            itemView.image_photo.setOnClickListener {
                actionListener.onMemeClicked(dogMeme, memes)
            }
        }
    }
}