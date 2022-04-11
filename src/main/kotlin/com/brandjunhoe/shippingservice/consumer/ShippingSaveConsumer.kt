package com.brandjunhoe.shippingservice.consumer

import com.brandjunhoe.shippingservice.consumer.event.ShippingSaveEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component


@Component
class ShippingSaveConsumer(
    private val eventPublisher: ApplicationEventPublisher,
    private val objectMapper: ObjectMapper
) : AcknowledgingMessageListener<String, String> {


    @KafkaListener(
        topics = ["\${spring.kafka.consumer.topic}"],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {

        val data = objectMapper.readValue(data.value(), ShippingSaveEvent::class.java)
        eventPublisher.publishEvent(data)
        acknowledgment?.acknowledge()

    }


}