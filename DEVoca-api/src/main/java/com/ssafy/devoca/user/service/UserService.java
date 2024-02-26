package com.ssafy.devoca.user.service;

import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.FavCategoryDTO;
import com.ssafy.devoca.user.model.UserDTO;
import org.apache.catalina.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    int loadUserIdx(String userId) throws Exception;
    int loadUserIdxById(String userId) throws Exception;
    String joinUser(UserDTO userDTO) throws Exception;
    UserDTO getUserInfo(int userIdx) throws Exception;
    void updateUserInfo(UserDTO userDTO) throws Exception;
    void updateFavCategory(Map<String, Object> params) throws Exception;
    void delFavCategory(int userIdx) throws Exception;
    void setFavCategory(Map<String, Object> params) throws Exception;
    List<FavCategoryDTO> getFavCategory(int userIdx) throws Exception;
    UserDTO getOtherUserInfo(int otherIdx, int userIdx) throws Exception;
    String getUserNickName(int userIdx) throws Exception;
    List<UserDTO> getOtherFollowingList(int userIdx, int otherIdx) throws Exception;
    List<UserDTO> getOtherFollowList(int userIdx, int otherIdx) throws Exception;
}
