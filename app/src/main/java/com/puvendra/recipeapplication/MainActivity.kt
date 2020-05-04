package com.puvendra.recipeapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import com.puvendra.recipeapplication.fragments.AddRecipes
import com.puvendra.recipeapplication.fragments.EditPostedRecipes
import com.puvendra.recipeapplication.fragments.ViewAllRecipes


class MainActivity : AppCompatActivity() {
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
        }
        addBtn.setOnClickListener(){
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, addRecipes)
            fragmentTransaction.commit()
        }
        editBtn.setOnClickListener(){
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.myFragment, editPostedRecipes)
            fragmentTransaction.commit()
        }
    }


}
