package com.puvendra.recipeapplication.fragments

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.database.recipeDatabase
import com.puvendra.recipeapplication.recipeViewModel
import kotlinx.android.synthetic.main.fragment_add_recipes.*


class AddRecipes : Fragment() {

    private lateinit var viewModel: recipeViewModel
    lateinit var storageReference: StorageReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_recipes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    var selectedImage: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            val uri = data.data
            val uploadTask = storageReference!!.putFile(uri!!)

            val task = uploadTask.continueWithTask{
                    task ->
                if (!task.isSuccessful){
                    Toast.makeText(context,"failed", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl }.addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val downloadUri = task.result
                    selectedImage = downloadUri!!
                    Log.d("Direct Link ", selectedImage.toString())
                    image_selector.setImageURI(data?.data)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        storageReference = FirebaseStorage.getInstance().getReference("image_upload")

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.recipe_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        image_selector.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent, 0)
        }


        submitBtn.setOnClickListener {
            val recipe_Name = recipeName.text.toString().trim()
            val recipe_Type = recipeType.text.toString().trim()
            val recipe_ingredients = ingredientsAll.text.toString().trim()
            val recipe_steps = stepsR.text.toString().trim()

            val recipe = recipeDatabase()
            Log.d("Direct_Link ", selectedImage.toString())
            recipe.imageURL = selectedImage.toString()
            recipe.name = recipe_Name
            recipe.foodType = recipe_Type
            recipe.ingredients = recipe_ingredients
            recipe.steps = recipe_steps
            viewModel.addRecipe(recipe)

        }

    }





}
