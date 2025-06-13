package covy.socketalarm.kafka.consumer;

import covy.socketalarm.kafka.response.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaChatMessageConsumer {

  private final SimpMessagingTemplate simpMessagingTemplate;

  @KafkaListener(topics = "chat.room.message.sending")
  public void sendMessage(ChatMessageResponse chatMessageResponse) {
    simpMessagingTemplate.convertAndSend("/topic/public/rooms/"+chatMessageResponse.roomId(), chatMessageResponse);
  }

}
