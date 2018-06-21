package com.example.amiltonedev_dt016.kotlinacademy1.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amiltonedev_dt016.kotlinacademy1.R
import com.example.amiltonedev_dt016.kotlinacademy1.observeSafe
import com.example.amiltonedev_dt016.kotlinacademy1.ui.adapter.ComicsListAdapter
import com.example.amiltonedev_dt016.kotlinacademy1.ui.navigator.listener.ComicsListFragmentNavigatorListener
import com.example.amiltonedev_dt016.kotlinacademy1.ui.viewmodel.ComicsListViewModel
import kotlinx.android.synthetic.main.fragment_comics_list.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class ComicsListFragment: Fragment() {

    companion object {
        fun newInstance(): ComicsListFragment {
            return ComicsListFragment()
        }
    }

    private val viewModel: ComicsListViewModel by viewModel()
    private val comicsListAdapter: ComicsListAdapter by inject()
    private val navigatorListener: ComicsListFragmentNavigatorListener by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comics_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        viewModel.comicsLiveData.observeSafe(this) {
            comicsListAdapter.updateComicsList(it)
        }
    }

    private fun setUpRecyclerView() {
        comicsListAdapter.comicClickedListener = {
            comicId -> navigatorListener.gotToComicDetails(comicId)
        }

        fragment_comics_list_recycler_view.layoutManager = LinearLayoutManager(activity)
        fragment_comics_list_recycler_view.adapter = comicsListAdapter
    }
}