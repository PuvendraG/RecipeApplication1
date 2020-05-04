package com.puvendra.recipeapplication.fragments


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.databinding.FragmentViewAllRecipesBinding
import kotlinx.android.synthetic.main.fragment_view_all_recipes.*

class ViewAllRecipes : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentViewAllRecipesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_all_recipes, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.food_list, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

    }

}
