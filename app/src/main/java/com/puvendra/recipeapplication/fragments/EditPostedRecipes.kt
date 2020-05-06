package com.puvendra.recipeapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.RecipesAdapter
import com.puvendra.recipeapplication.recipeViewModel
import kotlinx.android.synthetic.main.fragment_edit_recipes.*


class EditPostedRecipes : Fragment() {
    private lateinit var viewModel : recipeViewModel
    private val recipesAdapter = RecipesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_edit_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.food_list, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        edit_posted_recipes.adapter = adapter
        edit_posted_recipes.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val str: String = edit_posted_recipes.getSelectedItem().toString()
                //recipeName.setText(str)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerEdit_view_recipes.adapter = recipesAdapter
        viewModel.fetchRecipe()
        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            recipesAdapter.setRecipes(it)
        })

    }

}
