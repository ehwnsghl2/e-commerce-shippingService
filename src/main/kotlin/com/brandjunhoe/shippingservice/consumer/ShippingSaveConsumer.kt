package com.brandjunhoe.shippingservice.consumer

import com.brandjunhoe.shippingservice.consumer.event.ShippingSaveDTO
import com.brandjunhoe.shippingservice.shipping.application.ShippingService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class ShippingSaveConsumer(
    private val shippingService: ShippingService,
    private val objectMapper: ObjectMapper
) : AcknowledgingMessageListener<String, String> {


    @KafkaListener(
        topics = ["\${spring.kafka.consumer.topic}"],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val data = objectMapper.readValue(data.value(), ShippingSaveDTO::class.java)
        shippingService.save(data)
        acknowledgment?.acknowledge()
    }

}

// {"orderCode" : "orderCode", "postCode" : "06555", "address" : "address", "addressDetail" : "addressDetail"}