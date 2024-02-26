package com.ssafy.devoca.board.service;

import com.ssafy.devoca.board.model.BoardDTO;
import com.ssafy.devoca.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getBoardList(Integer boardType) throws Exception {
        return boardMapper.getBoardList(boardType);
    }

    @Override
    public void deleteBoard(Integer boardId, Integer userIdx) throws Exception {
        boardMapper.deleteBoard(boardId, userIdx);
    }

    @Override
    public void createBoard(BoardDTO boardDTO) throws Exception {
        boardMapper.createBoard(boardDTO);
    }

    @Override
    public BoardDTO getBoardDetail(Integer boardId) throws Exception {
        return boardMapper.getBoardDetail(boardId);
    }

    @Override
    public List<BoardDTO> getMyboard(Integer userIdx) throws Exception {
        return boardMapper.getMyboard(userIdx);
    }

    @Override
    public int getBoardUserIdx(int boardId) throws Exception {
        return boardMapper.getBoardUserIdx(boardId);
    }


}
