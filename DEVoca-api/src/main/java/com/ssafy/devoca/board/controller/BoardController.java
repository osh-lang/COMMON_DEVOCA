package com.ssafy.devoca.board.controller;

import com.ssafy.devoca.board.model.BoardDTO;
import com.ssafy.devoca.board.service.BoardService;
import com.ssafy.devoca.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    /*
     * 게시판 목록 api
     * @author Ryu jiyun
     * */
    @GetMapping("list/{boardType}")
    public ResponseEntity<List<BoardDTO>> getBoardList(@PathVariable("boardType") Integer boardType){
        log.info("게시판 목록 조회 api 호출 : {}", boardType);
        try{
            List<BoardDTO> boardList = boardService.getBoardList(boardType);
            return ResponseEntity.status(HttpStatus.OK).body(boardList);
        } catch(Exception e){
            log.debug("게시판 목록 조회 호출 실패: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 게시판 글 삭제 api
     * @author Ryu jiyun
     * */
    @PatchMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@RequestHeader("token") String token
                                                ,@PathVariable("boardId") Integer boardId){
        log.info("게시판 글 삭제 api 호출 : {}", boardId);
        try{
            int userIdx = userService.loadUserIdx(token);
            boardService.deleteBoard(boardId, userIdx);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.debug("게시판 글 삭제 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 게시판 글 작성 api
     * @author Ryu jiyun
     * */
    @PostMapping("")
    public ResponseEntity<String> createBoard(@RequestHeader("token") String token
                                        ,@RequestBody BoardDTO boardDTO){
        log.info("게시판 글 작성 api 호출 : {}, {}", token, boardDTO);
        try{
            int userIdx = userService.loadUserIdx(token);
            boardDTO.setUserIdx(userIdx);
            boardService.createBoard(boardDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            log.debug("게시판 글 작성 api 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
     * 게시판 상세 조회 api
     * @author Ryu jiyun
     * */
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDTO> getBoardDetail(@PathVariable("boardId") Integer boardId){
        log.info("게시판 상세 조회 호출 : {}",boardId);
        try{
            BoardDTO boardDTO = boardService.getBoardDetail(boardId);
            log.info("boardDTO : {}", boardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
        }catch (Exception e){
            log.info("게시판 상세 조회 호출 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
