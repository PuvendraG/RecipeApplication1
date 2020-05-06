package com.puvendra.recipeapplication.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.RecipesAdapter
import com.puvendra.recipeapplication.databinding.FragmentViewAllRecipesBinding
import com.puvendra.recipeapplication.recipeViewModel
import kotlinx.android.synthetic.main.fragment_view_all_recipes.*


class ViewAllRecipes : Fragment() {

    private lateinit var viewModel : recipeViewModel
    private val adapter = RecipesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_view_all_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.food_list, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view_recipes.adapter = adapter
        viewModel.fetchRecipe()
        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            adapter.setRecipes(it)
        })

    }

}
