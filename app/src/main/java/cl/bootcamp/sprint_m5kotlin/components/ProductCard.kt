package cl.bootcamp.sprint_m5kotlin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.sprint_m5kotlin.model.Product

@Composable
fun ProductCard(product: Product,
                onClick: () -> Unit,
                modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)


        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth() // Asegura que la imagen ocupe todo el ancho
                    .height(220.dp), // Ajusta la altura según sea necesario
                contentScale = ContentScale.Crop // Asegura que la imagen se recorte correctamente
            )
            Text(text = product.name)
            Text(text = "$${product.price}")

        }
    }
}

@Composable
fun NavegationBarButtons(navController: NavController, totalPrice: Double) {
    val selectedItem = remember { mutableStateOf(0) }
    val items = listOf("Home", "Cart", "Profile")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.ShoppingCart,
        Icons.Filled.Person
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (item == "Cart" && totalPrice > 0) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = Color.Red
                                ) {
                                    Text(
                                        text = "${totalPrice.toInt()}", // Ajusta a entero si es necesario
                                        color = Color.White,
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }
                        ) {
                            Icon(
                                icons[index],
                                contentDescription = item
                            )
                        }
                    } else {
                        Icon(
                            icons[index],
                            contentDescription = item
                        )
                    }
                },
                label = { Text(item) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    when (index) {
                        0 -> navController.navigate("productListView")
                        1 -> navController.navigate("cart")
                        2 -> navController.navigate("profile")
                    }
                }
            )
        }
    }
}