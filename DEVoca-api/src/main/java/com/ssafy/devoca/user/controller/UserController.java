package com.ssafy.devoca.user.controller;

import com.ssafy.devoca.mypage.service.MypageService;
import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.FavCategoryDTO;
import com.ssafy.devoca.user.model.UserDTO;
import com.ssafy.devoca.user.service.UserService;
import com.ssafy.devoca.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final MypageService mypageService;

    /*
     * 회원 가입 api
     * @author Ryu jiyun
     * @Github https://github.com/Ryujy
     * */
    @PostMapping("")
    public ResponseEntity<String> joinUser(@RequestBody UserDTO userDTO) {
        log.info("user 회원가입 호출");
        try{
            String accessToken = userService.joinUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(accessToken);
        } catch (Exception e) {
            log.error("회원가입 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 회원 정보 조회 api
     * @author Ryu jiyun
     * @Github https://github.com/Ryujy
     * */
    @GetMapping("")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("token") String token){
        log.info("회원 정보 조회 호출 : {}", token);
        try{
            int userIdx = userService.loadUserIdx(token);
            log.info("userIdx : {}", userIdx);
            UserDTO userInfo = userService.getUserInfo(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(userInfo);
        } catch (Exception e) {
            log.error("회원 정보 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 다른 회원 정보 조회 api
     * @author Ryu jiyun
     * @Github https://github.com/Ryujy
     * */
    @GetMapping("/{otherId}")
    public ResponseEntity<UserDTO> getOtherUserInfo(@RequestHeader("token") String token
                                                    ,@PathVariable("otherId") String otherId){
        log.info("다른 회원 정보 조회 호출 : {}", otherId);
        try{
            int userIdx = userService.loadUserIdx(token);
            int otherIdx = userService.loadUserIdxById(otherId);
            log.info("userIdx : {}", userIdx);
            UserDTO userInfo = userService.getOtherUserInfo(otherIdx, userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(userInfo);
        } catch (Exception e) {
            log.error("다른 회원 정보 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/follower/{otherId}")
    public ResponseEntity<List<UserDTO>> getOtherFollowList(@RequestHeader("token") String token
                                                        ,@PathVariable("otherId") String otherId){
        log.info("다른 회원의 팔로워 목록 조회 호출 : {}를 팔로우하는 사람들 : ", otherId);
        try{
            int userIdx = userService.loadUserIdx(token);
            int otherIdx = userService.loadUserIdxById(otherId);
            List<UserDTO> followList = userService.getOtherFollowList(userIdx, otherIdx);
            return ResponseEntity.status(HttpStatus.OK).body(followList);
        } catch (Exception e){
            log.error("다른 회원의 팔로워 목록 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/following/{otherId}")
    public ResponseEntity<List<UserDTO>> getOtherFollowingList(@RequestHeader("token") String token
                                                                ,@PathVariable("otherId") String otherId){
        log.info("다른 회원의 팔로잉 목록 조회 호출 : {}가 팔로우 하는 사람들 : ", otherId);
        try{
            int userIdx = userService.loadUserIdx(token);
            int otherIdx = userService.loadUserIdxById(otherId);
            List<UserDTO> followingList = userService.getOtherFollowingList(userIdx, otherIdx);
            return ResponseEntity.status(HttpStatus.OK).body(followingList);
        } catch (Exception e){
            log.error("다른 회원의 팔로잉 목록 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("")
    public ResponseEntity<UserDTO> updateUserInfo(@RequestHeader("token") String token,
                                                  @RequestBody UserDTO userDTO){
        log.info("user 정보 수정 호출 : {}", userDTO);
        try{
            int userIdx = userService.loadUserIdx(token);
            userDTO.setUserIdx(userIdx);
            userService.updateUserInfo(userDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("회원 정보 수정 실패: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* 관심 분야 설정
        - delete 후 insert
    */
    @PutMapping("/fav")
    public ResponseEntity<String> updateFavCategory(@RequestHeader("token") String token,
                                                 @RequestBody List<Integer> favList){
        log.info("user 관심 분야 설정 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            Map<String, Object> params = new HashMap<>();
            params.put("userIdx", userIdx);
            params.put("favList", favList);
            userService.updateFavCategory(params);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.error("user 관심 분야 설정 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/fav")
    public ResponseEntity<List<FavCategoryDTO>> getFavCategory(@RequestHeader("token") String token){
        log.info("회원 관심 분야 조회 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<FavCategoryDTO> favList = userService.getFavCategory(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(favList);
        } catch (Exception e){
            log.error("회원 관심 분야 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}