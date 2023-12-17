package com.example.procatfirst.ui.auth

data class AuthUiState(
    val phoneNumber: String = "89000000009",
    val password: String = "",
    val enteredPasswordWrong: Boolean = false,
    val enteredPhoneNumberWrong: Boolean = false
    )
