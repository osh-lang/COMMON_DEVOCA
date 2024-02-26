package com.ssafy.devoca.dm.service;

import com.ssafy.devoca.dm.model.DmDTO;
import com.ssafy.devoca.dm.model.DmRoomDTO;
import com.ssafy.devoca.dm.model.DmUserDTO;
import com.ssafy.devoca.dm.model.LastDateDTO;
import com.ssafy.devoca.dm.model.mapper.DmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DmServiceImpl implements DmService {

    private final DmMapper dmMapper;

    // DM 채팅방 리스트 조회
    @Override
    public List<DmRoomDTO> getDmRoomList(int loginUserIdx) throws Exception {
        return dmMapper.getDmRoomList(loginUserIdx);
    }

    // DM 채팅 내역 조회
    @Override
    public List<DmDTO> getDmList(String roomUuid, int scroll) throws Exception {
        return dmMapper.getDmList(roomUuid, scroll * 10);
    }

    // DM 채팅방 참여자인지 확인
    @Override
    public Boolean getParticipantsYN(String roomUuid, int loginUserIdx) throws Exception {
        return dmMapper.getParticipantsYN(roomUuid, loginUserIdx);
    }

    // 방 랜덤 아이디 가져오기
    @Override
    public String getRoomUuid(int loginUserIdx, int chatUserIdx) throws Exception {
        return dmMapper.getRoomUuid(loginUserIdx, chatUserIdx);
    }

    // 방 생성
    @Override
    public void createRoom(String roomUuid) throws Exception {
        dmMapper.createRoom(roomUuid);
    }

    // 참여자 테이블에 참여자 저장
    @Override
    public void insertParticipants(String roomUuid, int loginUserIdx, int chatUserIdx) throws Exception {
        dmMapper.insertParticipants(roomUuid, loginUserIdx, chatUserIdx);
    }

    // 방 랜덤 아이디로 idx 가져오기
    @Override
    public int getRoomIdxByRoomUuid(String roomUuid) throws Exception {
        return dmMapper.getRoomIdxByRoomUuid(roomUuid);
    }

    @Override
    public String getRoomUuidByRoomIdx(int roomIdx) throws Exception {
        return dmMapper.getRoomUuidByRoomIdx(roomIdx);
    }

    // 메시지 저장
    @Override
    public void saveMessage(DmDTO dmDTO) throws Exception {
        dmMapper.saveMessage(dmDTO);
    }

    @Override
    public void updateLastDate(LastDateDTO lastDateDTO) throws Exception {
        dmMapper.updateLastDate(lastDateDTO);
    }

    @Override
    public void updateLastDateSendDate(DmDTO dmDTO) throws Exception {
        dmMapper.updateLastDateSendDate(dmDTO);
    }

    @Override
    public int getChatUserIdx(String roomUuid, int userIdx) throws Exception {
        return dmMapper.getChatUserIdx(roomUuid, userIdx);
    }

    @Override
    public DmUserDTO getChatUser(String roomUuid, int userIdx) throws Exception {
        return dmMapper.getChatUser(roomUuid, userIdx);
    }
}