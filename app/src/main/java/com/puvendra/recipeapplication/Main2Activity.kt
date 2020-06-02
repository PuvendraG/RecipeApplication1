  package com.puvendra.recipeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.puvendra.recipeapplication.fragments.AddRecipes
import com.puvendra.recipeapplication.fragments.EditPostedRecipes
import com.puvendra.recipeapplication.fragments.ViewAllRecipes

  class Main2Activity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val addRecipes = AddRecipes()
    private val editPostedRecipes = EditPostedRecipes()
    private val viewAllRecipes = ViewAllRecipes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBtn = findViewById<Button>(R.id.addRecipes)
        val viewBtn = findViewById<Button>(R.id.viewRecipes)
        val editBtn = findViewById<Button>(R.id.editRecipes)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, viewAllRecipes)
        fragmentTransaction.commit()

        viewBtn.setOnClickListener(){
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, viewAllRecipes)
            fragmentTransaction.commit()
        } //main fragment to view all the recipes

        addBtn.setOnClickListener(){
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, addRecipes)
            fragmentTransaction.commit()
        } // to add recipes
        editBtn.setOnClickListener(){
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, editPostedRecipes)
            fragmentTransaction.commit()
        } // Listed recipes where you can go edit them

    }


}
