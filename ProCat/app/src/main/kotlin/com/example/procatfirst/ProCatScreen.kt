package com.example.procatfirst

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.procatfirst.ui.auth.AuthScreen

import com.example.procatfirst.ui.auth.AuthViewModel
import com.example.procatfirst.ui.item.ToolScreen
import com.example.procatfirst.ui.item.ToolViewModel
import com.example.procatfirst.ui.start.StartScreen
import com.example.procatfirst.ui.tools.ToolsScreen


enum class ProCatScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Auth(title = R.string.auth),
    Tool(title = R.string.tool),
    Tools(title = R.string.tools),
    Misha(title = R.string.test),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProCatAppBar(
    currentScreen: ProCatScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@Composable
fun ProCatApp (
    authViewModel: AuthViewModel = viewModel(),
    toolViewModel: ToolViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ProCatScreen.valueOf(
        backStackEntry?.destination?.route ?: ProCatScreen.Start.name
    )

    Scaffold(
        topBar = {
            ProCatAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        //val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ProCatScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ProCatScreen.Start.name) {
                StartScreen(
                    onNextButtonClicked = {
                        navController.navigate(ProCatScreen.Auth.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = ProCatScreen.Auth.name) {
                AuthScreen(
                    onNextButtonClicked = {
                        navController.navigate(ProCatScreen.Tools.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = ProCatScreen.Tools.name) {
                ToolsScreen(
                    onNextButtonClicked = {
                        navController.navigate(ProCatScreen.Tool.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = ProCatScreen.Tool.name) {
                ToolScreen(
                    onNextButtonClicked = {
                        navController.navigate(ProCatScreen.Misha.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = ProCatScreen.Misha.name) {
                TestButtons(
                    onNextButtonClicked = {
                        navController.navigate(ProCatScreen.Start.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        }

    }
}