package org.subham.newsapp.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.subham.newsapp.theme.imageSize
import org.subham.newsapp.theme.smallPadding

@Composable
fun EmptyContent(
    message: String,
    icon: DrawableResource,
    isOnRetryBtnVisible: Boolean = true,
    onRetryClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(imageSize),
            painter = painterResource(icon),
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            contentDescription = null
        )
        Text(
            text = message,
            modifier = Modifier
                .padding(smallPadding),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )

        if (isOnRetryBtnVisible) {
            Button(
                onClick = onRetryClick
            ) {
                Text(
                    text = stringResource(Res.string.retry),
                    modifier = Modifier
                        .padding(smallPadding),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }


    }
}