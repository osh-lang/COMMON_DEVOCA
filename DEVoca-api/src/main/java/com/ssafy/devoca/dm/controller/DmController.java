package com.ssafy.devoca.dm.controller;

import com.ssafy.devoca.dm.model.DmDTO;
import com.ssafy.devoca.dm.model.DmRoomDTO;
import com.ssafy.devoca.dm.model.DmUserDTO;
import com.ssafy.devoca.dm.service.DmService;
import com.ssafy.devoca.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * DM 관련 Controller
 *
 * @author kimjuyi
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dm")
public class DmController {

    private final DmService dmService;
    private final UserService userService;

    /**
     * DM 목록 페이지 진입 시 로그인 한 유저의 DM 채팅방 목록 조회
     *
     * @return Dm 채팅방 정보 리스트
     *         (상대 유저 이미지, 상대 유저 닉네임, 마지막 메시지, 메시지 작성 시간, 안읽은 메시지 수)
     */
    @GetMapping("")
    public ResponseEntity<List<DmRoomDTO>> getDmRoomList(@RequestHeader("token") String token){
        log.info("getDmRoomList 호출");
        try {
            // 유저 idx 가져오기
            int loginUserIdx = userService.loadUserIdx(token);

            List<DmRoomDTO> dmRoomList = dmService.getDmRoomList(loginUserIdx);
            return ResponseEntity.status(HttpStatus.OK).body(dmRoomList);
        } catch (Exception e) {
            log.error("getDmRoomList 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * dm 상세 페이지에서 조회되는 채팅 상대 유저 정보
     *
     * @param token 로그인 유저 토큰
     * @param roomUuid 방 랜덤 아이디
     * @return 채팅 상대 유저 정보
     */
    @GetMapping("/{roomUuid}/user")
    public ResponseEntity<DmUserDTO> getDmUser (@RequestHeader("token") String token, @PathVariable("roomUuid") String roomUuid) {
        log.info("getDmUser 호출 : {} {}", token, roomUuid);

        try {
            int userIdx = userService.loadUserIdx(token);
            DmUserDTO dmUserDTO = dmService.getChatUser(roomUuid, userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(dmUserDTO);
        } catch (Exception e) {
            log.error("getDmUser 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 채팅방 진입 시 채팅 내역 조회
     * 무한스크롤(한 번에 10개의 메시지 데이터)
     *
     * @param roomUuid 채팅방 랜덤 아이디
     * @param scroll 스크롤 횟수
     * @return DmDTO의 리스트
     */
    @GetMapping("/{roomUuid}/{scroll}")
    public ResponseEntity<List<DmDTO>> getDmList(@RequestHeader("token") String token, @PathVariable("roomUuid") String roomUuid, @PathVariable("scroll") int scroll) {

        log.info("getDmList 호출 : {} {}", roomUuid, scroll);
        try {
            // 유저 idx 가져오기
            int loginUserIdx = userService.loadUserIdx(token);

            // 해당 유저가 채팅방 참여자가 아닐 경우 BAD_REQUEST 반환
            if(!dmService.getParticipantsYN(roomUuid, loginUserIdx)) {
                log.error("getDmList 조회 실패 : 접근 권한 없음.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            List<DmDTO> DmList = dmService.getDmList(roomUuid, scroll);
            return ResponseEntity.status(HttpStatus.OK).body(DmList);

        } catch (Exception e) {
            log.error("getDmList 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 타 유저의 피드에서 메시지 버튼 클릭 시
     * 채팅방이 존재하면 해당 방 랜덤 아이디 반환
     *        존재하지 않으면 채팅 방 생성, 참여자 저장 후 방 랜덤 아이디 반환
     *
     * @param chatUserId 채팅할 유저 아이디
     * @return
     */
    @GetMapping("/{chatUserId}")
    public ResponseEntity<String> getRoomUuid(@RequestHeader("token") String token, @PathVariable("chatUserId") String chatUserId) {
        log.info("getRoomUuid 호출 : {}", chatUserId);

        try {
            // 로그인 유저 idx 가져오기
            int loginUserIdx = userService.loadUserIdx(token);

            // 채팅 유저 idx 가져오기
            int chatUserIdx = userService.loadUserIdxById(chatUserId);

            String roomUuid = dmService.getRoomUuid(loginUserIdx, chatUserIdx);

            // 두 사용자가 참여한 채팅방이 있을 경우 해당 방 랜덤 아이디 반환
            if(roomUuid != null) {
                return ResponseEntity.status(HttpStatus.OK).body(roomUuid);
            }

            // 두 사용자가 참여한 채팅방이 없을 경우
            // 랜덤 방 아이디 생성
            UUID uuid = UUID.randomUUID();
            String newRoomUuid = uuid.toString().substring(0, 8);

            dmService.createRoom(newRoomUuid);
            log.info("방 생성 완료");

            // 참여자 테이블에 참여자 저장
            dmService.insertParticipants(newRoomUuid, loginUserIdx, chatUserIdx);
            log.info("참여자 저장 완료");

            return ResponseEntity.status(HttpStatus.OK).body(newRoomUuid);
        } catch (Exception e) {
            log.error("getRoomUuid 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}