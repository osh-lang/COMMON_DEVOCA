package com.ssafy.devoca.comment.controller;

import com.ssafy.devoca.board.service.BoardService;
import com.ssafy.devoca.card.service.CardService;
import com.ssafy.devoca.comment.model.CommentDTO;
import com.ssafy.devoca.comment.service.CommentService;
import com.ssafy.devoca.notify.service.NotifyService;
import com.ssafy.devoca.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Comment 관련 Controller
 *
 * @author kimjuyi
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final NotifyService notifyService;
    private final CardService cardService;
    private final BoardService boardService;

    /**
     * Card나 Board의 상세 페이지에서 댓글 조회
     *
     * @param flag card or board
     * @param cardBoardId cardId or boardId
     * @return CommentList
     */
    @GetMapping("/{flag}/{cardBoardId}")
    public ResponseEntity<List<CommentDTO>> getCommentList(@PathVariable("flag") String flag, @PathVariable("cardBoardId") int cardBoardId) {
        log.info("getCommentList 호출 : {} {}", flag, cardBoardId);

        try {
            List<CommentDTO> commentList = commentService.getCommentList(flag, cardBoardId);
            return ResponseEntity.status(HttpStatus.OK).body(commentList);
        } catch (Exception e) {
            log.error("getCommentList 조회 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 댓글 작성
     *
     * @param map flag, cardBoardId, commentContent를 map으로 받아온다.
     * @return HttpStatus.CREATED
     */
    @PostMapping("")
    public ResponseEntity<String> registComment(@RequestHeader("token") String token
                                                ,@RequestBody Map<String, Object> map) {
        log.info("registComment 호출 : {}", map);

        try {
            int userIdx = userService.loadUserIdx(token);

            map.put("userIdx", userIdx);

            commentService.registComment(map);

            String flag = map.get("flag").toString();
            int cardBoardId = Integer.parseInt(map.get("cardBoardId").toString());

            if(flag.equals("card")) {
                int originUserIdx = cardService.getCardUserIdx(cardBoardId);
                notifyService.send(originUserIdx, 6, cardBoardId);
            } else {
                int originUserIdx = boardService.getBoardUserIdx(cardBoardId);
                notifyService.send(originUserIdx, 0, cardBoardId);
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("registComment 댓글 등록 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 댓글 삭제
     *
     * @param commentId 삭제할 댓글 아이디
     * @return HttpStatus.OK
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@RequestHeader("token") String token
                                                ,@PathVariable("commentId") int commentId) {
        log.info("deleteComment 호출 : {}", commentId);

        try {
            int userIdx = userService.loadUserIdx(token);

            int registerIdx = commentService.getUserIdxByCommentId(commentId);

            // 로그인 한 유저와 댓글 작성자가 동일인이 아니면 BAD_REQUEST
            if(userIdx != registerIdx) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            commentService.deleteComment(commentId);
            log.info("commentId 삭제 완료 : {}", commentId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("deleteComment 댓글 삭제 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 댓글 채택
     *
     * @param boardId 해당 글 아이디
     * @param commentId 댓글 아이디
     * @return
     */
    @PutMapping("/{boardId}/{commentId}")
    public ResponseEntity<String> pickComment(@RequestHeader("token") String token
                                                ,@PathVariable("boardId") int boardId, @PathVariable("commentId") int commentId) {
        log.info("pickComment 호출 : {}", commentId);

        try {
            int userIdx = userService.loadUserIdx(token);

            int boardUserIdx = commentService.getBoardUserIdx(boardId);
            int commentUserIdx = commentService.getUserIdxByCommentId(commentId);

            // 로그인 한 유저와 글 작성자가 동일인이 아니면 BAD_REQUEST
            // 댓글 작성자와 채택 하려는 유저가 동일인이면 BAD_REQUEST
            if(userIdx != boardUserIdx || userIdx == commentUserIdx) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            commentService.pickComment(commentId);
            log.info("commentId 채택 완료 : {}", commentId);

            notifyService.send(commentUserIdx, 1, boardId);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("pickComment 댓글 채택 실패 : {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
