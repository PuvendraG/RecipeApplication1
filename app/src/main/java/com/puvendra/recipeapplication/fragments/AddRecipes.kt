package com.puvendra.recipeapplication.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.database.recipeDatabase
import com.puvendra.recipeapplication.recipeViewModel
import kotlinx.android.synthetic.main.fragment_add_recipes.*



class AddRecipes : Fragment() {

    private lateinit var viewModel: recipeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_recipes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.recipe_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        submitBtn.setOnClickListener {
            val recipe_Name = recipeName.text.toString().trim()
            val recipe_Type = recipeType.text.toString().trim()
            val recipe_ingredients = ingredientsAll.text.toString().trim()
            val recipe_steps = stepsR.text.toString().trim()

            val recipe = recipeDatabase()

            recipe.name = recipe_Name
            recipe.foodType = recipe_Type
            recipe.ingredients = recipe_ingredients
            recipe.steps = recipe_steps
            viewModel.addRecipe(recipe)


        }
    }




}
