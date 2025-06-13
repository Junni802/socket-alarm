package covy.socketalarm.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public void publishMessage(String topic, Object message) {
    kafkaTemplate.send(topic, message);
  }
}