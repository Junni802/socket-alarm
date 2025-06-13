package covy.socketalarm.handler;

import covy.socketalarm.dao.AlarmDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(EchoHandler.class);
  @Autowired
  private AlarmDao alarmDao;

  public void setAlarmDao(AlarmDao alarmDao) {
    this.alarmDao = alarmDao;
  }

  private static final Logger logger = (Logger) LoggerFactory.getLogger(WebSocketHandler.class);

  private List<WebSocketSession> sessions = new ArrayList<>();


  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info("socket 연결");

    sessions.add(session);
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
      throws Exception {
    for (WebSocketSession webSocketSession : sessions) {
      String hsid = (String) webSocketSession.getAttributes().get("user_id");
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    super.afterConnectionClosed(session, status);
  }
}
