package com.puvendra.recipeapplication

import android.view.View
import com.puvendra.recipeapplication.database.recipeDatabase


interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view: View, reciper:recipeDatabase)
}