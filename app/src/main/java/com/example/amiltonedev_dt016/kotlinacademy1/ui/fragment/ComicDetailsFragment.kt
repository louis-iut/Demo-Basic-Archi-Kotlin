package com.example.amiltonedev_dt016.kotlinacademy1.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amiltonedev_dt016.kotlinacademy1.R
import com.example.amiltonedev_dt016.kotlinacademy1.observeSafe
import com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel.ComicDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_comic_details.*
import org.koin.android.architecture.ext.viewModel

class ComicDetailsFragment: Fragment() {

    companion object {
        private const val COMIC_ID_KEY = "ComicDetailsFragment.COMIC_ID_KEY"

        fun newInstance(id: String): ComicDetailsFragment {
            val fragment = ComicDetailsFragment()
            val args = Bundle()

            args.putString(COMIC_ID_KEY, id)
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var comicId: String
    private val viewModel: ComicDetailsViewModel by viewModel()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (arguments != null && arguments.containsKey(COMIC_ID_KEY)) {
            comicId = arguments.getString(COMIC_ID_KEY)
        }

        return inflater.inflate(R.layout.fragment_comic_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startObserveComic(comicId)

        viewModel.comicLiveData.observeSafe(this) {
            fragment_comic_detail_title.text = it.title
            fragment_comic_detail_code.text = it.diamondCode
            fragment_comic_detail_creators.text = it.creators
            fragment_comic_detail_date.text = it.date
            Picasso.with(context).load(it.coverImagePath).into(fragment_comic_detail_image)
        }
    }
}