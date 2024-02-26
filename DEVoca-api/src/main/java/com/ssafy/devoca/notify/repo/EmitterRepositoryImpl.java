package com.ssafy.devoca.notify.repo;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
/*
 *  Emitter, event cache 저장 및 crud
 *  @author 배성연
 */

@Repository
public class EmitterRepositoryImpl implements EmitterRepository{
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    @Override   // emitter 저장
    public SseEmitter save(String emitterId,SseEmitter sseEmitter){
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    @Override   // 이벤트 저장
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    @Override   // userIdx로 emitter 전부 찾기
    public Map<String, SseEmitter> findEmittersByUserIdx(int userIdx) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userIdx+"_"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override   // userIdx로 event cache 전부 찾기
    public Map<String, Object> findEventCachesByUserIdx(int userIdx) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userIdx+"_"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override   // emitter Id로 emitter 삭제
    public void deleteEmitterById(String id) {
        emitters.remove(id);
    }

    @Override   // userIdx로 그 회원의 emitter 전부 지우기
    public void deleteEmittersByUserIdx(int userIdx) {
        emitters.forEach(
                (key, emitter) -> {
                    if(key.startsWith(userIdx+"_")){
                        emitters.remove(key);
                    }
                }
        );
    }

    @Override   // userIdx로 그 회원의 event cache 전부 지우기
    public void deleteEventCacheByUserIdx(int userIdx) {
        eventCache.forEach(
                (key, emitter) -> {
                    if(key.startsWith(userIdx+"_")){
                        eventCache.remove(key);
                    }
                }
        );
    }


}
