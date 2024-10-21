package cl.bootcamp.sprint_m5kotlin.navegation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.sprint_m5kotlin.viewmodel.CartViewModel
import cl.bootcamp.sprint_m5kotlin.viewmodel.ProductViewModel
import cl.bootcamp.sprint_m5kotlin.view.CartView
import cl.bootcamp.sprint_m5kotlin.view.PaymentView
import cl.bootcamp.sprint_m5kotlin.view.ProductDetailView
import cl.bootcamp.sprint_m5kotlin.view.ProductListView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationHost(viewModel: ProductViewModel, cartViewModel: CartViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "productListView") {
        composable("productListView") {
            ProductListView( navController, viewModel,0.0 , cartViewModel)
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
            ProductDetailView( navController, viewModel, 0.0, cartViewModel)
        }
        composable("cart") {
            CartView(navController, cartViewModel, viewModel)
        }
        composable("profile") {
            PaymentView(navController, viewModel, cartViewModel)
        }
    }
}



