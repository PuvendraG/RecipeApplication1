package com.puvendra.recipeapplication.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.*
import com.puvendra.recipeapplication.DialogFragments.ChoosenRecipe
import com.puvendra.recipeapplication.Interface.IFirebaseLoadDone
import com.puvendra.recipeapplication.Interface.RecyclerViewClickListener
import com.puvendra.recipeapplication.MainActivity
import com.puvendra.recipeapplication.R
import com.puvendra.recipeapplication.adapters.RecipesAdapter
import com.puvendra.recipeapplication.database.recipeDatabase
import com.puvendra.recipeapplication.recipeViewModel
import kotlinx.android.synthetic.main.fragment_view_all_recipes.*
import kotlinx.android.synthetic.main.recyler_view_layout.*


class ViewAllRecipes : Fragment(), IFirebaseLoadDone, RecyclerViewClickListener {

    lateinit var reciperRef : DatabaseReference
    lateinit var iFirebaseLoadDone : IFirebaseLoadDone
    private lateinit var viewModel : recipeViewModel
    private val recipesAdapter = RecipesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //Firebase List Spinner
        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)

        //Looks into a file called recipes in the firebase
        reciperRef = FirebaseDatabase.getInstance().getReference("recipes")
        iFirebaseLoadDone= this

        //Make sure live data is is being fetch from the database
        reciperRef.addValueEventListener(object: ValueEventListener{
            var recipeList_:MutableList<recipeDatabase> = ArrayList<recipeDatabase>()
            override fun onCancelled(p0: DatabaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailure(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (reciperSnapShot in p0.children) {
                    recipeList_.add(reciperSnapShot.getValue<recipeDatabase>(recipeDatabase::class.java!!)!!)
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(recipeList_)
            }

        })


        return inflater.inflate(R.layout.fragment_view_all_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recipesAdapter.listener = this
        recycler_view_recipes.adapter = recipesAdapter
        viewModel.fetchRecipe()
        viewModel.recipe.observe(viewLifecycleOwner, Observer { recipesAdapter.setRecipes(it) })

    }

    override fun onFirebaseLoadSuccess(recipeList: List<recipeDatabase>) {
        val reciper_types_= getRecipeTypes(recipeList)
        var list = ArrayList<String>()
        val aa = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, reciper_types_)
        spinner.adapter = aa

    }

    private fun getRecipeTypes(recipeList: List<recipeDatabase>): List<String> {
        //populating the spinner list
        val result = ArrayList<String>()
        for (recipe in recipeList)
            result.add(recipe.foodType!!)
        return result

    }

    override fun onFirebaseLoadFailure(message: String) {

    }

    override fun onRecyclerViewItemClicked(view: View, recipe: recipeDatabase) {
        //to open up the dialog fragment based on what is clicked on the recycler view
        recipes_items.setOnClickListener {
            ChoosenRecipe(recipe)
                .show(childFragmentManager, "")
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return view
    }


    
}
