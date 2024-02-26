package com.ssafy.devoca.notify.model.mapper;

import com.ssafy.devoca.notify.model.NotifyDTO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface NotifyMapper {
    public void saveNotify(NotifyDTO notifyDTO) throws SQLException;
    public List<Integer> getAllUserIdx() throws SQLException;
    public void updateNotificationReadYN(@Param("loginUserIdx") int loginUserIdx) throws SQLException;
    public List<NotifyDTO> getNotification(int loginUserIdx)
        throws SQLException;
}
