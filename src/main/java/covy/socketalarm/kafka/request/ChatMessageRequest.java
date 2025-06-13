package covy.socketalarm.kafka.request;

import java.io.Serializable;
import lombok.Builder;

@Builder
public record ChatMessageRequest(String from, String text) implements Serializable {
}