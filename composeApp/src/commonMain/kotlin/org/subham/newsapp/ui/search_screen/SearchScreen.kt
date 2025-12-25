package org.subham.newsapp.ui.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.subham.newsapp.theme.mediumPadding
import org.subham.newsapp.ui.common.ArticleListScreen
import org.subham.newsapp.ui.common.SearchBar
import org.subham.newsapp.ui.home_screen.articlesList

@Composable
fun SearchScreen() {
    var text by rememberSaveable{
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ){
        SearchBar(
            text = text,
            onValueChange = {text = it},
            onSearch = { query ->
                if(query.trim().isNotEmpty()) println(query)
            }
        )
        ArticleListScreen(
            articles = articlesList,
            onClick = {}
        )
    }
}