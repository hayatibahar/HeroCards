package com.cloud.herocards.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloud.herocards.R
import com.cloud.herocards.databinding.FragmentHeroCardBinding
import com.cloud.herocards.databinding.HeroRecyclerRowBinding
import com.cloud.herocards.viewmodel.HeroCardViewModel


class HeroCardFragment : Fragment() {
    private lateinit var viewModel : HeroCardViewModel
    private var heroId = 0
    private lateinit var dataBinding : FragmentHeroCardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_hero_card,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            heroId = HeroCardFragmentArgs.fromBundle(it).heroId
        }
        viewModel = ViewModelProviders.of(this).get(HeroCardViewModel::class.java)
        viewModel.getRoomDataById(heroId)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.heroLiveData.observe(viewLifecycleOwner,Observer{
            it?.let {
                dataBinding.selectedHero = it
            }
        })
    }

}