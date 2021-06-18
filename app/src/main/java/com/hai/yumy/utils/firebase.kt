package com.hai.yumy.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.hai.yumy.models.Recipe
import java.util.*
import kotlin.collections.ArrayList

/**
 * Add a recipe to database
 */
fun uploadRecipeToFirebase(recipe: Recipe) {


    Log.d("Firebase", "Attempting to save data in firebase")

    val recipesRef = FirebaseDatabase.getInstance().getReference("/recipes")

    // Storing image
    val filename = UUID.randomUUID().toString()

    val imgRef = FirebaseStorage.getInstance().getReference("/images/$filename")



    imgRef.putFile(Uri.parse(recipe.image)).addOnSuccessListener { snapshot ->
        Log.d("Firebase", "Successfully uploaded the image: ${snapshot.metadata?.path}")

        imgRef.downloadUrl.addOnSuccessListener {

            recipe.image = it.toString()

            if (recipe.id != null) {
                // Updating
                Log.d("Firebase", "Attempting to UPDATE Recipe with id=${recipe.id}")
                recipesRef.child(recipe.id!!).setValue(recipe).addOnSuccessListener {
                    Log.d("Firebase", "Recipe saved in firebase!")
                }.addOnFailureListener {
                    Log.wtf("Firebase", "Failed to save recipe in firebase!")
                }

            } else {
                // Inserting
                recipesRef.push().setValue(recipe).addOnSuccessListener {
                    Log.d("Firebase", "Recipe saved in firebase!")
                }.addOnFailureListener {
                    Log.wtf("Firebase", "Failed to save recipe in firebase!")
                }

            }

        }
    }
}

/**
 * Get a list of all recipes in the database
 */
fun getRecipesFromFirebase(callback: (ArrayList<Recipe>) -> Unit) {
    Log.d("Firebase", "Attempting to get recipes from Firebase")

    val recipes = ArrayList<Recipe>()
    val recipesRef = FirebaseDatabase.getInstance().getReference("/recipes")

    recipesRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (!snapshot.exists()) {
                Log.wtf("Firebase", "No recipes snapshot found")
                return
            }

            for (recipeSnapshot in snapshot.children) {
                val recipe = recipeSnapshot.getValue(Recipe::class.java)
                recipe?.id = recipeSnapshot.key
//                Log.d("Firebase", "Recipe found: $recipe")
                recipes.add(recipe!!)
            }
            Log.d("Firebase", "Recipes found: ${recipes.joinToString(", ")}")
            callback(recipes)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.wtf("Firebase", "Failed to get recipes from Firebase, error: $error")

        }

    })
}

/**
 * Get recipe by id from database
 */
fun getRecipe(recipeId: String, callback: (Recipe?) -> Unit) {
    Log.d("Firebase", "Attempting to get one recipe from Firebase")

    val recipeRef = FirebaseDatabase.getInstance().getReference("/recipes").child(recipeId)

    recipeRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (!snapshot.exists()) {
                Log.wtf("Firebase", "No recipe found")
//                callback(null)
                return
            }

            val recipe = snapshot.getValue(Recipe::class.java)
            Log.d("Firebase", "One recipe found: $recipe")
            callback(recipe)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
}

fun deleteRecipe(recipeId: String) {
    Log.d("Firebase", "Attempting to DELETE one recipe from Firebase")
    FirebaseDatabase.getInstance().getReference("/recipes").child(recipeId).removeValue()
}