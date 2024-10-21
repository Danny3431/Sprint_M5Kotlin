package cl.bootcamp.sprint_m5kotlin.model

import androidx.compose.ui.graphics.Color

data class Product(
    val id: Int ,
    val name: String,
    val price: Double,
    val imageResId: Int,
    val sizes: List<String>,
    val availableColors: List<String>,  // Lista de colores como Strings
    val colorValues: List<Color>,  // Lista de colores como objetos Color
    val selectedSize: String? = null,
    val selectedColor: String? = null,
    val quantity: Int = 0,
    val selectedColorValue: Color? = Color.Transparent // Valor por defecto
)