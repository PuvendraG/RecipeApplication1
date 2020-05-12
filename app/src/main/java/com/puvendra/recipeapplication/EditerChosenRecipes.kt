package com.puvendra.recipeapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.puvendra.recipeapplication.database.recipeDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.edit_posted_recipes.*
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

        Glide.with(this).load(recipes.imageURL).into(imageView_edit)
        recipeName.setText(recipes.name)
        ingredientsAll.setText(recipes.ingredients)
        stepsR.setText(recipes.steps)
    }



}
