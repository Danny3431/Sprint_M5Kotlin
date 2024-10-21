package cl.bootcamp.sprint_m5kotlin.model

data class CartItem(
    val product: Product,
    val quantity: Int,
    val cartId: Int = 0,
    val totalPrice: Double = 0.0,
    val selectedSize: String = "",
    val selectedColor: String = ""
)