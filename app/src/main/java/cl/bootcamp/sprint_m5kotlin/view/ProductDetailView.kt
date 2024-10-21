package cl.bootcamp.sprint_m5kotlin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.sprint_m5kotlin.components.NavegationBarButtons
import cl.bootcamp.sprint_m5kotlin.model.colorMap
import cl.bootcamp.sprint_m5kotlin.viewmodel.CartViewModel
import cl.bootcamp.sprint_m5kotlin.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailView(navController: NavController,
                      productViewModel: ProductViewModel,
                      totalPrice: Double,
                      cartViewModel: CartViewModel) {
    val productId = navController.currentBackStackEntry?.arguments?.getString("productId")?.toInt() ?: 0

    // Acceder al producto desde el ViewModel
    val product = productViewModel.products.find { it.id == productId }

    if (product == null) {
        Text("Producto no encontrado")
        return
    }
    // Estado para la talla seleccionada
    var selectedSize by remember { mutableStateOf<String?>(null) }
    var selectedColor by remember { mutableStateOf<Color?>(null) } // Usar Color para los círculos
    var selectedColorName by remember { mutableStateOf<String?>(null) } // Almacena el nombre del color
    var showError by remember { mutableStateOf(false) } // Estado para manejar errores



    // Mostrar la imagen del producto
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Producto Seleccionado",fontSize = 25.sp, // Ajusta el tamaño de la fuente
                    maxLines = 1, // Limita a una línea
                    overflow = TextOverflow.Ellipsis) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan // Color de fondo a cyan
                ),
                modifier = Modifier.height(75.dp) // Ajusta la altura aquí
            )

        },
        bottomBar = {
            NavegationBarButtons(navController, 0.0) // Pasar el precio total si es necesario
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Text(text = product.name, style = MaterialTheme.typography.headlineMedium)

            // Mostrar tallas como botones
            Text("Selecciona tu talla:", style = MaterialTheme.typography.bodyMedium)
            Row {
                product.sizes.forEach { size ->
                    Button(
                        onClick = { selectedSize = size },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedSize == size) Color.Cyan else Color.Gray
                        )
                    ) {
                        Text(text = size)
                    }
                }
            }

            // Mostrar colores como círculos
            Text("Selecciona tu color:", style = MaterialTheme.typography.bodyMedium)
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                product.availableColors.forEach { colorName ->
                    val color = colorMap[colorName] ?: Color.Transparent
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .background(color, shape = CircleShape)
                            .border(
                                width = 2.dp,
                                color = if (selectedColorName == colorName) Color.Cyan else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                selectedColorName = colorName // Almacena el nombre del color
                            }
                    )
                }
            }


            // Ajustar el tamaño de la imagen
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .size(350.dp)
                    .padding(vertical = 16.dp)
            )

            Text(text = "$${product.price}", style = MaterialTheme.typography.bodyLarge)

            // Botón para agregar al carrito
            Button(onClick = {
                if (selectedSize != null && selectedColorName != null) {
                    // Agrega el producto al carrito usando el nombre del color
                    cartViewModel.addToCart(product, 1, selectedSize!!, selectedColorName!!)
                    navController.navigate("cart")
                } else {
                    showError = true // Mostrar error si no se selecciona nada
                }
            }) {
                Text("Agregar al Carrito")
            }

            // Mensaje de error si no se seleccionó talla o color
            if (showError) {
                Text("Por favor, selecciona una talla y un color.", color = Color.Red)
            }
        }
    }
}