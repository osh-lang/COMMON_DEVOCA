package com.ssafy.devoca.dm.model.mapper;

import com.ssafy.devoca.dm.model.DmDTO;
import com.ssafy.devoca.dm.model.DmRoomDTO;
import com.ssafy.devoca.dm.model.DmUserDTO;
import com.ssafy.devoca.dm.model.LastDateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface DmMapper {
    public List<DmRoomDTO> getDmRoomList(int loginUserIdx) throws SQLException;

    public List<DmDTO> getDmList(@Param("roomUuid") String roomUuid, @Param("scroll") int scroll) throws SQLException;

    public Boolean getParticipantsYN(@Param("roomUuid") String roomUuid, @Param("loginUserIdx") int loginUserIdx) throws SQLException;

    public String getRoomUuid(@Param("loginUserIdx") int loginUserIdx, @Param("chatUserIdx") int chatUserIdx) throws SQLException;

    public int createRoom(@Param("roomUuid") String roomUuid) throws SQLException;

    public void insertParticipants(@Param("roomUuid") String roomUuid, @Param("loginUserIdx") int loginUserIdx, @Param("chatUserIdx") int chatUserIdx) throws SQLException;
    public int getRoomIdxByRoomUuid(String roomUuid) throws SQLException;
    public String getRoomUuidByRoomIdx(int roomIdx) throws SQLException;
    public void saveMessage(DmDTO dmDTO) throws SQLException;
    public void updateLastDate(LastDateDTO lastDateDTO) throws SQLException;
    public void updateLastDateSendDate(DmDTO dmDTO) throws SQLException;
    public int getChatUserIdx(@Param("roomUuid") String roomUuid, @Param("userIdx") int userIdx) throws SQLException;
    public DmUserDTO getChatUser(@Param("roomUuid") String roomUuid, @Param("userIdx") int userIdx) throws SQLException;
}