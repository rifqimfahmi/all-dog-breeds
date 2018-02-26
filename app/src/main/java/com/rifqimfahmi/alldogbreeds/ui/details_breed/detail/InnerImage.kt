package com.rifqimfahmi.alldogbreeds.ui.details_breed.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifqimfahmi.alldogbreeds.R
import kotlinx.android.synthetic.main.fragment_breed_image.*

/*
 * Created by Rifqi Mulya Fahmi on 22/02/18.
 */
 
class InnerImage : Fragment() {

    companion object {
        fun newInstance(link: String): InnerImage {
            val innerImage = InnerImage()
            val bundle = Bundle()
            bundle.putString(ImageDetail.SELECTED_LINK, link)
            innerImage.arguments = bundle
            return innerImage
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_breed_image, container, false)
    }

    override fun onResume() {
        super.onResume()
        Glide.with(this)
                .load(getUrl())
                .apply(RequestOptions().placeholder(R.drawable.loading_image_breed))
                .into(image_breed_photo)
    }

    private fun getUrl(): String? {
        return arguments?.getString(ImageDetail.SELECTED_LINK)
    }
}