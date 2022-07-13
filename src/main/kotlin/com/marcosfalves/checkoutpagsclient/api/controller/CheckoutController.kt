package com.marcosfalves.checkoutpagsclient.api.controller

import com.marcosfalves.checkoutpagsclient.api.model.Authentication
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutCreationRequest
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutCreationResponse
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutSessionResponse
import com.marcosfalves.checkoutpagsclient.api.service.CheckoutService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/checkout")
class CheckoutController(val checkoutService: CheckoutService) {

    @PostMapping("/session")
    @ResponseStatus(HttpStatus.CREATED)
    fun createSession(@Valid @RequestBody authentication: Authentication): CheckoutSessionResponse {
        return checkoutService.createSession(authentication)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCheckout(@Valid @RequestBody checkoutCreationRequest: CheckoutCreationRequest): CheckoutCreationResponse {
        return checkoutService.createCheckout(checkoutCreationRequest)
    }
}