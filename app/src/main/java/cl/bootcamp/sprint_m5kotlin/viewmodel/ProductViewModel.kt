package cl.bootcamp.sprint_m5kotlin.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.sprint_m5kotlin.R
import cl.bootcamp.sprint_m5kotlin.model.CartItem
import cl.bootcamp.sprint_m5kotlin.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel( ) {

    // Estado interno Mutable, que sólo se puede cambiar desde dentro del ViewModel
    val product: Product? = null
    val selectedSize: String? = null
    val selectedColor: String? = null
    val quantity: Int = 0
    val totalPrice: Double = 0.0
    val cartItems: List<CartItem> = emptyList()

    val selectedProduct = MutableStateFlow<Product?>(null)
    val selectedSizeState = MutableStateFlow<String?>(null)
    val selectedColorState = MutableStateFlow<String?>(null)
    val quantityState = MutableStateFlow(0)
    val totalPriceState = MutableStateFlow(0.0)


    // Estado externo inmutable, que se puede observar desde fuera del ViewModel
    val selectedProductState: StateFlow<Product?> = selectedProduct




    private val _products = listOf(

        Product(1, "Zapatilla Deportiva", 49.99, R.drawable.shoe1, listOf("37", "39", "40"), listOf("Rojo", "Azul"), listOf(Color.Red, Color.Blue)),
        Product(2, "Zapatilla Casual", 59.99, R.drawable.shoe2, listOf("40", "41"), listOf("Negro", "Blanco"), listOf(Color.Black, Color.White)),
        Product(3, "Zapatilla Running", 69.99, R.drawable.shoe3, listOf("37", "40"), listOf("Rosa", "Amarillo"), listOf(Color.Magenta, Color.Yellow)),
        Product(4, "Zapatilla Elegante", 79.99, R.drawable.shoe4, listOf("38", "41", "44"), listOf("Gris", "Marrón"), listOf(Color.Gray, Color.Magenta)),
        Product(5, "Zapatilla Deportiva", 49.99,R.drawable.shoe5, listOf("37", "43"), listOf("Azul", "Rojo"), listOf(Color.Blue, Color.Red)),
        Product(6, "Zapatilla Casual", 59.99, R.drawable.shoe6, listOf("39", "42"), listOf("Negro", "Gris"), listOf(Color.Black, Color.Gray)),
        Product(7, "Zapatilla Running", 69.99, R.drawable.shoe7, listOf("38", "40"), listOf("Rosa", "Naranja"), listOf(Color.Magenta, Color.Yellow)),
        Product(8, "Zapatilla Elegante", 79.99, R.drawable.shoe8, listOf("37", "38", "42"), listOf("Azul", "Blanco"), listOf(Color.Blue, Color.White)),
        Product(9, "Zapatilla Deportiva", 49.99, R.drawable.shoe9, listOf("38", "40"), listOf("Rojo", "Azul"), listOf(Color.Red, Color.Blue)),
        Product(10, "Zapatilla Running", 59.99, R.drawable.shoe10, listOf("37","38", "42"), listOf("Negro", "Azul"), listOf(Color.Black, Color.Blue)),
        Product(11, "Zapatilla Casual", 69.99, R.drawable.shoe11, listOf("40", "41"), listOf("Amarillo", "Gris"), listOf(Color.Yellow, Color.Gray)),
        Product(12, "Zapatilla Elegante", 79.99, R.drawable.shoe12, listOf("37", "38", "41"), listOf("Blanco", "Marrón"), listOf(Color.White, Color.Magenta)),
        Product(13, "Zapatilla Deportiva", 49.99, R.drawable.shoe13, listOf("38", "39"), listOf("Azul", "Negro"), listOf(Color.Blue, Color.Black)),
        Product(14, "Zapatilla Casual", 59.99, R.drawable.shoe14, listOf("37", "38", "40"), listOf("Rojo", "Gris"), listOf(Color.Red, Color.Gray)),
        Product(15, "Zapatilla Running", 69.99, R.drawable.shoe15, listOf("37", "38", "39"), listOf("Verde", "Azul"), listOf(Color.Green, Color.Blue)),
        Product(16, "Zapatilla Elegante", 79.99, R.drawable.shoe16, listOf("38", "40", "41", "42"), listOf("Blanco", "Negro"), listOf(Color.White, Color.Black)),
    )


    val products: List<Product> get() = _products
}


