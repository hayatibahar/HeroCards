package com.cloud.herocards.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.herocards.R
import com.cloud.herocards.adapter.HeroRecyclerAdapter
import com.cloud.herocards.viewmodel.HeroListViewModel
import kotlinx.android.synthetic.main.fragment_hero_list.*


class HeroListFragment : Fragment() {
    private lateinit var viewModel : HeroListViewModel
    private val recyclerHeroAdapter = HeroRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HeroListViewModel::class.java)
        viewModel.refreshData()

        heroListRecyclerView.layoutManager = LinearLayoutManager(context)
        heroListRecyclerView.adapter = recyclerHeroAdapter

        swipeRefreshLayout.setOnRefreshListener {
            loading.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            heroListRecyclerView.visibility = View.GONE
            viewModel.refreshFromWeb()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

    }

    fun observeLiveData(){

        viewModel.heroes.observe(viewLifecycleOwner, Observer {
            it?.let {
                heroListRecyclerView.visibility = View.VISIBLE
                recyclerHeroAdapter.updateHeroList(it)
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    errorMessage.visibility = View.VISIBLE
                    heroListRecyclerView.visibility = View.GONE
                }
                else{
                    errorMessage.visibility = View.GONE
                }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    heroListRecyclerView.visibility = View.GONE
                    errorMessage.visibility = View.GONE
                    loading.visibility = View.VISIBLE
                }
                else{
                    loading.visibility = View.GONE
                }
            }
        })
    }

}