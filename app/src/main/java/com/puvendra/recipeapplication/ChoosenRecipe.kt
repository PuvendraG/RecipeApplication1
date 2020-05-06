package com.puvendra.recipeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puvendra.recipeapplication.adapters.RecipesAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.choose_recipes_.*
import kotlinx.android.synthetic.main.recyler_view_layout.view.*

class ChoosenRecipe : AppCompatActivity() {

    private lateinit var viewModel : recipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_recipes_)

        viewModel.fetchRecipe()


    }


}
