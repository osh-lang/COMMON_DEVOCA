package com.ssafy.devoca.card.model.mapper;

import com.ssafy.devoca.card.model.CardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CardMapper {
    public void registerCard(CardDTO cardDTO) throws SQLException;

    public void updateCard(CardDTO cardDTO) throws SQLException;

    public void deleteCard(int cardId) throws SQLException;

    public CardDTO getCardDetail(@Param("cardId") int cardId, @Param("loginUserIdx") int loginUserIdx) throws SQLException;

    public List<CardDTO> getCardList(@Param("scroll") int scroll, @Param("loginUserIdx") int loginUserIdx)
            throws SQLException;

    public List<CardDTO> getCardListByWord
            (@Param("wordId") int wordId, @Param("scroll") int scroll, @Param("loginUserIdx") int loginUserIdx)
            throws SQLException;

    public List<CardDTO> getCardListByUserId
            (@Param("userId") String userId, @Param("scroll") int scroll, @Param("loginUserIdx") int loginUserIdx)
            throws SQLException;

    public void repostCard(CardDTO cardDTO) throws SQLException;

    public void repostCntUpdate(int originCardId) throws SQLException;

    public List<CardDTO> getMyCard
            (@Param("userIdx") int userIdx, @Param("scroll") int scroll) throws SQLException;

    public void deleteLike(@Param("loginUserIdx") int loginUserIdx, @Param("cardId") int cardId);

    public void addLike(@Param("loginUserIdx") int loginUserIdx, @Param("cardId") int cardId);

    public int getCardUserIdx(int cardId) throws SQLException;

}
