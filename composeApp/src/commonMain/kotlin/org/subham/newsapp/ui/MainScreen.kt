package org.subham.newsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.setting
import newsapp.composeapp.generated.resources.settings_24
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.subham.newsapp.ui.navigation.NavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Headline",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.settings_24),
                            contentDescription = stringResource(Res.string.setting)
                        )
                    }
                }
            )
        }
    ) {
        NavGraph(
            modifier = Modifier
                .padding(it)
        )
    }
}