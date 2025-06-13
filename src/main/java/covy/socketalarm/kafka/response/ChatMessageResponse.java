package covy.socketalarm.kafka.response;

import java.io.Serializable;
import lombok.Builder;

@Builder
public record ChatMessageResponse(Long id, Long roomId, String content, String writer) implements
    Serializable {
}
