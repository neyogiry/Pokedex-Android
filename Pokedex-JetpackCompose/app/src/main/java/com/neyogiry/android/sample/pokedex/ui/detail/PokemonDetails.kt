package com.neyogiry.android.sample.pokedex.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.neyogiry.android.sample.pokedex.R

@Composable
fun PokemonDetails() {
    Column {
        val headerLayoutId = "header"
        val imageLayoutId = "image"
        val detailsLayoutId = "details"
        val constraints = constraints(headerLayoutId, imageLayoutId, detailsLayoutId)

        ConstraintLayout(constraintSet = constraints) {
            Header(headerLayoutId)
            Details(detailsLayoutId)
            PokemonImage(imageLayoutId)
        }
    }
}

@Composable
fun Header(layoutId: String = "") {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = Color.Cyan)
            .layoutId(layoutId)
    )
}

@Composable
fun PokemonImage(layoutId: String = "") {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "",
        modifier = Modifier
            .background(color = Color.Green)
            .layoutId(layoutId)
    )
}

@Composable
fun Details(layoutId: String = "") {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            )
            .padding(12.dp)
            .layoutId(layoutId)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 48.dp, start = 24.dp, end = 14.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Pokemon",
                fontSize = 24.sp,
                modifier = Modifier
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow {
                items(1) {
                    Text(
                        text = "Water",
                        modifier = Modifier
                            .wrapContentHeight()
                            .background(color = Color.Cyan)
                            .padding(4.dp)
                    )
                }
            }

        }

    }
}

private fun constraints(
    headerLayoutId: Any,
    imageLayoutId: Any,
    detailsLayoutId: Any,
) : ConstraintSet {
    return ConstraintSet {
        val header = createRefFor(headerLayoutId)
        val image = createRefFor(imageLayoutId)
        val details = createRefFor(detailsLayoutId)

        constrain(header) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(image) {
            bottom.linkTo(details.top, margin = (-30).dp)
            centerHorizontallyTo(details)
        }

        constrain(details) {
            top.linkTo(header.bottom, margin = -40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PokemonDetailsPreview() {
    PokemonDetails()
}