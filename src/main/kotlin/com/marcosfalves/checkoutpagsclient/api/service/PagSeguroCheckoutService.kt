package com.marcosfalves.checkoutpagsclient.api.service

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.marcosfalves.checkoutpagsclient.api.model.Authentication
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutCreationRequest
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutCreationResponse
import com.marcosfalves.checkoutpagsclient.api.model.CheckoutSessionResponse
import kong.unirest.HttpResponse
import kong.unirest.Unirest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

@Service
class PagSeguroCheckoutService() : CheckoutService {

    @Value("\${api.pagseguro.host}")
    private lateinit var apiHost: String

    override fun createSession(authentication: Authentication): CheckoutSessionResponse {

        val response: HttpResponse<String> =
            Unirest.post("${apiHost}/sessions")
                .header("Content-Type", "application/xml")
                .queryString("email", authentication.email)
                .queryString("token", authentication.token)
                .asString()

        return if (response.isSuccess) {
            XmlMapper().readValue(response.body, CheckoutSessionResponse::class.java)
        } else {
            throw RuntimeException("Status: ${response.status}, Body: ${response.body}, Error: ${response.parsingError?.get()}")
        }
    }

    override fun createCheckout(checkoutCreationRequest: CheckoutCreationRequest): CheckoutCreationResponse {

        var item = checkoutCreationRequest.item

        val response: HttpResponse<String> =
            Unirest.post("${apiHost}/checkout")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryString("email", checkoutCreationRequest.authentication.email)
                .queryString("token", checkoutCreationRequest.authentication.token)
                .field("email", checkoutCreationRequest.authentication.email)
                .field("token", checkoutCreationRequest.authentication.token)

                .field("currency", "BRL")
                .field("reference", UUID.randomUUID().toString())

                .field("itemId1", item.id)
                .field("itemDescription1", item.description)
                .field("itemAmount1", item.amount)
                .field("itemQuantity1", item.quantity.toString())
                .field("itemWeight1", item.weight.toString())
                .field("shippingCost", item.shippingCost)
                .field("senderEmail", checkoutCreationRequest.senderEmail)

                //Fixo para testes
                .field("senderName", "Jose Comprador")
                .field("senderAreaCode", "44")
                .field("senderPhone", "988888888")
                .field("senderCPF", "38440987803")
                .field("senderBornDate", "12/03/1990")
                .field("shippingType", "1")
                .field("shippingAddressStreet", "Av.Brig.Faria Lima")
                .field("shippingAddressNumber", "1384")
                .field("shippingAddressComplement", "2o andar")
                .field("shippingAddressDistrict", "Jardim Paulistano")
                .field("shippingAddressPostalCode", "01452002")
                .field("shippingAddressCity", "Sao Paulo")
                .field("shippingAddressState", "SP")
                .field("shippingAddressCountry", "BRA")
                .field("maxUses", "1")
                .field("maxAge", "3000")
                .asString()

        val xmlMapper = XmlMapper().registerModule(JavaTimeModule())

        return if (response.isSuccess) {
            xmlMapper.readValue(response.body, CheckoutCreationResponse::class.java)
        } else {
            throw RuntimeException("Status: ${response.status}, Body: ${response.body}, Error: ${response.parsingError?.get()}")
        }
    }
}