package com.puvendra.recipeapplication.DialogFragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.database.recipeDatabase
import com.puvendra.recipeapplication.recipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.edit_posted_recipes.*
import kotlinx.android.synthetic.main.edit_posted_recipes.ingredientsAll
import kotlinx.android.synthetic.main.edit_posted_recipes.recipeName
import kotlinx.android.synthetic.main.edit_posted_recipes.stepsR
import kotlinx.android.synthetic.main.edit_posted_recipes.submitBtn


class EditerChosenRecipes(private val recipes : recipeDatabase) : DialogFragment() {

    private lateinit var viewModel: recipeViewModel
    lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this).get(recipeViewModel::class.java)

        return inflater.inflate(R.layout.edit_posted_recipes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get().load(recipes.imageURL).into(imageView_edit)
        recipeName.setText(recipes.name)
        ingredientsAll.setText(recipes.ingredients)
        stepsR.setText(recipes.steps)

        storageReference = FirebaseStorage.getInstance().getReference("image_upload/" + selectedImage)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.recipe_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        imageView_edit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent, 0)
        }

        submitBtn.setOnClickListener {
            val name = recipeName.text.toString().trim()
            val ingredients = ingredientsAll.text.toString().trim()
            val steps = stepsR.text.toString().trim()

            recipes.imageURL = selectedImage.toString()
            recipes.name = name
            recipes.ingredients = ingredients
            recipes.steps = steps

            viewModel.updateRecipe(recipes)

        }
    }

    var selectedImage: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
                    imageView_edit.setImageURI(data?.data)
                }
            }
        }
    }



}
