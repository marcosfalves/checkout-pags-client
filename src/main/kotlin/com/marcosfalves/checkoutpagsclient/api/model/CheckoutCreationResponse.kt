package com.marcosfalves.checkoutpagsclient.api.model

import java.time.OffsetDateTime

data class CheckoutCreationResponse(
    var code: String? = "",
    var date: OffsetDateTime? = null
)
