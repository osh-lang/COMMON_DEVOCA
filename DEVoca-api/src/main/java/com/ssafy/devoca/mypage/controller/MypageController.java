package com.ssafy.devoca.mypage.controller;

import com.ssafy.devoca.board.model.BoardDTO;
import com.ssafy.devoca.board.service.BoardService;
import com.ssafy.devoca.card.model.CardDTO;
import com.ssafy.devoca.card.service.CardService;
import com.ssafy.devoca.comment.model.CommentDTO;
import com.ssafy.devoca.comment.service.CommentService;
import com.ssafy.devoca.mypage.service.MypageService;
import com.ssafy.devoca.user.model.BadgeDTO;
import com.ssafy.devoca.user.model.UserDTO;
import com.ssafy.devoca.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Slf4j
public class MypageController {

    private final MypageService mypageService;
    private final UserService userService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final CardService cardService;

    @GetMapping("/badge")
    public ResponseEntity<List<BadgeDTO>> getUserBadges(@RequestHeader("token") String token) {
        log.info("회원의 배지 목록 조회 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<BadgeDTO> badgeList = mypageService.getUserBadges(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(badgeList);
        } catch (Exception e){
            log.error("회원의 배지 목록 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 팔로우 하기 api
     * @author Ryu jiyun
     * */
    @GetMapping("/follow/{followId}")
    public ResponseEntity<String> followUser(@RequestHeader("token") String token
                                            ,@PathVariable("followId") String followId){
        log.info("팔로우 api 호출 : {}", followId);
        try{
            int userIdx = userService.loadUserIdx(token);
            int followIdx = userService.loadUserIdxById(followId);
            log.info("userIdx : {}, followIdx : {}", userIdx, followIdx);
            mypageService.followUser(userIdx, followIdx);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.debug("팔로우 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 팔로우 취소 api
     * @author Ryu jiyun
     * */
    @DeleteMapping("/unfollow/{followId}")
    public ResponseEntity<String> unfollowUser(@RequestHeader("token") String token
                                                ,@PathVariable("followId") String followId){
        log.info("팔로우 취소 api 호출 : {}", followId);
        try{
            int userIdx = userService.loadUserIdx(token);
            int followIdx = userService.loadUserIdxById(followId);
            mypageService.unfollowUser(userIdx, followIdx);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.debug("언팔로우 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/follower")
    public ResponseEntity<List<UserDTO>> getFollowList(@RequestHeader("token") String token){
        log.info("회원의 팔로워 목록 조회 호출 : 나를 팔로우하는 사람들");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<UserDTO> followerList = mypageService.getFollowList(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(followerList);
        } catch (Exception e){
            log.error("회원의 팔로워 목록 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/following")
    public ResponseEntity<List<UserDTO>> getFollowingList(@RequestHeader("token") String token){
        log.info("회원의 팔로잉 목록 조회 호출 : 내가 팔로우 하는 사람들");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<UserDTO> followingList = mypageService.getFollowingList(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(followingList);
        } catch (Exception e){
            log.error("회원의 팔로잉 목록 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<UserDTO>> recommendFollow(@RequestHeader("token") String token){
        log.info("팔로우 추천 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<UserDTO> recommendList = mypageService.recommendFollow(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(recommendList);
        } catch(Exception e){
            log.error("팔로우 추천 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 나의 글 조회 api
     * @author Ryu jiyun
     * */
    @GetMapping("/myboard")
    public ResponseEntity<List<BoardDTO>> getMyboard(@RequestHeader("token") String token){
        log.info("마이페이지 나의 글 조회 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<BoardDTO> getMyboardList = boardService.getMyboard(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(getMyboardList);
        } catch (Exception e){
            log.info("마이페이지 나의 글 조회 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 나의 댓글 조회 api
     * @author Ryu jiyun
     * */
    @GetMapping("/mycomment")
    public ResponseEntity<List<CommentDTO>> getMycomment(@RequestHeader("token") String token){
        log.info("마이페이지 나의 댓글 조회 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<CommentDTO> getCommentList = commentService.getMycommentList(userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(getCommentList);
        } catch (Exception e){
            log.info("마이페이지 나의 댓글 조회 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 나의 피드 조회 api
     * @author Ryu jiyun
     * */
    @GetMapping("/mycard/{scroll}")
    public ResponseEntity<List<CardDTO>> getMyCard(@RequestHeader("token") String token
                                                    ,@PathVariable("scroll") int scroll){
        log.info("마이페이지 나의 피드 조회 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<CardDTO> getCommentList = cardService.getMyCard(userIdx, scroll);
            return ResponseEntity.status(HttpStatus.OK).body(getCommentList);
        } catch (Exception e){
            log.info("마이페이지 나의 피드 조회 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 프로필 사진 업로드 api
     * @author Ryu jiyun
     * */
    @PostMapping("/profile")
    public ResponseEntity<String> uploadProfileImg(@RequestHeader("token") String token
                                                ,@RequestParam("image") MultipartFile image){
        try{
            int userIdx = userService.loadUserIdx(token);
            log.info("사용자 프로필 사진 업로드 api 호출: {}, {}", userIdx ,image);
            String objectName = null;
            if (!image.isEmpty()) {
                objectName = mypageService.uploadProfileImg(image, userIdx);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(objectName);
        } catch (Exception e){
            log.info("프로필 이미지 업로드 실패: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
