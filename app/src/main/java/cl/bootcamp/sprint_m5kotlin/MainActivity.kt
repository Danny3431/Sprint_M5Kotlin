package cl.bootcamp.sprint_m5kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.sprint_m5kotlin.viewmodel.CartViewModel
import cl.bootcamp.sprint_m5kotlin.viewmodel.ProductViewModel
import cl.bootcamp.sprint_m5kotlin.navegation.AppNavigationHost
import cl.bootcamp.sprint_m5kotlin.ui.theme.Sprint_M5Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: ProductViewModel by viewModels()
        val cartViewModel: CartViewModel by viewModels()


        setContent {
            Sprint_M5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AppNavigationHost(viewModel = viewModel, cartViewModel = cartViewModel)
                }
            }
        }
    }
}