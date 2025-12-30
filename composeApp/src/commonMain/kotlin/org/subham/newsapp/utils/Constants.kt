package org.subham.newsapp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.dark_mode
import newsapp.composeapp.generated.resources.light_mode
import newsapp.composeapp.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource
import org.subham.newsapp.BuildKonfig

val news_api_key = BuildKonfig.NEWS_API_KEY
enum class Type {
    MOBILE,
    DESKTOP,
    WEB
}

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )
val FadeOut = fadeOut(animationSpec = tween(90))