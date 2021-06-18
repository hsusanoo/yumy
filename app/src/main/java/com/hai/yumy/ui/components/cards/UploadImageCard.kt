package com.hai.yumy.ui.components.cards

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter
import com.hai.yumy.R
import com.hai.yumy.ui.theme.gray200
import com.hai.yumy.ui.theme.gray600

@Composable
fun UploadImageCard(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    onUpload: () -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .size(height = 250.dp, width = 160.dp)
    ) {
        if (imageUri == null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(gray200)
                    .clickable {
                        onUpload()
                    }) {
                Column {

                    Icon(
                        imageVector = Icons.Outlined.PhotoCamera,
                        contentDescription = "Image icon",
                        tint = gray600,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Tap to upload an image",
                        style = MaterialTheme.typography.body2,
                        color = gray600,
                        fontSize = 12.sp
                    )
                }
            }
        } else {

            Image(
                painter = rememberGlidePainter(
                    imageUri,
//                    fadeIn = true,
                ),
                contentDescription = "Dish Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(height = 250.dp, width = 160.dp)
                    .clickable {
                        onUpload()
                    }
            )
        }
    }

}

@Preview
@Composable
fun UploadImagePreview() {
    UploadImageCard(imageUri = Uri.parse(""), onUpload = {})
}