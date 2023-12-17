package com.example.procatfirst.data

import com.example.procatfirst.R

data class Tool(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val description: String,
    val specifications: String
)

object ToolDataProvider {
    val tools = listOf(
        Tool(
            id = 1,
            name = "Молоток",
            imageResId = R.drawable.hammer,
            description = "Очень качественный, громкий",
            specifications = "Материал: пластик"
        ),
    )
}