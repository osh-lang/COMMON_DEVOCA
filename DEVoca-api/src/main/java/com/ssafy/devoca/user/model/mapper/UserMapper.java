package com.ssafy.devoca.user.model.mapper;

import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.FavCategoryDTO;
import com.ssafy.devoca.user.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int loadUserIdx(String userId) throws SQLException;
    int checkUser(long id) throws SQLException;
    void joinUser(UserDTO userDTO) throws SQLException;
    UserDTO getUserInfo(int userIdx) throws SQLException;
    UserDTO getOtherUserInfo(@Param("otherIdx") int otherIdx, @Param("userIdx") int userIdx) throws SQLException;
    void updateUserInfo(UserDTO userDTO) throws SQLException;
    void delFavCategory(int userIdx) throws SQLException;
    void setFavCategory(Map<String, Object> params) throws SQLException;
    List<FavCategoryDTO> getFavCategory(int userIdx) throws SQLException;
    void getBadge(@Param("userIdx") int userIdx, @Param("badgeId") int badgeId) throws SQLException;
    String getUserNickName(int userIdx) throws SQLException;
    List<UserDTO> getOtherFollowingList(@Param("userIdx") int userIdx, @Param("otherIdx") int otherIdx) throws SQLException;
    List<UserDTO> getOtherFollowList(@Param("userIdx") int userIdx, @Param("otherIdx") int otherIdx) throws SQLException;
}
