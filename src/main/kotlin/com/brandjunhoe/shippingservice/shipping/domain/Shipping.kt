package com.brandjunhoe.shippingservice.shipping.domain

import com.brandjunhoe.shippingservice.shipping.Address
import com.brandjunhoe.shippingservice.shipping.domain.enums.ShippingStateEnum
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "shipping")
class Shipping(

    @EmbeddedId
    val shippingCode: ShippingCode,

    @Column(name = "order_code", length = 255, nullable = false)
    val orderCode: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "state", columnDefinition = "enum", nullable = false)
    var state: ShippingStateEnum = ShippingStateEnum.SHIPPING_READY,

    @Embedded
    val address: Address,

    @Column(name = "company_name", length = 45)
    var companyName: String? = null,

    @Column(name = "invoice_number", length = 45)
    var invoiceNumber: String? = null,

    @Column(name = "invoice_input_date")
    var invoiceInputDate: Date? = null,

    @Column(name = "shipping_start_date")
    var shippingStartDate: Date? = null,

    @Column(name = "shipping_complete_date")
    var shippingCompleteDate: Date? = null,

    @Column(name = "memo", length = 255)
    var memo: String? = null

) {

    fun saveInvoice(companyName: String, invoiceNumber: String) {
        this.companyName = companyName
        this.invoiceNumber = invoiceNumber
        this.invoiceInputDate = Date()
    }

    fun startShipping() {
        this.shippingStartDate = Date()
    }

    fun completeShipping() {
        this.shippingCompleteDate = Date()
    }

}