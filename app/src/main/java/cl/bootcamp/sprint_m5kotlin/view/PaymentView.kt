package cl.bootcamp.sprint_m5kotlin.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.sprint_m5kotlin.components.NavegationBarButtons
import cl.bootcamp.sprint_m5kotlin.viewmodel.CartViewModel
import cl.bootcamp.sprint_m5kotlin.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentView(
    navController: NavController,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    // Recolectar el total del carrito desde el CartViewModel
    val totalPrice = cartViewModel.totalPrice.collectAsState(initial = 0.0).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pago", fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan
                ),
                modifier = Modifier.height(75.dp)
            )
        },
        bottomBar = {
            NavegationBarButtons(navController, totalPrice) // Pasar el precio total
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Tienes que pagar: $${String.format("%.2f", totalPrice)}", style = MaterialTheme.typography.titleLarge)
            Text("Método de pago: Tarjeta de crédito, Débito o Transferencia", style = MaterialTheme.typography.titleMedium)

            //Botones de pago y cancelar

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón para procesar el pago
                Button(onClick = {
                    // Lógica para procesar el pago aquí
                    navController.navigate("productListView")
                }, modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text("Procesar Pago")
                }

                // Botón para cancelar la compra
                Button(
                    onClick = {
                        cartViewModel.clearCart() // Limpiar el carrito
                        navController.navigate("productListView") // Navegar a la lista de productos
                    },
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Text("Cancelar Compra")
                }
            }
        }
    }
}