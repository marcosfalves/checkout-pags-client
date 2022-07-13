package com.marcosfalves.checkoutpagsclient.api.model

import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

data class CheckoutCreationRequest(

    @field:Valid
    @field:NotNull
    val authentication: Authentication,

    @field:Email
    val senderEmail: String,

    @field:NotNull
    val item: ItemCheckout
)
