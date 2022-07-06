package com.marcosfalves.checkoutpagsclient.api.controller

import com.marcosfalves.checkoutpagsclient.infrastructure.client.PagSeguroClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/checkout")
class CheckoutController(val pagSeguroClient: PagSeguroClient) {

    @GetMapping
    @ResponseBody
    fun createCheckout(): String {
        pagSeguroClient.getSession()
        return "Chamou o checkout"
    }

}