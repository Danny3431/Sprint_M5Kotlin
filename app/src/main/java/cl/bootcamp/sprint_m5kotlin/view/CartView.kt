package cl.bootcamp.sprint_m5kotlin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.sprint_m5kotlin.components.NavegationBarButtons
import cl.bootcamp.sprint_m5kotlin.model.colorMap
import cl.bootcamp.sprint_m5kotlin.viewmodel.CartViewModel
import cl.bootcamp.sprint_m5kotlin.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(
    navController: NavController,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel
) {
    val products = productViewModel.products
    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val totalPrice by cartViewModel.totalPrice.collectAsState(initial = 0.0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras", fontSize = 25.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan),
                modifier = Modifier.height(75.dp)
            )
        },
        bottomBar = {
            NavegationBarButtons(navController, totalPrice)
        }
    ) { paddingValues ->
        if (cartItems.isEmpty()) {
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Text("El carrito está vacío")
                Button(onClick = { navController.navigate("productListView") }) {
                    Text("Ir a la lista de productos")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                items(cartItems) { item ->
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = item.product.name)
                        Text(text = "Talla: ${item.selectedSize}")
                        Text(text = "Color: ${item.selectedColor.takeIf { it in colorMap.keys } ?: "Desconocido"}")
                        Image(
                            painter = painterResource(id = item.product.imageResId),
                            contentDescription = item.product.name,
                            modifier = Modifier.size(100.dp)
                        )
                        Row {
                            Button(onClick = {
                                if (item.quantity > 1) {
                                    cartViewModel.removeFromCart(item)
                                    cartViewModel.addToCart(
                                        item.product,
                                        -1,
                                        item.selectedSize,
                                        item.selectedColor.toString()
                                    ) // Resta uno
                                }
                            }) { Text("-") }
                            Text(
                                text = " ${item.quantity} ",
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Button(onClick = {
                                cartViewModel.addToCart(
                                    item.product,
                                    1,
                                    item.selectedSize,
                                    item.selectedColor.toString()
                                ) // Suma uno
                            }) { Text("+") }
                            Button(onClick = { cartViewModel.removeFromCart(item) }) { Text("Eliminar") }
                        }
                        Text(text = "Total: $${item.product.price * item.quantity}")
                    }
                }
                item {
                    Text(
                        text = "Total a pagar: $${String.format("%.2f", totalPrice)}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { navController.navigate("productListView") },
                            modifier = Modifier.weight(1f)
                                .padding(end = 8.dp) // Añadir un padding entre los botones
                        ) {
                            Text("Seguir Comprando")
                        }
                        Button(
                            onClick = { navController.navigate("profile") },
                            modifier = Modifier.weight(1f)
                                .padding(start = 8.dp) // Añadir un padding entre los botones
                        ) {
                            Text("Finalizar Compra")
                        }
                    }
                }
            }
        }
    }
}
