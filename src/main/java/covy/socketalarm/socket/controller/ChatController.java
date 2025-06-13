package covy.socketalarm.socket.controller;

import covy.socketalarm.kafka.command.ChatMessageCreateCommand;
import covy.socketalarm.kafka.request.ChatMessageRequest;
import covy.socketalarm.kafka.response.ChatMessageResponse;
import covy.socketalarm.kafka.producer.KafkaProducer;
import covy.socketalarm.kafka.usecase.ChatMessageCreateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
class ChatController {
  private final ChatMessageCreateUseCase chatMessageCreateUseCase; // service(MVC) 계층
  private final KafkaProducer kafkaProducer;

  public void sendMessage(@DestinationVariable Long roomId, @Payload ChatMessageRequest chatMessage) {
    ChatMessageCreateCommand chatMessageCreateCommand = ChatMessageCreateCommand.builder()
        .content(chatMessage.text())
        .from(chatMessage.from())
        .roomId(roomId)
        .build();
    Long chatId = chatMessageCreateUseCase.createChatMessage(chatMessageCreateCommand); // DB에 등록 후 Chat Message Id 반환
    ChatMessageResponse chatMessageResponse = ChatMessageResponse.builder()
        .id(chatId)
        .roomId(roomId)
        .content(chatMessage.text())
        .writer(chatMessage.from())
        .build();
    kafkaProducer.publishMessage("chat.room.message.sending", chatMessageResponse);
  }
}
