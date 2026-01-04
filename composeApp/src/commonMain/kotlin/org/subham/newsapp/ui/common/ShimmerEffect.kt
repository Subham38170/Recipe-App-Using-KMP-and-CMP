package org.subham.newsapp.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import org.subham.newsapp.theme.cardMinSize
import org.subham.newsapp.theme.imageSize
import org.subham.newsapp.theme.mediumPadding
import org.subham.newsapp.theme.shimmerColors
import org.subham.newsapp.theme.xLargePadding
import org.subham.newsapp.theme.xxSmallPadding
import org.subham.newsapp.theme.xxxLargePadding
import org.subham.newsapp.utils.Type
import org.subham.newsapp.utils.getType

@Composable
fun ShimmerEffect() {
    val isDesktop = remember { getType() in listOf(Type.DESKTOP, Type.WEB) }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(cardMinSize),
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding)
    ) {
        items(if (isDesktop) 12 else 5) {
            ArticleCardShimmerEffect()
        }

    }
}

@Composable
fun ArticleCardShimmerEffect() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .shimmerEffect()
        )

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(xxxLargePadding)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(xxxLargePadding)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(mediumPadding)
                    .shimmerEffect()
            )

        }
    }
}

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = LinearEasing),
            RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 100f, translateAnimation+100f),
        tileMode = TileMode.Mirror
    )
    background(brush, RoundedCornerShape(10))
}