package org.subham.newsapp.ui.setting_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.subham.newsapp.data.repository.LocalRepository
import org.subham.newsapp.utils.AppPreferences

class SettingViewModel(
    private val localNewsRepository: LocalRepository,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _currentTheme = MutableStateFlow<String?>(null)
    val currentTheme = _currentTheme.asStateFlow()

    init {
        getCurrentTheme()
    }

    fun deleteAllBookMarkedArticles(){
        viewModelScope.launch(Dispatchers.IO){
            localNewsRepository.deleteAllarticles()
        }
    }
    fun changeTheme(value: String) = appPreferences.changeTheme(value).also {
        getCurrentTheme()
    }


    private fun getCurrentTheme() {
        _currentTheme.update {
            appPreferences.getTheme()
        }
    }
}