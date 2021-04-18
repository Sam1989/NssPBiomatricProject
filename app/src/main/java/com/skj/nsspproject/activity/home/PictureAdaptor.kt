package com.skj.nsspproject.activity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skj.nsspproject.R
import com.skj.nsspproject.databinding.ItemPictureBinding
import com.skj.nsspproject.model.HitsItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class PictureAdaptor(
    var ReferArray: ArrayList<HitsItem?>
) : RecyclerView.Adapter<PictureAdaptor.ViewHolder>() {

    fun setNewData(newData: ArrayList<HitsItem?>) {
        ReferArray = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPictureBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = ReferArray[position]
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return ReferArray.size
    }

    inner class ViewHolder(val mBinding: ItemPictureBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: HitsItem) {

            Picasso.get().invalidate(item.previewURL)

            Picasso.get().load(item.previewURL)
                .networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                .placeholder(R.mipmap.icon_noimage)
                .error(R.mipmap.icon_noimage)
                .into(mBinding.restImage)

        }
    }

}