package com.brandjunhoe.shippingservice.shipping.application

import com.brandjunhoe.shippingservice.consumer.event.ShippingSaveDTO
import com.brandjunhoe.shippingservice.shipping.domain.ShippingRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ShippingService(private val shippingRepository: ShippingRepository) {


    @EventListener
    fun save(DTO: ShippingSaveDTO) {
        shippingRepository.save(DTO.toEntity())
    }

}