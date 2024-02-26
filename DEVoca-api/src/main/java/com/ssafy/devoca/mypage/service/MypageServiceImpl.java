package com.ssafy.devoca.mypage.service;

import com.ssafy.devoca.mypage.model.mapper.MypageMapper;
import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MypageServiceImpl implements MypageService {

    private final MypageMapper mypageMapper;

    @Override
    public List<BadgeDTO> getUserBadges(int userIdx) throws Exception {
        return mypageMapper.getUserBadges(userIdx);
    }

    @Override
    public void followUser(int userIdx, int followIdx) throws Exception {
        mypageMapper.followUser(userIdx, followIdx);
    }

    @Override
    public void unfollowUser(int userIdx, int followIdx) throws Exception {
        mypageMapper.unfollowUser(userIdx, followIdx);
    }

    @Override
    public List<UserDTO> getFollowList(int userIdx) throws Exception {
        return mypageMapper.getFollowList(userIdx);
    }

    @Override
    public List<UserDTO> getFollowingList(int userIdx) throws Exception {
        return mypageMapper.getFollowingList(userIdx);
    }

    @Override
    public List<UserDTO> recommendFollow(int userIdx) throws Exception {
        return mypageMapper.recommendFollow(userIdx);
    }

    @Override
    @Transactional
    public String uploadProfileImg(MultipartFile image, int userIdx) throws Exception {

        // 파일 정보
        String originFileName = image.getOriginalFilename();
        String extName
                = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
        Long size = image.getSize();
        String type = image.getContentType();
        log.info("type : {}" , type);

        // 저장할 이름
        String saveFileName = getSaveFileName(extName, userIdx);
        InputStream stream = image.getInputStream();

        String bucketName = "devoca";
        return getImageUrl(saveFileName, bucketName, userIdx);
    }

    @Override
    public String getImageUrl(String objectName, String bucket, int userIdx) throws Exception {
        return "";
    }

    private void saveImageUrl(String url, int userIdx) {
        mypageMapper.saveImageUrl(url, userIdx);
    }


    private String getSaveFileName(String extName, int userIdx) {
        String filename = "";

        Calendar calendar = Calendar.getInstance();
        filename += userIdx;
        filename += calendar.get(Calendar.YEAR);
        filename += calendar.get(Calendar.MONTH);
        filename += calendar.get(Calendar.DATE);
        filename += calendar.get(Calendar.HOUR);
        filename += calendar.get(Calendar.MINUTE);
        filename += calendar.get(Calendar.SECOND);
        filename += calendar.get(Calendar.MILLISECOND);
        filename += extName;

        return filename;
    }

    @Override
    public String getObjectName(int userIdx) throws Exception {
        return mypageMapper.getObjectName(userIdx);
    }
}
