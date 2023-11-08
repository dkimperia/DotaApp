package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.entity.Comment
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                DotaScreen()
            }
        }
    }
}

@Composable
fun DotaScreen() {
    val comments = listOf(
        Comment(),
        Comment(),
        Comment(),
        Comment(),
        Comment(),
        Comment(),
        Comment(),
        Comment(),
        Comment(),
        Comment()
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.d_surface)
    ) {
        LazyColumn {
            item { Header() }
            item { Box(Modifier.padding(20.dp, 20.dp)) { ProductInfo() } }
            item { Box(Modifier.padding(20.dp, 20.dp)) { RatingInfo() } }
            items(comments) {comment -> Box(Modifier.padding(20.dp, 20.dp)) { CommentCard(comment) }}
            item { Box(Modifier.padding(20.dp, 20.dp)) { InstallButton() } }
        }
    }
}


@Composable
fun Header() {
    val overlayOffset = 100f

    ConstraintLayout {
        val (headerImg, headerLogo, title, stats) = createRefs()
        Image(
            painter = painterResource(R.drawable.header_sqr_img),
            contentDescription = "header image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(headerImg) {}
        )

        Surface(
            modifier = Modifier
                .size(120.dp, 120.dp - 40.dp)
                .padding(20.dp, 0.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(
                    2.dp,
                    color = colorResource(R.color.d_logo_border),
                    RoundedCornerShape(15.dp)
                )
                .constrainAs(headerLogo) {
                    top.linkTo(headerImg.bottom, margin = (-20).dp)
                },
            color = colorResource(R.color.black),
        ) {
            Image(
                painter = painterResource(R.drawable.header_logo),
                contentDescription = "logo image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(15.dp, 15.dp)
            )
        }

        Column (
            Modifier
                .constrainAs(title) {
                    bottom.linkTo(headerLogo.bottom, margin = (5).dp)
                    start.linkTo(headerLogo.end)
                }
        ){
            Text(
                text = "DoTA 2",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            HeaderStats()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x050b18)
@Composable
fun HeaderPreview() {
    Header()
}
@Composable
fun HeaderStats() {
    Row(modifier = Modifier.padding(0.dp)) {
        Image(
            painter = painterResource(R.drawable.star_5),
            contentDescription = "logo image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(2.dp, 6.dp).size(15.dp,15.dp)
        )
        Image(
            painter = painterResource(R.drawable.star_5),
            contentDescription = "logo image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(2.dp, 6.dp).size(15.dp,16.dp)
        )
        Image(
            painter = painterResource(R.drawable.star_5),
            contentDescription = "logo image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(2.dp, 6.dp).size(15.dp,16.dp)
        )
        Image(
            painter = painterResource(R.drawable.star_5),
            contentDescription = "logo image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(2.dp, 6.dp).size(15.dp,16.dp)
        )
        Image(
            painter = painterResource(R.drawable.star_5),
            contentDescription = "logo image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(2.dp, 6.dp).size(15.dp,16.dp)
        )
        Text(
            text = "70M",
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.padding(8.dp, 4.dp)
        )


    }
}



@Composable
fun ProductInfo() {
    Column {
        ProductTags()
        Spacer(0.dp, 15.dp)
        ProductDescription()
        Spacer(0.dp, 15.dp)
        VideoList()
    }
}

@Composable
fun ProductTags(
    tags : List<String> = listOf(
        "MMO",
        "STRATEGY",
        "MULTIPLAYER",
        "LIFEKILLER",
        "OMG",
        "WTF"
    )
) {

    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(tags) {tag ->
            Surface(
                color = colorResource(R.color.d_tag_background).copy(alpha = 0.24f),
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
            ){
                Text(
                    modifier = Modifier.padding(15.dp, 6.dp),
                    text = tag,
                    color = colorResource(R.color.d_tag_text_color)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x050b18)
@Composable
fun ProductTagPreview() {
    ProductTags()
}

@Composable
fun VideoList(
    videos : List<Int> = listOf(
        R.drawable.video_preview_2,
        R.drawable.header_img,
        R.drawable.video_preview_1
    )
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        items(videos) { videoPreviewID ->
            Image(
                painter = painterResource(id = videoPreviewID),
                contentDescription = "preview $videoPreviewID",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(220.dp, 120.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
        }
    }
}

@Preview(showBackground = true,  backgroundColor = 0x050b18)
@Composable
fun VideoListPreview() {
    VideoList()
}

@Composable
fun ProductDescription(text : String = "Dota 2 is a multiplayer online battle arena (MOBA) game which has two teams of five players compete to collectively destroy a large structure defended by the opposing team known as the \"Ancient\", whilst defending their own.") {
    Text(
        text = text,
        color = colorResource(R.color.white).copy(alpha = 0.6f)
    )
}


@Preview(showBackground = true,  backgroundColor = 0x050b18)
@Composable
fun ProductDescriptionPreview() {
    ProductDescription()
}


@Composable
fun RatingInfo() {
    Text("Rating info")
}

@Composable
fun CommentCard(comment : Comment = Comment()) {
    Column {
        Row {
            Image(
                painter = painterResource(R.drawable.header_img),
                contentDescription = "header image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
            )
            Spacer(10.dp, 0.dp)
            Column {
                Text(
                    text = comment.authorName,
                    color = Color.White
                )
                Text(
                    text = comment.date.toString(),
                    color = Color.Gray.copy(alpha = 0.8f)
                )
            }
        }

        Text(
            text = comment.text,
            color = Color.White.copy(alpha = 0.6f)
        )
    }
}


@Preview(showBackground = true,  backgroundColor = 0x050b18)
@Composable
fun CommentCardPreview() {
    CommentCard()
}

@Composable
fun InstallButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.d_button))
    ) {
        Text(
            text = "Install",
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true,  backgroundColor = 0x050b18)
@Composable
fun InstallButtonPreview() {
    InstallButton()
}

@Composable
fun Spacer(
    horizontalPadding : Dp = 10.dp,
    verticalPadding : Dp = 10.dp
) {
    Box(Modifier.padding(horizontalPadding, verticalPadding))
}
