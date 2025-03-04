package com.example.pagingsample.ui.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimationCardListItem(
    headlineContent :  @Composable () -> Unit,
    overlineContent  : @Composable() (() -> Unit)? = null,
    supportingContent : @Composable() (() -> Unit)? = null,
    trailingContent : @Composable() (() -> Unit)? = null,
    leadingContent : @Composable() (() -> Unit)? = null,
    color : Color? = null,
    index : Int,
    scale : Float = 0.8f,
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier
) {
    val animatedProgress = remember { Animatable(scale) }

    LaunchedEffect(index) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(ANIMATION_SPEED, easing = EaseInOutQuad)
        )
    }
    StyleCardListItem(
        headlineContent,
        overlineContent,
        supportingContent,
        trailingContent,
        leadingContent,
        color,
        modifier,
        cardModifier.graphicsLayer {
            scaleX = animatedProgress.value
            scaleY = animatedProgress.value
        }
    )
}

@Composable
fun StyleCardListItem(
    headlineContent :  @Composable () -> Unit,
    overlineContent  : @Composable() (() -> Unit)? = null,
    supportingContent : @Composable() (() -> Unit)? = null,
    trailingContent : @Composable() (() -> Unit)? = null,
    leadingContent : @Composable() (() -> Unit)? = null,

    color : Color? = null,
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier
) {
    CardListItem(
        headlineContent, overlineContent, supportingContent, trailingContent,leadingContent, modifier = modifier, cardModifier = cardModifier,
        hasElevation = false,
        containerColor = color ?: cardNormalColor()
    )
}

@Composable
private fun CardListItem(
    headlineContent :  @Composable () -> Unit,
    overlineContent  : @Composable() (() -> Unit)? = null,
    supportingContent : @Composable() (() -> Unit)? = null,
    trailingContent : @Composable() (() -> Unit)? = null,
    leadingContent : @Composable() (() -> Unit)? = null,
    hasElevation : Boolean = false,
    containerColor : Color? = null,
    modifier: Modifier = Modifier,
    cardModifier : Modifier = Modifier
) {
    MyCustomCard(hasElevation = hasElevation, containerColor = containerColor, modifier = cardModifier) {
        TransplantListItem(
            headlineContent = headlineContent,
            overlineContent = overlineContent,
            supportingContent = supportingContent,
            trailingContent = trailingContent,
            leadingContent = leadingContent,
            modifier = modifier
        )
    }
}

@Composable
fun MyCustomCard(
    modifier: Modifier = Modifier,
    containerColor : Color? = null,
    hasElevation : Boolean = false,
    content: @Composable () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = if(hasElevation) 1.75.dp else 0.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = appHorizontalDp(), vertical = cardNormalDp()),
        shape = MaterialTheme.shapes.medium,
        colors = if(containerColor == null) CardDefaults.cardColors() else CardDefaults.cardColors(containerColor = containerColor)
    ) {
        content()
    }
}


@Composable
fun TransplantListItem(
    headlineContent :  @Composable () -> Unit,
    overlineContent  : @Composable() (() -> Unit)? = null,
    supportingContent : @Composable() (() -> Unit)? = null,
    trailingContent : @Composable() (() -> Unit)? = null,
    leadingContent : @Composable() (() -> Unit)? = null,
    colors : Color? = null,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = headlineContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        colors = ListItemDefaults.colors(containerColor = colors ?: Color.Transparent) ,
        trailingContent = trailingContent,
        leadingContent = leadingContent,
        modifier = modifier
    )
}

@Composable
fun cardNormalColor() : Color = largeCardColor().copy(alpha = .6f)
@Composable
fun largeCardColor() : Color = MaterialTheme.colorScheme.surfaceVariant
//@Composable
fun cardNormalDp() : Dp = 2.5.dp

//@Composable
fun appHorizontalDp() : Dp = 15.dp

const val ANIMATION_SPEED = 400
