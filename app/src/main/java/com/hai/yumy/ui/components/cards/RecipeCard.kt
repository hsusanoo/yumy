package com.hai.yumy.ui.components.cards


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter
import com.hai.yumy.models.Recipe
import com.hai.yumy.ui.components.Tag
import com.hai.yumy.ui.theme.gray600
import com.hai.yumy.ui.theme.green300
import com.hai.yumy.ui.theme.yellow500

@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {

    val (id, image, name, _, tags, _, preptimeH, preptimeM, servings) = recipe

    var prepString = ""
    if (preptimeH != 0) prepString = "${preptimeH}H"
    if (preptimeM != 0) prepString += "${preptimeM}min"

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {  }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(150.dp)
        ) {
            Image(
                painter = rememberGlidePainter(image),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                alignment = BiasAlignment(0f, -0f/300),
                modifier = Modifier
                    .padding(10.dp)
                    .size(130.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(.8f)
                    .clip(RoundedCornerShape(5))
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            ) {
                Column(Modifier.weight(1f)) {
                    Text(text = name!!, fontSize = 15.sp)
                    LazyRow {
                        items(tags) {
                            Tag(text = it)
                        }
                    }
                }

                Row {

                    Row(modifier = Modifier.weight(1f)) {
                        if (prepString.isNotEmpty()) {
                            Icon(
                                Icons.Outlined.Timer,
                                contentDescription = "Check icon",
                                tint = green300,
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = prepString,
                                color = gray600,
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }

                    Row {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "Person icon",
                            tint = yellow500,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "× $servings",
                            color = gray600,
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                }

            }

        }
    }

}

@Preview
@Composable
fun RecipeCardPreview() {
    Column(modifier = Modifier.padding(20.dp)) {

        val recipe1 = Recipe(
            image = "https://www.deelux.co.uk/wp-content/uploads/2018/09/IMG_2658.jpg",
            name = "Hearty Minestrone Soup Recipe",
            tags = listOf("Soup", "Healthy", "Pasta"),
            preptimeH = 1,
            preptimeM = 45,
            servings = 4
        )

        RecipeCard(recipe1)
        RecipeCard(recipe1)
        RecipeCard(recipe1)
        RecipeCard(recipe1)
        RecipeCard(recipe1)
        RecipeCard(recipe1)
        RecipeCard(recipe1)
    }
}





