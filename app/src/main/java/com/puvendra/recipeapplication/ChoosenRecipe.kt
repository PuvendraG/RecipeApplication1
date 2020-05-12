package com.puvendra.recipeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.puvendra.recipeapplication.Interface.RecyclerViewClickListener
import com.puvendra.recipeapplication.adapters.RecipesAdapter
import com.puvendra.recipeapplication.database.recipeDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.choose_recipes_.*
import kotlinx.android.synthetic.main.edit_posted_recipes.*
import kotlinx.android.synthetic.main.recyler_view_layout.view.*

class ChoosenRecipe(private val recipes : recipeDatabase) : DialogFragment(),
    RecyclerViewClickListener {

    private lateinit var viewModel : recipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)

        return inflater.inflate(R.layout.choose_recipes_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get().load(recipes.imageURL).into(c_imageView)
        recipeName_.setText(recipes.name)
        ingredientsAll_.setText(recipes.ingredients)
        stepsR_.setText(recipes.steps)
    }

    override fun onRecyclerViewItemClicked(view: View, reciper: recipeDatabase) {

    }




}
