package com.ssafy.devoca.comment.service;

import com.ssafy.devoca.comment.model.CommentDTO;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public List<CommentDTO> getCommentList(String flag, int cardBoardId) throws Exception;
    public void registComment(Map<String, Object> map) throws Exception;
    public int getUserIdxByCommentId(int commentId) throws Exception;
    public void deleteComment(int commentId) throws Exception;
    public int getBoardUserIdx(int boardId) throws Exception;
    public void pickComment(int commentId) throws Exception;
    public List<CommentDTO> getMycommentList(int userIdx) throws Exception;
}
