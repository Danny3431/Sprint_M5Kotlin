package cl.bootcamp.sprint_m5kotlin.viewmodel

import androidx.lifecycle.ViewModel
import cl.bootcamp.sprint_m5kotlin.model.CartItem
import cl.bootcamp.sprint_m5kotlin.model.Product
import cl.bootcamp.sprint_m5kotlin.model.colorMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> get() = _product
    private val _selectedSize = MutableStateFlow<String?>(null)
    val selectedSize: StateFlow<String?> get() = _selectedSize
    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor: StateFlow<String?> get() = _selectedColor
    private val _quantity = MutableStateFlow<Int>(0)
    val quantity: StateFlow<Int> get() = _quantity
    private val _totalPrice = MutableStateFlow<Double>(0.0)
    val totalPrice: StateFlow<Double> get() = _totalPrice

    // Funciones para actualizar los valores
    fun setProduct(product: Product) {
        _product.value = product
    }

    fun setSelectedSize(size: String) {
        _selectedSize.value = size
    }

    fun setSelectedColor(color: String) {
        _selectedColor.value = color
    }

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

    fun setTotalPrice(totalPrice: Double) {
        _totalPrice.value = totalPrice

    }

    // Manejar duplicados al agregar al carrito
    fun addToCart(product: Product, quantity: Int, selectedSize: String, selectedColorName: String) {
        val cartItem = CartItem(product, quantity, selectedSize = selectedSize, selectedColor = selectedColorName)

        // Lógica para agregar el item al carrito
        val existingItem = _cartItems.value.find { it.product.id == product.id }

        if (existingItem != null) {
            // Actualizamos la cantidad si ya existe
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + quantity)
            _cartItems.value = _cartItems.value.map { if (it.product.id == product.id) updatedItem else it }
        } else {
            // Agregamos el producto al carrito si no existe
            _cartItems.value = _cartItems.value + cartItem
        }

        _totalPrice.value += product.price * quantity // Actualizar el precio total
    }

    fun removeFromCart(item: CartItem) {
        _cartItems.value = _cartItems.value - item
    }

    //limpiar carrito
    fun clearCart() {
        _cartItems.value = emptyList()
        _totalPrice.value = 0.0
    }
}