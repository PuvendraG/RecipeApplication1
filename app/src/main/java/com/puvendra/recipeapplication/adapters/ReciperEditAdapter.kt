package com.puvendra.recipeapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puvendra.recipeapplication.Interface.RecyclerViewClickListener
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.database.recipeDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyler_view_layout.view.*

class ReciperEditAdapter : RecyclerView.Adapter<ReciperEditAdapter.RecipeEditViewModel>() {


    private var recipes = mutableListOf<recipeDatabase>()
    var listener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReciperEditAdapter.RecipeEditViewModel(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.recyler_view_layout,
                    parent,
                    false
                )
        )

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeEditViewModel, position: Int) {
        holder.view.recipe_name.text = recipes[position].name
        Picasso.get().load(recipes[position].imageURL).into(holder.view.img_recipe)
        holder.view.recipes_items.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it,recipes[position]) }

    }


    fun setRecipes(recipes_: List<recipeDatabase>) {
        this.recipes = recipes_ as MutableList<recipeDatabase>
        notifyDataSetChanged()
    }

    class RecipeEditViewModel(val view: View) : RecyclerView.ViewHolder(view) {

    }




}