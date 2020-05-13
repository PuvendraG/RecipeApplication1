package com.puvendra.recipeapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.puvendra.recipeapplication.database.recipeDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.edit_posted_recipes.*
import kotlinx.android.synthetic.main.recyler_view_layout.view.*
import java.net.URL


class EditerChosenRecipes(private val recipes : recipeDatabase) : DialogFragment() {

    private lateinit var viewModel: recipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)

        return inflater.inflate(R.layout.edit_posted_recipes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get().load(recipes.imageURL).into(imageView_edit)
        recipeName.setText(recipes.name)
        ingredientsAll.setText(recipes.ingredients)
        stepsR.setText(recipes.steps)

        submitBtn.setOnClickListener {
            val name = recipeName.text.toString().trim()
            val ingredients = ingredientsAll.text.toString().trim()
            val steps = stepsR.text.toString().trim()

            recipes.name = name
            recipes.ingredients = ingredients
            recipes.steps = steps

            viewModel.updateRecipe(recipes)

        }
    }



}
