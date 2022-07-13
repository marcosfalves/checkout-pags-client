package com.marcosfalves.checkoutpagsclient.api.service

import com.marcosfalves.checkoutpagsclient.api.model.Authentication
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutCreationRequest
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutCreationResponse
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutSessionResponse

interface CheckoutService {

    fun createSession(authentication: Authentication): CheckoutSessionResponse
    fun createCheckout(checkoutCreationRequest: CheckoutCreationRequest): CheckoutCreationResponse

}