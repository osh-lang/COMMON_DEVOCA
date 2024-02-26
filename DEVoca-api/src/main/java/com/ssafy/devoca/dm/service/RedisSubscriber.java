package com.ssafy.devoca.dm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.devoca.dm.model.DmDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * 메시지 리스너
 * 구독자에게 메시지 전달
 *
 * @author kimjuyi
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    private final DmService dmService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("onMessage : {}", message);
        String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

        try {
            DmDTO dmDTO = objectMapper.readValue(publishMessage, DmDTO.class);
            String roomUuid = dmService.getRoomUuidByRoomIdx(dmDTO.getRoomIdx());
            dmDTO.setRoomIdx(0);
            messagingTemplate.convertAndSend("/sub/chat/" + roomUuid, dmDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
