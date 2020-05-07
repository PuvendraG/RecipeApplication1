package com.puvendra.recipeapplication.Interface

import com.puvendra.recipeapplication.database.recipeDatabase

interface IFirebaseLoadDone {
    fun onFirebaseLoadSuccess(recipeList: List<recipeDatabase>)
    fun onFirebaseLoadFailure (message: String)
}