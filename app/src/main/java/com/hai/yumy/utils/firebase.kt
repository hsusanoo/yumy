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

/**
 * Add a recipe to database
 */
fun uploadRecipeToFirebase(recipe: Recipe) {
    Log.d("Firebase", "Attempting to save data in firebase")

    val recipesRef = FirebaseDatabase.getInstance().getReference("/recipes")

    val filename = UUID.randomUUID().toString()

    val imgRef = FirebaseStorage.getInstance().getReference("/images/$filename")

    imgRef.putFile(Uri.parse(recipe.image)).addOnSuccessListener {
        Log.d("Firebase", "Successfully uploaded the image: ${it.metadata?.path}")

        imgRef.downloadUrl.addOnSuccessListener {

            recipe.image = it.toString()

            recipesRef.push().setValue(recipe).addOnSuccessListener {
                Log.d("Firebase", "Recipe saved in firebase!")
            }.addOnFailureListener {
                Log.d("Firebase", "Failed to save recipe in firebase!")
            }

        }
    }
}

/**
 * Get a list of all recipes in the database
 */
fun getRecipesFromFirebase(): List<Recipe> {
    Log.d("Firebase", "Attempting to get recipes from Firebase")

    val recipes = arrayListOf<Recipe>()

    val recipesRef = FirebaseDatabase.getInstance().getReference("/recipes")

    // FIXME: recipes array is empty at the end, might be because it's async or smthg
    recipesRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (!snapshot.exists()) {
                Log.wtf("Firebase", "No recipes snapshot found")
                return
            }

            for (recipeSnapshot in snapshot.children) {
                val recipe = recipeSnapshot.getValue(Recipe::class.java)
//                Log.d("Firebase", "Recipe found: $recipe")
                recipes.add(recipe!!)
            }
            Log.d("Firebase", "Recipes found: ${recipes.joinToString(", ")}")
        }

        override fun onCancelled(error: DatabaseError) {
            Log.wtf("Firebase", "Failed to get recipes from Firebase, error: $error")

        }

    })
    Log.d("Firebase", "Final recipes found: ${recipes.joinToString(", ")}")
    return recipes

}