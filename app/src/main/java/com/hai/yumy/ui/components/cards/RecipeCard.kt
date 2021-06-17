package com.hai.yumy.ui.components.cards


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

    val (_,image, name, _, tags, _, preptimeH, preptimeM, servings) = recipe

    var prepString = ""
    if (preptimeH != 0) prepString = "${preptimeH}H"
    if (preptimeM != 0) prepString += "${preptimeM}min"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
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
                modifier = Modifier
                    .padding(10.dp)
                    .size(130.dp)
                    .clip(RoundedCornerShape(5))
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            ) {
                Column(Modifier.weight(1f)) {
                    Text(text = name, fontSize = 15.sp)
                    Row {
                        for (tag: String in tags) {
                            Tag(text = tag)
                        }
                    }
                }

                Row {

                    Row(modifier = Modifier.weight(1f)) {
                        if (prepString.isNotEmpty()) {
                            Icon(
                                Icons.Outlined.CheckCircle,
                                contentDescription = "Check icon",
                                tint = green300,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(text = prepString, color = gray600)
                        }
                    }

                    Row {
                        Icon(
                            Icons.Outlined.Timer,
                            contentDescription = "Person icon",
                            tint = yellow500,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = "× $servings", color = gray600)
                    }

                }

            }

        }
    }

}

@Preview("Dish Cards")
@Composable
fun DefaultPreview() {
    Column(modifier = Modifier.padding(20.dp)) {

//        DishCard(
//            R.drawable.random_dish_2,
//            "Shrimp and cheese grits",
//            0,
//            45,
//            listOf("Sea Food", "Quick"),
//            2
//        )
//
//        DishCard(
//            image = R.drawable.randomdish,
//            name = "Garlic Herb Roasted Potatoes Carrots and Zucchini",
//            preptimeH = 1,
//            preptimeM = 15,
//            tags = listOf("Vegan", "Healthy"),
//            1
//        )

    }
}





