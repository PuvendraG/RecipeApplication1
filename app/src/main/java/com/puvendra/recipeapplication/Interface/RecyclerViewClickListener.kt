package com.puvendra.recipeapplication.Interface

import android.view.View
import android.view.ViewGroup
import com.puvendra.recipeapplication.database.recipeDatabase

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view:View, recipe: recipeDatabase)

    fun getView(position: Int, convertView: View?, parent: ViewGroup): View?
}
