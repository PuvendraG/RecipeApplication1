package com.puvendra.recipeapplication.DialogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.puvendra.recipeapplication.Interface.RecyclerViewClickListener
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.database.recipeDatabase
import com.puvendra.recipeapplication.recipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.choose_recipes_.*

class ChoosenRecipe(private val recipes : recipeDatabase) : DialogFragment(),
    RecyclerViewClickListener {

    private lateinit var viewModel : recipeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // to get data,  ViewModel is to encapsulate the data for a UI controller to let the data survive configuration change
        viewModel = ViewModelProvider(this).get(recipeViewModel::class.java)

        //inflaterge
        return inflater.inflate(R.layout.choose_recipes_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Seting the data, based on what has been choosen
        Picasso.get().load(recipes.imageURL).into(c_imageView)
        recipeName_.setText(recipes.name)
        ingredientsAll_.setText(recipes.ingredients)
        stepsR_.setText(recipes.steps)
    }

    override fun onRecyclerViewItemClicked(view: View, reciper: recipeDatabase) {

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return view
    }


}
