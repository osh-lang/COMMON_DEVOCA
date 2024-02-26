package com.ssafy.devoca.mypage.service;

import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.FavCategoryDTO;
import com.ssafy.devoca.user.model.UserDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface MypageService {

    List<BadgeDTO> getUserBadges(int userIdx) throws Exception;
    void followUser(int userIdx, int followIdx) throws Exception;
    void unfollowUser(int userIdx, int followIdx) throws Exception;
    List<UserDTO> getFollowList(int userIdx) throws Exception;
    List<UserDTO> getFollowingList(int userIdx) throws Exception;
    List<UserDTO> recommendFollow(int userIdx) throws Exception;
    String uploadProfileImg(MultipartFile image, int userIdx) throws Exception;
    String getImageUrl(String objectName, String bucket, int userIdx) throws Exception;
    String getObjectName(int userIdx) throws Exception;
}
