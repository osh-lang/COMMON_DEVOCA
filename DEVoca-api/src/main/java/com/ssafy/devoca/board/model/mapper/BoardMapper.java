package com.ssafy.devoca.board.model.mapper;


import com.ssafy.devoca.board.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDTO> getBoardList(Integer boardType) throws SQLException;

    void deleteBoard(@Param("boardId") Integer boardId, @Param("userIdx") Integer userIdx) throws SQLException;

    void createBoard(BoardDTO boardDTO) throws SQLException;

    BoardDTO getBoardDetail(Integer boardId) throws SQLException;
    List<BoardDTO> getMyboard(Integer userIdx) throws SQLException;

    int getBoardUserIdx(int boardId) throws SQLException;
}