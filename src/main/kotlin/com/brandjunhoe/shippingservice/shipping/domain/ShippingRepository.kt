package com.brandjunhoe.shippingservice.shipping.domain

interface ShippingRepository {
    fun save(shipping: Shipping) : Shipping

}