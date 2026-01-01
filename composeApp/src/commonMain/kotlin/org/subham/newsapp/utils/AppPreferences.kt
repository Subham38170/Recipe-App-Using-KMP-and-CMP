package org.subham.newsapp.utils

import com.russhwolf.settings.Settings

class AppPreferences(
    private val settings: Settings
) {
    companion object {
        const val THEME_KEY = "Theme"
    }

    fun changeTheme(theme: String) = settings.putString(THEME_KEY, theme)


    fun getTheme(): String {
        val savedTheme = settings.getString(THEME_KEY, Theme.SYSTEM_DEFAULT.name)
        return savedTheme
    }
}