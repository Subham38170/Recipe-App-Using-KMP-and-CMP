package org.subham.newsapp.utils

import com.russhwolf.settings.Settings


expect fun getType(): Type


expect fun getRandomId(): String


expect fun sharedLink(url: String)


expect fun createSettings(): Settings