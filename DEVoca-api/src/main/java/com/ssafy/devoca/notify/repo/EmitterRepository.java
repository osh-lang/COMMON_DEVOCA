package com.ssafy.devoca.notify.repo;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    public SseEmitter save(String emitterId, SseEmitter sseEmitter);
    public void saveEventCache(String eventCacheId, Object event);
    public Map<String, SseEmitter> findEmittersByUserIdx(int userIdx);
    public Map<String, Object> findEventCachesByUserIdx(int userIdx);
    public void deleteEmitterById(String Id);
    public void deleteEmittersByUserIdx(int userIdx);
    public void deleteEventCacheByUserIdx(int userIdx);
}
