package com.rifqimfahmi.alldogbreeds.ui.breeds.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifqimfahmi.alldogbreeds.R
import com.rifqimfahmi.alldogbreeds.ui.base.helper.RecyclerViewActionListener
import com.rifqimfahmi.alldogbreeds.util.CommonUtils
import kotlinx.android.synthetic.main.item_dog_breed.view.*

/*
 * Created by Rifqi Mulya Fahmi on 19/02/18.
 */

class AdapterDogBreeds(context: Context) : RecyclerView.Adapter<AdapterDogBreeds.ItemDogBreed>() {

    val mDogBreed: ArrayList<String> = ArrayList()
    val mActionItemListener: RecyclerViewActionListener<String> = context as RecyclerViewActionListener<String>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemDogBreed {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_dog_breed, parent, false)
        return ItemDogBreed(view)
    }

    override fun getItemCount(): Int {
        return mDogBreed.size
    }

    override fun onBindViewHolder(holder: ItemDogBreed?, position: Int) {
        holder?.bind(mDogBreed[position], mActionItemListener)
    }

    fun addData(data: List<String>) {
        mDogBreed.addAll(data)
        notifyDataSetChanged()
    }


    fun removeAllData() {
        mDogBreed.clear()
    }

    class ItemDogBreed(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(breed: String, mActionItemListener: RecyclerViewActionListener<String>) {
            itemView.textview_breed.text = CommonUtils.uppercaseTheFirstLetter(breed)
            itemView.setOnClickListener {
                mActionItemListener.onItemClick(breed)
            }
        }
    }
}