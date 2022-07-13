package com.marcosfalves.checkoutpagsclient.api.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class Authentication(
    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val token: String
)