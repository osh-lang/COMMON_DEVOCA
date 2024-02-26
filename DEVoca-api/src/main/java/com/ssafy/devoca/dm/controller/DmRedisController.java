package com.ssafy.devoca.dm.controller;

import com.ssafy.devoca.dm.model.DmDTO;
import com.ssafy.devoca.dm.model.LastDateDTO;
import com.ssafy.devoca.dm.service.DmService;
import com.ssafy.devoca.dm.service.RedisPublisher;
import com.ssafy.devoca.dm.service.RedisService;
import com.ssafy.devoca.notify.service.NotifyService;
import com.ssafy.devoca.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * DM Redis 관련 Controller
 *
 * @author kimjuyi
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class DmRedisController {
    private final RedisPublisher redisPublisher;
    private final ChannelTopic channelTopic;

    private final RedisService redisService;
    private final DmService dmService;
    private final UserService userService;
    private final NotifyService notifyService;

    private final RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/chat/{roomUuid}/enter")
    public void userEnter(@DestinationVariable("roomUuid") String roomUuid, LastDateDTO lastDateDTO) {
        log.info("userEnter : {} {}", roomUuid, lastDateDTO);
        redisService.redisEnterUser(roomUuid, lastDateDTO.getUserId());

        // 마지막 조회 시간 업데이트
        try {
            int roomIdx = dmService.getRoomIdxByRoomUuid(roomUuid);
            lastDateDTO.setRoomIdx(roomIdx);
        } catch (Exception e) {
            log.error("roomUuid로 roomIdx 가져오기 에러 : {}", e);
            throw new RuntimeException(e);
        }

        try {
            dmService.updateLastDate(lastDateDTO);
        } catch (Exception e) {
            log.error("updateLastDate 마지막 조회 시간 갱신 에러 : {}", e);
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/chat/{roomUuid}")
    public void handleChatMessage(@DestinationVariable("roomUuid") String roomUuid, DmDTO dmDTO) {
        log.info("hadleChatMessage 호출 : {} {}", roomUuid, dmDTO);

        try {
            int roomIdx = dmService.getRoomIdxByRoomUuid(roomUuid);
            dmDTO.setRoomIdx(roomIdx);
            redisPublisher.publish(channelTopic, dmDTO);
        } catch (Exception e) {
            log.error("roomUuid로 roomIdx 가져오기 에러 : {}", e);
            throw new RuntimeException(e);
        }

        try {
            log.info("메시지 DB 저장 : {}", dmDTO);
            dmService.saveMessage(dmDTO);
        } catch (Exception e) {
            log.error("메시지 DB 저장 에러 : {}", e);
            throw new RuntimeException(e);
        }

        try {
            log.info("updateLastDateSendDate");
            dmService.updateLastDateSendDate(dmDTO);
        } catch (Exception e) {
            log.error("updateLastDateSendDate 마지막 조회 시간 = 메시지 전송 시간으로 저장 에러 : {}", e);
            throw new RuntimeException(e);
        }

        Long userCnt = redisTemplate.opsForList().size(roomUuid);
        if(userCnt == 1) {
            // push 알림 보내기
            try {
                int userIdx = userService.loadUserIdxById(dmDTO.getSendUserId());
                int pushUserIdx = dmService.getChatUserIdx(roomUuid, userIdx);
                int roomIdx = dmService.getRoomIdxByRoomUuid(roomUuid);
                notifyService.send(pushUserIdx, 4, roomIdx);
                log.info("push 알림 보내기 성공 : {}", pushUserIdx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @MessageMapping("/chat/{roomUuid}/exit")
    public void userExit(@DestinationVariable("roomUuid") String roomUuid, LastDateDTO lastDateDTO) {
        log.info("userExit : {} {}", roomUuid, lastDateDTO);

        // redis 저장소 삭제
        redisService.redisUserExit(roomUuid, lastDateDTO.getUserId());

        // 마지막 조회 시간 업데이트
        try {
            int roomIdx = dmService.getRoomIdxByRoomUuid(roomUuid);
            lastDateDTO.setRoomIdx(roomIdx);
        } catch (Exception e) {
            log.error("roomUuid로 roomIdx 가져오기 에러 : {}", e);
            throw new RuntimeException(e);
        }

        try {
            dmService.updateLastDate(lastDateDTO);
        } catch (Exception e) {
            log.error("updateLastDate 마지막 조회 시간 갱신 에러 : {}", e);
            throw new RuntimeException(e);
        }
    }
}
