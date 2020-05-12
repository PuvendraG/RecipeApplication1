package com.puvendra.recipeapplication.Interface

import android.view.View
import com.puvendra.recipeapplication.database.recipeDatabase

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view:View, recipe: recipeDatabase)

}
