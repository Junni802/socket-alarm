package covy.socketalarm.kafka.command;

import lombok.Builder;

@Builder
public record ChatMessageCreateCommand(Long roomId, String content, String from) {
}
