package com.hai.yumy.ui.components.cards


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun DishCard(
    image: Int,
    name: String,
    prepDurationH: Number,
    prepDurationM: Number,
    tags: List<String>,
    serving: Number
) {

    val yellow500 = Color(0xFFF59E0B)
    val green300 = Color(0xFF6EE7B7)
    val gray600 = Color(0xFF4B5563)

    var prepString: String = ""
    if (prepDurationH != null && prepDurationH != 0) prepString = "${prepDurationH}H"
    if (prepDurationM != null && prepDurationM != 0) prepString += "${prepDurationM}min"

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
                painter = painterResource(id = image),
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
                            Icons.Outlined.Person,
                            contentDescription = "Person icon",
                            tint = yellow500,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = "× $serving", color = gray600)
                    }

                }

            }

        }
    }

}

@Composable
fun Tag(text: String) {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(30))
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp,
            modifier = Modifier
                .padding(horizontal = 7.dp, vertical = 2.dp)
        )
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
//            prepDurationH = 1,
//            prepDurationM = 15,
//            tags = listOf("Vegan", "Healthy"),
//            1
//        )

    }
}





