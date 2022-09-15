package com.example.mycityapp.ui

import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState

    fun updateCurrentCategory(category: CategoryName) {
        _uiState.update {
            it.copy(
                currentTab = category
            )
        }
    }

    fun updateCurrentDetails(place: Place?) {
        _uiState.update {
            it.copy(
                currentDetails = place
            )
        }
    }
}