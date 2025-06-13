package covy.socketalarm.kafka.usecase;

import covy.socketalarm.kafka.command.ChatMessageCreateCommand;

public interface ChatMessageCreateUseCase {
  Long createChatMessage(ChatMessageCreateCommand command);
}
