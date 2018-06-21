package com.example.amiltonedev_dt016.kotlinacademy1.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amiltonedev_dt016.kotlinacademy1.R
import com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper.ComicViewDataWrapper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicsListAdapter(private val context: Context) : RecyclerView.Adapter<ComicsListAdapter.ComicViewHolder>() {

    private var comicsList: List<ComicViewDataWrapper> = ArrayList()
    var comicClickedListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val view = inflater.inflate(R.layout.item_comic, parent, false)
        return ComicViewHolder(context, view, comicClickedListener)
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }

    override fun onBindViewHolder(holder: ComicViewHolder?, position: Int) {
        holder!!.bind(comicsList[position])
    }

    fun updateComicsList(comicsList: List<ComicViewDataWrapper>) {
        this.comicsList = comicsList
        notifyDataSetChanged()
    }

    class ComicViewHolder(
            private val context: Context,
            private val view: View,
            private val listener: (String) -> Unit) : RecyclerView.ViewHolder(view) {

        private val title = view.item_comic_title!!
        private val image = view.item_comic_image!!
        private val date = view.item_comic_date!!

        fun bind(comic: ComicViewDataWrapper) {
            title.text = comic.title
            date.text = comic.date

            Picasso.with(context).load(comic.coverImagePath).into(image)

            view.setOnClickListener { listener(comic.comic.id) }
        }
    }
}