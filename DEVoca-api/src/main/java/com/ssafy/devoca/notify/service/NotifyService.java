package com.ssafy.devoca.notify.service;

import com.ssafy.devoca.notify.model.NotifyDTO;
import com.ssafy.devoca.notify.model.mapper.NotifyMapper;
import com.ssafy.devoca.notify.repo.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyService {
    private static final Long DEFAULT_TIMEOUT = 600L * 1000 * 60;

    private final EmitterRepository emitterRepository;
    private final NotifyMapper notifyMapper;

    // Emitter 만들어 연결
    public SseEmitter connect(int userIdx, String lastEventId){
        String emitterId = createEId(userIdx);
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        log.info("emitter 생성 및 저장 완료");

        // emitter 만료시 삭제 설정
        emitter.onCompletion(() -> emitterRepository.deleteEmitterById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteEmitterById(emitterId));

        // 503 error 방지 : 더미 이벤트 전송
        String eventId = createEId(userIdx);
        sendNotification(emitter, eventId, emitterId, "SSE Connected.");
        log.info("더미 이벤트 전송 완료");

        // 미수신한 lastEvent가 있다면 재전송
        if(!lastEventId.isEmpty()){
            Map<String, Object> eventCaches = emitterRepository.findEventCachesByUserIdx(userIdx);
            eventCaches.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
        }
        log.info("미수신 이벤트 재전송 완료");

        return emitter;
    }

    // userIdx로 Emitter, eventId 만드는 메서드
    private String createEId(int userIdx){
        return userIdx + "_" + System.currentTimeMillis();
    }

    // 각 이벤트 emitter로 전송하는 메서드
    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data){
        try{
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("sse")
                    .data(data)
            );
        }catch (IOException e){
            emitterRepository.deleteEmitterById(emitterId);
        }
    }

    // 한 사람의 모든 Emitter에다가 이벤트 전부 전송하는 메서드
    public void send(int userIdx, int notificationType, int notificationLinkId) throws Exception{
        NotifyDTO notifyDTO = new NotifyDTO(userIdx, notificationType, notificationLinkId, null, false);
        notifyDTO.setNotificationId(createEId(userIdx));
        log.info("notifyDTO 생성 완료");
        // DM 제외 알림 DB에 저장
        if(notificationType != 4){
            notifyMapper.saveNotify(notifyDTO);
        }
        log.info("notifyDTO DB 저장 완료");

        String eventId = notifyDTO.getNotificationId();
        Map<String, SseEmitter> emitters = emitterRepository.findEmittersByUserIdx(userIdx);
        emitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notifyDTO);
                    sendNotification(emitter, eventId, key, notifyDTO);
                }
        );
        log.info(userIdx + "에게 알림 보내기 완료 : " + notifyDTO.toString());
    }

    public List<Integer> getAllUserIdx() throws Exception{
        return notifyMapper.getAllUserIdx();
    }

    // 알림창 조회 메서드
    public  List<NotifyDTO> getNotification(int loginUserIdx) throws Exception{
        // 알림 가져오기
        List<NotifyDTO> notifyDTOList = notifyMapper.getNotification(loginUserIdx);
        // 지금까지의 모든 알림 isRead true로 바꾸기
        notifyMapper.updateNotificationReadYN(loginUserIdx);
        return notifyDTOList;
    }

}
