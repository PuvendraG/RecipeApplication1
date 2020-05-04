package com.puvendra.recipeapplication

import android.app.ProgressDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.puvendra.recipeapplication.database.NODE_RECIPES
import com.puvendra.recipeapplication.database.recipeDatabase

class recipeViewModel: ViewModel() {

    private val _result = MutableLiveData<Exception?>()
    val result : LiveData<Exception?>get()=_result

    fun addRecipe(recipes: recipeDatabase){
         val dbRecipes = FirebaseDatabase.getInstance().getReference(NODE_RECIPES)
        recipes.recipeId = dbRecipes.push().key.toString() // Id of the recipes
        dbRecipes.child(recipes.recipeId!!).setValue(recipes).addOnCompleteListener {
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
     }

}
