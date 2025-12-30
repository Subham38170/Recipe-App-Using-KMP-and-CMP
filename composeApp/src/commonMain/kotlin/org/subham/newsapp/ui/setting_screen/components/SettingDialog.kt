package org.subham.newsapp.ui.setting_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.apply
import newsapp.composeapp.generated.resources.cancel
import newsapp.composeapp.generated.resources.choose_a_theme
import newsapp.composeapp.generated.resources.delete
import newsapp.composeapp.generated.resources.delete_bookmark

import org.jetbrains.compose.resources.stringResource
import org.subham.newsapp.theme.mediumPadding
import org.subham.newsapp.theme.xLargePadding
import org.subham.newsapp.theme.xSmallPadding
import org.subham.newsapp.utils.Theme

@Composable
fun DeleteBookmarkDialog(
    onDeleteBookmark: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(Res.string.delete_bookmark)
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.delete_bookmark)
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDeleteBookmark
            ) {
                Text(
                    text = stringResource(Res.string.delete)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(Res.string.cancel)
                )
            }
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    currentTheme: String,
    onThemeChange: (Theme) -> Unit,
    onDismissRequest: () -> Unit
) {
    var currentSelectedTheme by remember { mutableStateOf(Theme.valueOf(currentTheme)) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(
                modifier = Modifier
                    .wrapContentSize(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier
                        .padding(mediumPadding)
                ) {
                    Text(
                        text = stringResource(Res.string.choose_a_theme),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(xSmallPadding)
                    )
                    Theme.entries.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = { currentSelectedTheme = it }
                                ),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentSelectedTheme == it,
                                onClick = {
                                    currentSelectedTheme = it
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = stringResource(it.title)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(xLargePadding))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = onDismissRequest
                        ) {
                            Text(
                                text = stringResource(Res.string.cancel)
                            )
                        }
                        Spacer(modifier = Modifier.width(mediumPadding))
                        TextButton(
                            onClick = {
                                onThemeChange(currentSelectedTheme)
                            }
                        ) {
                            Text(
                                text = stringResource(Res.string.apply)
                            )
                        }
                    }
                }

            }
        }
    )
}
