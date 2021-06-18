package com.hai.yumy.ui.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hai.yumy.models.Recipe
import com.hai.yumy.ui.components.cards.UploadImageCard
import com.hai.yumy.ui.components.formsections.IngredientsSection
import com.hai.yumy.ui.components.formsections.TagsSection
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.ui.theme.gray50
import com.hai.yumy.ui.theme.gray600
import com.hai.yumy.utils.getRecipe
import com.hai.yumy.utils.uploadRecipeToFirebase
import com.hai.yumy.viewmodels.RecipeVM

@Composable
fun NewRecipeView(
    recipeVM: RecipeVM,
    navController: NavHostController?,
    modifier: Modifier = Modifier,
    recipeId: String? = null
) {

    fun navigateToHome() {
        navController?.navigate("home")
    }

    if (recipeId != null) {
        getRecipe(recipeId = recipeId) {
            recipeVM.copy(it)
        }
    }

    val submit = {
        val _recipe = Recipe(
            recipeId,
            recipeVM.image.value!!.toString(),
            recipeVM.name.value,
            recipeVM.description.value,
            recipeVM.tags,
            recipeVM.ingredients,
            recipeVM.preptimeH.value.toInt(),
            recipeVM.preptimeM.value.toInt(),
            recipeVM.servings.value.toInt(),
        )
        uploadRecipeToFirebase(_recipe)
        recipeVM.clear()
        navigateToHome()
    }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri ->
        recipeVM.image.value = uri
    }

    Column(modifier) {
        Column(Modifier.weight(1f)) {

            //  Back arrow
            Row(Modifier.padding(16.dp)) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Arrow",
                    Modifier
                        .size(30.dp)
                        .clickable {
                            navigateToHome()
                        }
                )
            }

            // Scrollable form
            Column(Modifier.verticalScroll(rememberScrollState())) {

                //  Image
                UploadImageCard(
                    imageUri = recipeVM.image.value,
                    onUpload = { imageLauncher.launch("image/*") })

                Column(Modifier.padding(vertical = 10.dp, horizontal = 12.dp)) {
                    //  Name
                    TextField(
                        value = recipeVM.name.value,
                        onValueChange = { recipeVM.name.value = it },
                        placeholder = {
                            Text(
                                text = "Recipe name...",
                                style = MaterialTheme.typography.h5
                            )
                        },
                        textStyle = MaterialTheme.typography.h5,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )

                    SectionGap()

                    SectionTitle(title = "Tags")
                    TagsSection(
                        tags = recipeVM.tags,
                        onAdd = { recipeVM.addTag(it) },
                        onRemove = {
                            recipeVM.removeTag(it)
                        })

                    SectionGap()

                    //  Description
                    SectionTitle(title = "Description")
                    TextField(
                        value = recipeVM.description.value,
                        onValueChange = { recipeVM.description.value = it },
                        maxLines = 4,
                        textStyle = MaterialTheme.typography.body2,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = gray50)
                    )

                    SectionGap()

                    //  Ingredients
                    SectionTitle(title = "Ingredients")
                    IngredientsSection(
                        ingredientsList = recipeVM.ingredients,
                        onAdd = { recipeVM.addIngredient(it) },
                        onRemove = { recipeVM.removeIngredient(it) }
                    )

                    // Prep time
                    SectionGap()
                    SectionTitle(title = "Preparation time")
                    Row(
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        OutlinedTextField(
                            value = recipeVM.preptimeH.value,
                            onValueChange = { recipeVM.preptimeH.value = it },
                            label = { Text("HH") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(65.dp)
                        )

                        Text(
                            text = ":",
                            fontSize = 40.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                        OutlinedTextField(
                            value = recipeVM.preptimeM.value,
                            onValueChange = { recipeVM.preptimeM.value = it },
                            label = { Text(text = "MM") },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(65.dp)

                        )

                    }

                    // Servings
                    SectionGap()
                    SectionTitle(title = "Servings")
                    Row(Modifier.align(Alignment.CenterHorizontally)) {
                        OutlinedTextField(
                            value = recipeVM.servings.value,
                            onValueChange = { recipeVM.servings.value = it },
                            label = {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Person Icon"
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.width(65.dp)
                        )
                    }

                }
            }
        }

        SectionGap()
        SectionGap()

        //  Confirm Button
        Button(
            onClick = { submit() },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            enabled = recipeVM.isRequiredFieldsFilled()
        ) {
            Text(
                text = "Add Recipe",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
    }


}

@Composable
private fun SectionTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.body2,
        color = gray600,
        modifier = modifier.padding(top = 20.dp, bottom = 10.dp)
    )
}

@Composable
fun SectionGap() {
    Spacer(modifier = Modifier.height(15.dp))
}

@Preview
@Composable
private fun NewRecipePreview() {

    YumyTheme {
        Surface(color = Color.White) {
            NewRecipeView(recipeVM = RecipeVM(), null)
        }
    }
}