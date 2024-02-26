package com.ssafy.devoca.dm.service;

import com.ssafy.devoca.dm.model.DmDTO;
import com.ssafy.devoca.dm.model.DmRoomDTO;
import com.ssafy.devoca.dm.model.DmUserDTO;
import com.ssafy.devoca.dm.model.LastDateDTO;

import java.util.List;

public interface DmService {
    public List<DmRoomDTO> getDmRoomList(int loginUserIdx) throws Exception;
    public List<DmDTO> getDmList(String roomUuid, int scroll) throws Exception;
    public Boolean getParticipantsYN(String roomUuid, int loginUserIdx) throws Exception;
    public String getRoomUuid(int loginUserIdx, int chatUserIdx) throws Exception;
    public void createRoom(String roomUuid) throws Exception;
    public void insertParticipants(String roomUuid, int loginUserIdx, int chatUserIdx) throws Exception;
    public int getRoomIdxByRoomUuid(String roomUuid) throws Exception;
    public String getRoomUuidByRoomIdx(int roomIdx) throws Exception;
    public void saveMessage(DmDTO dmDTO) throws Exception;
    public void updateLastDate(LastDateDTO lastDateDTO) throws Exception;
    public void updateLastDateSendDate(DmDTO dmDTO) throws Exception;
    public int getChatUserIdx(String roomUuid, int userIdx) throws Exception;
    public DmUserDTO getChatUser(String roomUuid, int userIdx) throws Exception;
}