package org.subham.newsapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import java.util.UUID

actual fun getType(): Type {
    return Type.MOBILE
}

actual fun getRandomId(): String {
    return UUID.randomUUID().toString()
}

actual fun sharedLink(url: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"

        putExtra(Intent.EXTRA_TEXT, url)
    }
    val intentChooser = Intent.createChooser(intent, "Share Link")
    activityProvider.invoke().startActivity(intentChooser)

}

private var activityProvider: () -> Activity = {
    throw IllegalArgumentException("Error")
}

fun setActivityProvider(provider: () -> Activity) {
    activityProvider = provider
}

actual fun createSettings(): Settings {
    val prefs =
        activityProvider.invoke().getSharedPreferences(SharedPrefsFileName, Context.MODE_PRIVATE)
    return SharedPreferencesSettings(prefs)
}