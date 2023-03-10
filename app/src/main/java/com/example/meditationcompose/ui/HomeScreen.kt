package com.example.meditationcompose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationcompose.R
import com.example.meditationcompose.ui.theme.*
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column {
            Greeting("Sandesh")
            ChipSection(listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeaturesSection(
                listOf(
                    Feature(
                        "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ), Feature(
                        "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ), Feature(
                        "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ), Feature(
                        "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }
        BottomMenu(
            itemsBottom = listOf(
                ItemBottom("Home", R.drawable.ic_home),
                ItemBottom("Meditate", R.drawable.ic_bubble),
                ItemBottom("Sleep", R.drawable.ic_moon),
                ItemBottom("Music", R.drawable.ic_music),
                ItemBottom("Profile", R.drawable.ic_profile),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Greeting(name: String = "Sandesh") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good morning, $name",
                style = Typography.h2,
            )
            Text(
                text = "We wish you have a good day!",
                style = Typography.body1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(chips: List<String>) {

    var selectedChipState by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size) {
            Box(modifier = Modifier
                .padding(15.dp, 15.dp, 0.dp, 15.dp)
                .clickable {
                    selectedChipState = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(if (selectedChipState == it) ButtonBlue else DarkerButtonBlue)
                .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation() {

    Row(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightRed)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Daily Thought", style = Typography.h1)
            Text(text = "Meditation • 3-10 min", style = Typography.body1, color = TextWhite)
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_play),
                contentDescription = "Current Meditation Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeaturesSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Features", style = Typography.h1, modifier = Modifier.padding(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(features[it])
            }
        }

    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.colorDark)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.colorMedium
            )
            drawPath(
                path = lightColoredPath,
                color = feature.colorLight
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = Typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Icon(
                painter = painterResource(feature.icon),
                contentDescription = "",
                tint = TextWhite,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}


@Composable
fun BottomMenu(itemsBottom: List<ItemBottom>, modifier: Modifier = Modifier) {
    var selectedItemIndex by remember {
        mutableStateOf(0)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(top = 10.dp, bottom = 5.dp)
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsBottom.forEachIndexed { index, itemBottom ->

            BottomMenuItem(
                itemBottom = itemBottom,
                isSelected = index == selectedItemIndex
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    itemBottom: ItemBottom,
    isSelected: Boolean,
    onItemClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onItemClick() }
    ) {
        Icon(
            painter = painterResource(itemBottom.icon),
            contentDescription = itemBottom.title,
            tint = if (isSelected) TextWhite else AquaBlue,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) ButtonBlue else Color.Transparent)
                .padding(10.dp)
                .size(20.dp)
        )
        Text(
            text = itemBottom.title,
            color = if (isSelected) TextWhite else AquaBlue
        )
    }
}

//@Preview
@Composable
fun Test() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            BottomMenu(
                itemsBottom = listOf(
                    ItemBottom("Home", R.drawable.ic_home),
                    ItemBottom("Meditate", R.drawable.ic_bubble),
                    ItemBottom("Sleep", R.drawable.ic_moon),
                    ItemBottom("Music", R.drawable.ic_music),
                    ItemBottom("Profile", R.drawable.ic_profile),
                )
            )
        }
    }
}

data class Feature(
    val title: String,
    @DrawableRes val icon: Int,
    val colorLight: Color,
    val colorMedium: Color,
    val colorDark: Color,
)

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}

data class ItemBottom(
    val title: String,
    @DrawableRes val icon: Int,
)

