package com.brandjunhoe.shippingservice.consumer.event

import com.brandjunhoe.shippingservice.shipping.Address
import com.brandjunhoe.shippingservice.shipping.domain.Shipping
import com.brandjunhoe.shippingservice.shipping.domain.ShippingCode

class ShippingSaveEvent(

    val orderCode: String,

    val postCode: String,

    val address: String,

    val addressDetail: String

) {
    fun toEntity(): Shipping =
        Shipping(
            ShippingCode("code"),
            orderCode,
            address = Address(postCode, address, addressDetail)
        )

}
