package com.marcosfalves.checkoutpagsclient.api.model

data class ItemCheckout(
    val id: String,
    val description: String,
    val amount: String,
    val quantity: Int,
    val weight: Int,
    val shippingCost: String
)
