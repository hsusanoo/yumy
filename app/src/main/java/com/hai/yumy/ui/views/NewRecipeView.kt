package com.hai.yumy.ui.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hai.yumy.ui.components.IngredientsSection
import com.hai.yumy.ui.components.cards.UploadImageCard
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.ui.theme.gray50
import com.hai.yumy.ui.theme.gray600
import com.hai.yumy.viewmodels.DishVM

@Composable
fun NewRecipeView(dishViewModel: DishVM, modifier: Modifier = Modifier) {


    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri ->
        println("Uri inside image launcher: $uri")
        dishViewModel.image.value = uri
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
                            //  TODO: Go Back
                        }
                )
            }
            Column(Modifier.verticalScroll(rememberScrollState())) {

                //  Image
                UploadImageCard(
                    imageUri = dishViewModel.image.value,
                    onUpload = { imageLauncher.launch("image/*") })

                Column(Modifier.padding(vertical = 10.dp, horizontal = 12.dp)) {
                    //  Name
                    TextField(
                        value = dishViewModel.name.value,
                        onValueChange = { dishViewModel.name.value = it },
                        placeholder = {
                            Text(
                                text = "My Recipe...",
                                style = MaterialTheme.typography.h5
                            )
                        },
                        textStyle = MaterialTheme.typography.h5,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // TODO: Tags
                    LazyRow {

                    }

                    //  Description
                    SectionTitle(title = "Description")
                    TextField(
                        value = dishViewModel.description.value,
                        onValueChange = { dishViewModel.description.value = it },
                        maxLines = 4,
                        textStyle = MaterialTheme.typography.body2,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = gray50)
                    )
                    //  Ingredients
                    SectionTitle(title = "Ingredients")
                    IngredientsSection(
                        ingredientsList = dishViewModel.ingredients,
                        onAdd = { dishViewModel.addIngredient(it) },
                        onRemove = { dishViewModel.removeIngredient(it) }
                    )
                }
            }
        }

        //  Confirm Button
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
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

@Preview
@Composable
private fun NewRecipePreview() {

    YumyTheme {
        Surface(color = Color.White) {
            NewRecipeView(dishViewModel = DishVM())
        }
    }
}