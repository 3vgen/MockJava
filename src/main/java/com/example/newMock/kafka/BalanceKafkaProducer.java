package com.example.newMock.kafka;

import com.example.newMock.model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BalanceKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(BalanceKafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${kafka.topic.balance}")
    private String topic;

    public BalanceKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ResponseDTO responseDTO) {
        try {
            String json = objectMapper.writeValueAsString(responseDTO);
            kafkaTemplate.send(topic, responseDTO.getRqUID(), json);
            log.info("Отправлено в Kafka: {}", json);
        } catch (Exception e) {
            log.error("Ошибка отправки в Kafka: {}", e.getMessage());
        }
    }
}