package com.ssafy.devoca.card.service;

import com.ssafy.devoca.card.model.CardDTO;
import com.ssafy.devoca.card.model.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardMapper cardMapper;

    @Override
    public void registerCard(CardDTO cardDTO) throws Exception {
        // String[] cardRelatedKeywordArray를 String cardRelatedKeyword로
        String[] tempStringArray = cardDTO.getCardRelatedKeywordList();
        if(tempStringArray != null) {
            String tempString = Arrays.toString(tempStringArray);
            cardDTO.setCardRelatedKeyword(tempString);
        }
        cardMapper.registerCard(cardDTO);
    }

    @Override
    public void updateCard(CardDTO cardDTO) throws Exception {
        // String[] cardRelatedKeywordArray를 String cardRelatedKeyword로
        String[] tempStringArray = cardDTO.getCardRelatedKeywordList();
        if(tempStringArray != null) {
            String tempString = Arrays.toString(tempStringArray);
            cardDTO.setCardRelatedKeyword(tempString);
        }
        cardMapper.updateCard(cardDTO);
    }

    @Override
    public void deleteCard(int cardId) throws Exception {
        cardMapper.deleteCard(cardId);
    }

    @Override
    public CardDTO getCardDetail(int cardId, int loginUserIdx) throws Exception{
        CardDTO cardDTO = cardMapper.getCardDetail(cardId, loginUserIdx);

        // String cardRelatedKeyword를 String[] cardRelatedKeywordArray로
        String tempString = cardDTO.getCardRelatedKeyword();
        if(tempString != null) {
            tempString = tempString.substring(1);
            String[] tempStringArray = tempString.split(", ");

            String temp = tempStringArray[tempStringArray.length - 1];
            temp = temp.substring(0, temp.length() - 1);
            tempStringArray[tempStringArray.length - 1] = temp;

            cardDTO.setCardRelatedKeywordList(tempStringArray);
        }
        return cardDTO;
    }

    @Override
    public List<CardDTO> getCardList(int scroll, int loginUserIdx) throws Exception {
        return cardMapper.getCardList(scroll * 10, loginUserIdx);
    }

    @Override
    public List<CardDTO> getCardListByWord(int wordId, int scroll, int loginUserIdx) throws Exception {
        return cardMapper.getCardListByWord(wordId, scroll * 10, loginUserIdx);
    }

    @Override
    public List<CardDTO> getCardListByUserId(String userId, int scroll, int loginUserIdx) throws Exception {
        return cardMapper.getCardListByUserId(userId, scroll * 10, loginUserIdx);
    }

    @Override
    public void repostCard(CardDTO cardDTO) throws Exception {
        // String[] cardRelatedKeywordArray를 String cardRelatedKeyword로
        String[] tempStringArray = cardDTO.getCardRelatedKeywordList();
        if(tempStringArray != null) {
            String tempString = Arrays.toString(tempStringArray);
            cardDTO.setCardRelatedKeyword(tempString);
        }
        cardMapper.repostCard(cardDTO);
        cardMapper.repostCntUpdate(cardDTO.getOriginCardId());
    }

    @Override
    public List<CardDTO> getMyCard(int userIdx, int scroll) throws Exception {
        return cardMapper.getMyCard(userIdx, scroll);
    }

    @Override
    public void likeCard(int loginUserIdx, int cardId, boolean cardLikeYn) throws Exception {
        if(cardLikeYn) {
            // 좋아요 되어있다면 기존 테이블에서 삭제
            cardMapper.deleteLike(loginUserIdx, cardId);
        }else{
            // 안되어있다면 테이블 추가
            cardMapper.addLike(loginUserIdx, cardId);
        }
    }

    @Override
    public int getCardUserIdx(int cardId) throws Exception {
        return cardMapper.getCardUserIdx(cardId);
    }


}
