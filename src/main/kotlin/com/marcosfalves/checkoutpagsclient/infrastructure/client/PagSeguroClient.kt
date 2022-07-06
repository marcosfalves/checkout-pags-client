package com.marcosfalves.checkoutpagsclient.infrastructure.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PagSeguroClient() {

    private val URI_SESSION = "/sessions"

    @Autowired
    private lateinit var restTemplate: RestTemplate

    fun getSession() {
        val params: MutableMap<String, String> = mutableMapOf()
        params.put("email", "marquinhofalves@gmail.com")
        params.put("token", "264AC88A0C1347C4872E98BE8FB7B3A0")

        val response = restTemplate.getForObject(URI_SESSION, String::class.java, params)

        println(response)

    }
}