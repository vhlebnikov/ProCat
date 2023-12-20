package com.example.procatfirst.ui.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.procatfirst.ui.auth.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToolsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init{
        open()
    }

    var userInputSearch by mutableStateOf("")
        private set

    fun updateInputSearch(enteredWord: String){
        userInputSearch = enteredWord
    }

    fun search() {

    }

    private fun open() {
        _uiState.value = SearchUiState()
    }

}