package com.example.procatfirst.ui.item

import com.example.procatfirst.data.Tool

data class ToolUiState(
    val addedToCart: Boolean = false,
    val amount: Int = 0,
    val tool: Tool = Tool(0, "Название", 0, "Описание", "Характеристики", 0)
)
