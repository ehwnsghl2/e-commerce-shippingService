package com.brandjunhoe.shippingservice.shipping.infra

import com.brandjunhoe.shippingservice.shipping.domain.Shipping
import com.brandjunhoe.shippingservice.shipping.domain.ShippingCode
import com.brandjunhoe.shippingservice.shipping.domain.ShippingRepository
import org.springframework.data.jpa.repository.JpaRepository

interface ShippingJpaRepository : JpaRepository<Shipping, ShippingCode>, ShippingRepository {
}