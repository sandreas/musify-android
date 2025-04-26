package com.codewithfk.musify_android.ui.navigation

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codewithfk.musify_android.ui.feature.login.LoginScreen
import com.codewithfk.musify_android.ui.feature.onboarding.OnboardingScreen
import com.codewithfk.musify_android.ui.feature.register.RegisterScreen


@Composable
fun AppNavGraph(navController: NavHostController, startDestination: MusifyNavRoute) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<OnboardingRoute> {
            OnboardingScreen(
                navController
            )
        }
        composable<LoginRoute> {
            LoginScreen(navController)
        }

        composable<RegisterRoute> {
            RegisterScreen(navController)
        }
        composable<HomeRoute> {
            Log.d("AppNavGraph", "HomeScreen is not implemented yet")
            Text(text = "Home Screen", style = MaterialTheme.typography.titleLarge)
            // HomeScreen(navController)
        }
    }
}