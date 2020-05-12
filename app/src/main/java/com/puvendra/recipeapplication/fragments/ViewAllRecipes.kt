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
import com.puvendra.recipeapplication.ChoosenRecipe
import com.puvendra.recipeapplication.EditerChosenRecipes
import com.puvendra.recipeapplication.Interface.IFirebaseLoadDone
import com.puvendra.recipeapplication.Interface.RecyclerViewClickListener
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
    private val adapter = RecipesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(recipeViewModel::class.java)

        reciperRef = FirebaseDatabase.getInstance().getReference("recipes")
        iFirebaseLoadDone= this

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
        recycler_view_recipes.adapter = adapter
        viewModel.fetchRecipe()
        viewModel.recipe.observe(viewLifecycleOwner, Observer { adapter.setRecipes(it) })
    }

    override fun onFirebaseLoadSuccess(recipeList: List<recipeDatabase>) {
        val reciper_types_= getRecipeTypes(recipeList)
        var list = ArrayList<String>()
        val aa = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, reciper_types_)
        spinner.adapter = aa

    }

    private fun getRecipeTypes(recipeList: List<recipeDatabase>): List<String> {
        val result = ArrayList<String>()
        for (recipe in recipeList)
            result.add(recipe.foodType!!)
        return result

    }

    override fun onFirebaseLoadFailure(message: String) {

    }

    override fun onRecyclerViewItemClicked(view: View, recipe: recipeDatabase) {

        recipes_items.setOnClickListener {
            ChoosenRecipe(recipe).show(childFragmentManager, "")
        }
    }

}
