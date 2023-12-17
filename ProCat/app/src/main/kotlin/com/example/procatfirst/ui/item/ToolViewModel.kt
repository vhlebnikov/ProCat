package com.example.procatfirst.ui.item

import androidx.lifecycle.ViewModel
import com.example.procatfirst.data.ToolDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToolViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ToolUiState())
    val uiState: StateFlow<ToolUiState> = _uiState.asStateFlow()

    init{
        openFirstTool()
    }

    private fun openFirstTool() {
        val firstTool = ToolDataProvider.tools.firstOrNull()
        firstTool?.let {
            _uiState.value = ToolUiState(tool = it)
        }
    }

    fun addToCart() {
        val newAmount = _uiState.value.amount.plus(1)
        _uiState.update { currentState ->
            currentState.copy(
                addedToCart = true,
                amount = newAmount
            )
        }
    }

}