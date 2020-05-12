package com.puvendra.recipeapplication.database

import com.google.firebase.database.Exclude


data class recipeDatabase(
    @get:Exclude
    var recipeId: String? = null,
    var imageURL: String? = null,
    var foodType: String? = null,
    var name: String? = null,
    var ingredients: String? = null,
    var steps: String? = null,
    @get:Exclude
    var isDeleted: Boolean = false
)
