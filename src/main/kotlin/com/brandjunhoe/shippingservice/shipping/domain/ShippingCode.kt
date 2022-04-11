package com.brandjunhoe.shippingservice.shipping.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Create by DJH on 2022/03/27.
 */
@Embeddable
class ShippingCode(

    @Column(name = "shipping_code")
    val shippingCode: String

) : Serializable