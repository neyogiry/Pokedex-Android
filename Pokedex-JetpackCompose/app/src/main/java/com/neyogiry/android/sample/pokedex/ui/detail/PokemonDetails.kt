package com.neyogiry.android.sample.pokedex.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.neyogiry.android.sample.pokedex.R
import com.neyogiry.android.sample.pokedex.domain.PokemonDetail
import com.neyogiry.android.sample.pokedex.util.Image
import com.neyogiry.android.sample.pokedex.util.ImageHelper

@Composable
fun PokemonDetails(
    navController: NavController,
    url: String,
    viewModel: PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.provideFactory(url))
) {
    val viewState by viewModel.state.collectAsState()
    viewState.pokemon?.let { pokemon ->
        PokemonDetailContent(navController = navController, pokemon = pokemon, url = url)
    }
}

@Composable
private fun PokemonDetailContent(
    navController: NavController,
    pokemon: PokemonDetail,
    url: String,
) {
    Scaffold {
        val headerLayoutId = "header"
        val imageLayoutId = "image"
        val detailsLayoutId = "details"
        val constraints = constraints(headerLayoutId, imageLayoutId, detailsLayoutId)

        var headerBackgroundColor by remember { mutableStateOf(Color.White) }
        ConstraintLayout(constraintSet = constraints, modifier = Modifier.fillMaxSize()) {
            Header(navController = navController, layoutId = headerLayoutId, backgroundColor = headerBackgroundColor)
            Details(pokemon = pokemon, layoutId = detailsLayoutId)
            PokemonImage(imageUrl = ImageHelper.pokemonImageUrl(url), layoutId = imageLayoutId, headerBackgroundColor = { headerBackgroundColor = it })
        }
    }
}

@Composable
fun Header(
    navController: NavController,
    backgroundColor: Color,
    layoutId: String
) {
    Box(
        modifier = Modifier
            .height(270.dp)
            .fillMaxWidth()
            .background(color = backgroundColor)
            .layoutId(layoutId)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_expand_more),
            contentDescription = null,
            modifier = Modifier.clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { navController.popBackStack() },
            tint = Color.White
        )

    }
}

@Composable
fun PokemonImage(
    imageUrl: String,
    layoutId: String,
    headerBackgroundColor: (Color) -> Unit,
) {
    Image(
        url = imageUrl,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .layoutId(layoutId),
        averageColor = { headerBackgroundColor(it) }
    )
}

@Composable
fun Details(
    pokemon: PokemonDetail,
    layoutId: String,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            color = Color.White,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        )
        .layoutId(layoutId),
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 60.dp, start = 24.dp, end = 14.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = pokemon.name,
                fontSize = 24.sp,
                modifier = Modifier
                    .wrapContentSize()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                //Weight
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format("%.1f Kg", pokemon.weight/10f),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                    Text(
                        text = "Weight",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                }

                //Height
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format("%.1f m", pokemon.height/10f),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                    Text(
                        text = "Height",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .wrapContentHeight()
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

        constrain(details) {
            top.linkTo(header.bottom, margin = -40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(image) {
            linkTo(start = parent.start, top = parent.top, end = parent.end, bottom = details.top, topMargin = 12.dp, bottomMargin = (-100).dp, verticalBias = 0f)
            width = Dimension.percent(.6f)
            height = Dimension.fillToConstraints

            centerHorizontallyTo(details)
        }

    }
}