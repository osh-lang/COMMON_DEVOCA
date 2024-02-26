package com.ssafy.devoca.mypage.model.mapper;

import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.FavCategoryDTO;
import com.ssafy.devoca.user.model.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface MypageMapper {

    List<BadgeDTO> getUserBadges(int userIdx) throws SQLException;
    void followUser(@Param("userIdx") int userIdx, @Param("followIdx") int followIdx) throws SQLException;
    void unfollowUser(@Param("userIdx") int userIdx, @Param("followIdx") int followIdx) throws SQLException;
    List<UserDTO> getFollowList(int userIdx) throws SQLException;
    List<UserDTO> getFollowingList(int userIdx) throws SQLException;
    List<UserDTO> recommendFollow(int userIdx) throws SQLException;
    String getObjectName(int userIdx) throws SQLException;
    void saveImageUrl(@Param("url") String url, @Param("userIdx") int userIdx);
}
