package cl.bootcamp.sprint_m5kotlin.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.sprint_m5kotlin.components.NavegationBarButtons
import cl.bootcamp.sprint_m5kotlin.components.ProductCard
import cl.bootcamp.sprint_m5kotlin.viewmodel.CartViewModel
import cl.bootcamp.sprint_m5kotlin.viewmodel.ProductViewModel
import androidx.compose.foundation.pager.rememberPagerState
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.sprint_m5kotlin.model.Product
import kotlin.math.ceil



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListView(navController: NavController,
                    productViewModel: ProductViewModel,
                    totalPrice: Double,
                    cartViewModel: CartViewModel) {
    val products = productViewModel.products

    // Calcular el número total de páginas
    val totalPages = ceil(products.size / 2f).toInt() // 2 productos por página

    // Estado del Pager (necesitamos pasar el número de páginas)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { totalPages })// El estado del Pager, inicializado



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "SHOES TAP",
                        fontSize = 25.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan
                ),
                modifier = Modifier.height(75.dp)
            )
        },
        bottomBar = {
            NavegationBarButtons(navController, totalPrice) // Usa el totalPrice correcto
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                // Calcular el rango de productos para la página actual
                val startIndex = page * 2
                val endIndex = minOf((page + 1) * 2, products.size)
                val productsForPage = products.subList(startIndex, endIndex)

                ProductGrid(productsForPage, navController)
            }
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, navController: NavController) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductCard(
                product = product,
                onClick = { navController.navigate("productDetail/${product.id}") },
                modifier = Modifier
                    .fillMaxWidth(0.5f) // Esto es para asegurar que cada tarjeta ocupe la mitad del ancho
                    .padding(8.dp) // Espaciado entre tarjetas
            )
        }
    }
}