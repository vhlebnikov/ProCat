package com.example.procatfirst.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.procatfirst.ui.theme.ProCatFirstTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.procatfirst.R
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.procatfirst.ui.theme.md_theme_light_scrim
import com.example.procatfirst.ui.theme.md_theme_light_tertiary


@Composable
fun AuthScreen(
    onNextButtonClicked: () -> Unit,
    authViewModel: AuthViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val authUiState by authViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = typography.titleLarge,
            color = if (authUiState.enteredPasswordWrong) {
                md_theme_light_scrim
            } else {
                md_theme_light_tertiary
            }
        )

        inputField(
            userInputPassword = authViewModel.userInputPassword,
            onUserPasswordChanged = { authViewModel.updateUserPassword(it) },
            onKeyboardDone = { authViewModel.check() },
            isPasswordWrong = authUiState.enteredPasswordWrong,

            userInputPhoneNumber = authViewModel.userInputPhoneNumber,
            onUserPhoneNumberChanged = { authViewModel.updateUserPhoneNumber(it) },
            onKeyboardDone2 = { authViewModel.check() },
            isPhoneNumberWrong = authUiState.enteredPhoneNumberWrong,
        )


        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = { authViewModel.check()}
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                fontSize = 16.sp
            )
        }

        OutlinedButton(

            onClick = { authViewModel.forgotPassword() },

            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 16.sp
            )
        }

        Button(
            onClick = { onNextButtonClicked() },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(stringResource(R.string.next))
        }
    }

}

@Composable
fun inputField(
    isPasswordWrong: Boolean,
    userInputPassword: String,
    onUserPasswordChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,

    isPhoneNumberWrong: Boolean,
    userInputPhoneNumber: String,
    onUserPhoneNumberChanged: (String) -> Unit,
    onKeyboardDone2: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = userInputPhoneNumber,
            singleLine = true,
            shape = shapes.large,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            onValueChange = { onUserPhoneNumberChanged(it) },
            label = {
                if (isPhoneNumberWrong) {
                    Text(stringResource(R.string.wrong_phone_number))
                } else {
                    Text(stringResource(R.string.enter_phone_number))
                }
            },
            isError = isPhoneNumberWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone2() }
            )
        )

        OutlinedTextField(
            value = userInputPassword,
            singleLine = true,
            shape = shapes.large,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.surface,
                unfocusedContainerColor = colorScheme.surface,
                disabledContainerColor = colorScheme.surface,
            ),
            onValueChange = onUserPasswordChanged,
            label = {
                if (isPasswordWrong) {
                    Text(stringResource(R.string.wrong_password))
                } else {
                    Text(stringResource(R.string.enter_password))
                }
            },
            isError = isPasswordWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )

    }
}


@Preview(showBackground = true)
@Composable
fun AuthPreview() {
    ProCatFirstTheme {
        //AuthScreen()
    }
}