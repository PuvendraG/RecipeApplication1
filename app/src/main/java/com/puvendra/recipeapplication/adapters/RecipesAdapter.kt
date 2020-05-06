package com.puvendra.recipeapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puvendra.recipeapplication.ChoosenRecipe
import com.puvendra.recipeapplication.EditChoosenRecipe
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.RecyclerViewClickListener
import com.puvendra.recipeapplication.database.recipeDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyler_view_layout.view.*

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipeViewModel>(){
    private var recipes = mutableListOf<recipeDatabase>()
    var listener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        RecipeViewModel(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.recyler_view_layout,
                    parent,
                    false
                )
        )

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeViewModel, position: Int) {

        holder.view.recipe_name.text = recipes[position].name
        Picasso.get().load(recipes[position].imageURL).into( holder.view.img_recipe)

    }

    fun setRecipes(recipes_: List<recipeDatabase>){
        this.recipes = recipes_ as MutableList<recipeDatabase>
        notifyDataSetChanged()
    }

    class RecipeViewModel(val view:  View) : RecyclerView.ViewHolder(view){

        init {
            view.setOnClickListener {
                println("test" )
                val intent = Intent(view.context , ChoosenRecipe::class.java )
                view.context.startActivity(intent)

            }
        }
    }



}