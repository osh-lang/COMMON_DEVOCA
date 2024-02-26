package com.ssafy.devoca.vocalist.controller;

import com.ssafy.devoca.user.service.UserService;
import com.ssafy.devoca.vocalist.model.VldetailDTO;
import com.ssafy.devoca.vocalist.model.VocalistDTO;
import com.ssafy.devoca.vocalist.service.VocalistService;
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
@RequestMapping("/vocalist")
@Slf4j
public class VocalistController {

    private final UserService userService;
    private final VocalistService vocalistService;

    /*
     * 단어장 생성 api
     * 단어장 생성 및 단어장에 카드 저장
     * @author Ryu jiyun
     * */
    @PostMapping("")
    public ResponseEntity<String> createVocalist(@RequestHeader("token") String token
                                                 , @RequestBody VocalistDTO vocalistDTO){
        log.info("단어장 생성 api 호출 : {} ", vocalistDTO);
        try{
            int userIdx = userService.loadUserIdx(token);
            vocalistDTO.setUserIdx(userIdx);
            vocalistDTO.setVlWordCard(1);
            vocalistService.createVocalist(vocalistDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.info("단어장 생성 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    * 단어장에 의견카드 저장 api
    * @author Ryu jiyun
    * */
    @PostMapping("/store")
    public ResponseEntity<String> storeVocalist(@RequestHeader("token") String token
                                                , @RequestBody VocalistDTO vocalistDTO){
        log.info("단어장 저장 api 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            vocalistDTO.setUserIdx(userIdx);
            vocalistDTO.setVlWordCard(2);
            vocalistService.storeVocalist(vocalistDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.info("단어장 저장 api 호출 실패: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 마이페이지 > 단어장 목록 api
     * @author Ryu jiyun
     * */
    @GetMapping("")
    public ResponseEntity<List<VocalistDTO>> getVocalist(@RequestHeader("token") String token){
        log.info("단어장 목록 api 호출");
        try{
            int userIdx = userService.loadUserIdx(token);
            List<VocalistDTO> vocaList = vocalistService.getVocalist(userIdx);
            log.info("vocaList : {}", vocaList);
            return ResponseEntity.status(HttpStatus.OK).body(vocaList);
        } catch (Exception e){
            log.info("단어장 목록 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 단어장 삭제 api
     * @author Ryu jiyun
     * */
    @DeleteMapping("list/{vocaListId}")
    public ResponseEntity<String> deleteVocalist(@RequestHeader("token") String token
                                                , @PathVariable("vocaListId") Integer vlId){
        log.info("단어장 삭제 api 호출 : {}", vlId);
        try{
            int userIdx = userService.loadUserIdx(token);
            Map<String, Integer> params = new HashMap<>();
            params.put("vlId", vlId);
            params.put("userIdx", userIdx);
            vocalistService.deleteVocalist(params);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.info("단어장 삭제 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 단어장 상세 목록 조회 api
     * @author Ryu jiyun
     * */
    @GetMapping("detail/{vocalistId}")
    public ResponseEntity<List<VldetailDTO>> getVocalistDetail(@RequestHeader("token") String token
                                                                ,@PathVariable("vocalistId") Integer vocaListId){
        log.info("단어장 상세 목록 조회 api 호출 : {}", vocaListId);
        try{
            int userIdx = userService.loadUserIdx(token);
            List<VldetailDTO> vldetailDTOList = vocalistService.getVocalistDetail(vocaListId, userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(vldetailDTOList);
        } catch (Exception e){
            log.info("단어장 상세 목록 조회 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /*
     * 메인 피드 > 단어장 목록 api
     * 해당 의견 카드 id로 단어장 목록 호출
     * @author Ryu jiyun
     * */
    @GetMapping("/{cardId}")
    public ResponseEntity<List<VocalistDTO>>checkVocalist(@RequestHeader("token") String token
                                                            ,@PathVariable("cardId") Integer cardId){
        log.info("특정 카드의 단어장 저장 여부를 포함한 목록 api 호출 : {}", cardId);
        try{
            int userIdx = userService.loadUserIdx(token);
            List<VocalistDTO> vocalist = vocalistService.checkVocalist(cardId, userIdx);
            return ResponseEntity.status(HttpStatus.OK).body(vocalist);
        } catch (Exception e){
            log.info("특정 카드의 단어장 저장 여부를 포함한 목록 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 의견카드 단어장 저장 취소 api
     * @author Ryu jiyun
     * */
    @DeleteMapping("/{vocaListId}/{cardId}")
    public ResponseEntity<String> cancelVocalist(@RequestHeader("token") String token
                                                , @PathVariable("vocaListId") Integer vlId, @PathVariable("cardId") Integer cardId){
        log.info("의견 카드 단어장 저장 취소 api 호출 : {}, {}", vlId, cardId);
        try{
            int userIdx = userService.loadUserIdx(token);
            vocalistService.cancelVocalist(vlId, cardId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.info("의견 카드 단어장 저장 취소 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
