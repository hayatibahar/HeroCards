package com.cloud.herocards.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cloud.herocards.R
import com.cloud.herocards.databinding.HeroRecyclerRowBinding
import com.cloud.herocards.model.Hero
import com.cloud.herocards.view.HeroListFragmentDirections
import kotlinx.android.synthetic.main.hero_recycler_row.view.*

class HeroRecyclerAdapter(val heroList : ArrayList<Hero>) : RecyclerView.Adapter<HeroRecyclerAdapter.HeroViewHolder>(),HeroClickListener {
    class HeroViewHolder(var view: HeroRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HeroRecyclerRowBinding>(inflater, R.layout.hero_recycler_row, parent,false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.view.hero = heroList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateHeroList(newHeroList : List<Hero>){
        heroList.clear()
        heroList.addAll(newHeroList)
        notifyDataSetChanged()
    }

    override fun heroClicked(view: View) {
        val uuid = view.hero_uuid.text.toString().toIntOrNull()
        uuid?.let {
            val action = HeroListFragmentDirections.actionHeroListFragmentToHeroCardFragment(uuid)
            Navigation.findNavController(view).navigate(action)
        }
    }
}