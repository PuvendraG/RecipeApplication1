package com.puvendra.recipeapplication.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.puvendra.recipeapplication.*
import com.puvendra.recipeapplication.DialogFragments.EditerChosenRecipes
import com.puvendra.recipeapplication.Interface.RecyclerViewClickListener
import com.puvendra.recipeapplication.adapters.ReciperEditAdapter
import com.puvendra.recipeapplication.database.recipeDatabase
import kotlinx.android.synthetic.main.fragment_edit_recipes.*
import kotlinx.android.synthetic.main.recyler_view_layout.*



class EditPostedRecipes : Fragment(), RecyclerViewClickListener{

    private lateinit var viewModel : recipeViewModel
    private val recipesAdapter = ReciperEditAdapter()

    var spinner:Spinner? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)

        //layout inflater
        return inflater.inflate(R.layout.fragment_edit_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Internal List for the categories
       val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.food_list, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        edit_posted_recipes.adapter = adapter
        //Attempt to make a filter, get the selected item and use an query function to get back the foodtype selected
        edit_posted_recipes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val str: String = edit_posted_recipes.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recipesAdapter.listener = this

        //getting the recycler view to be populated by the data
        recyclerEdit_view_recipes.adapter = recipesAdapter
        viewModel.fetchRecipe()
        viewModel.recipe.observe(viewLifecycleOwner, Observer { recipesAdapter.setRecipes(it) })

    }

    override fun onRecyclerViewItemClicked(view: View, recipe: recipeDatabase) {
        recipes_items.setOnClickListener {
            //by clicking any item of the recycler view it will send you to EditerChoosenRecipes to edit the chosen recipe to edit
            EditerChosenRecipes(
                recipe
            )
                .show(childFragmentManager, "")
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return view
    }



}





