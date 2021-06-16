package com.hai.yumy.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.hai.yumy.models.Recipe
import java.util.*

fun uploadRecipeToFirebase(recipe: Recipe) {
    Log.d("Firebase", "Attempting to save data in firebase")

    val recipesRef = FirebaseDatabase.getInstance().getReference("/recipes")

    val filename = UUID.randomUUID().toString()

    val imgRef = FirebaseStorage.getInstance().getReference("/images/$filename")

    imgRef.putFile(Uri.parse(recipe.image))
        .addOnSuccessListener {
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