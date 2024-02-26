package com.ssafy.devoca.quiz.service;

import com.ssafy.devoca.quiz.model.*;
import com.ssafy.devoca.quiz.model.mapper.QuizMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizMapper quizMapper;

    @Transactional
    @Override
    public int createQuiz() throws Exception {
        log.info("createQuizId 호출 : 퀴즈 아이디 새로 생성");
        quizMapper.createQuizId(0);

        log.info("getQuizId 호출 : 생성한 퀴즈 아이디 가져오기");
        int quizId = quizMapper.getQuizId(0);

        log.info("createQuizWordList 호출 : 퀴즈로 만들 10개 단어 가져오기");
        List<QuizDTO> quizList = quizMapper.createQuizWordList();
        log.info(Arrays.toString(quizList.toArray()));

        log.info("saveQuizWord 호출 : 퀴즈 아이디에 단어로 다시 저장");
        quizMapper.saveQuizWord(quizList, quizId);

        return quizId;
    }

    @Override
    public List<QuizDTO> getQuiz(int quizId) throws Exception {
        if(quizId == 0) {
            log.info("getQuizId 호출 : 생성한 퀴즈 아이디 가져오기");
            quizId = quizMapper.getQuizId(0);
        }
        log.info("getQuizWordList 호출 : 퀴즈 단어 가져오기");
        List<QuizDTO> quizList = quizMapper.getQuizWordList(quizId);
        log.info(Arrays.toString(quizList.toArray()));

        return quizList;
    }

    @Transactional
    @Override
    public void saveQuizResult(QuizResultDTO quizResultDTO) throws Exception{
        log.info("saveQuizResult 호출 : 퀴즈 결과 저장");
        quizMapper.saveQuizResult(quizResultDTO);
        log.info("saveQuizAnswerList 호출 : 퀴즈 답안 저장");
        quizMapper.saveQuizAnswerList(quizResultDTO.getUserIdx(), quizResultDTO.getQuizId(),
                quizResultDTO.getQuizAnswerDTOList());
    }

    @Override
    public int getQuizCnt(int loginUserIdx) throws Exception {
        return quizMapper.getQuizCnt(loginUserIdx);
    }

    @Override
    public List<QuizListDTO> getQuizResultList(int loginUserIdx) throws Exception {
        return quizMapper.getQuizResultList(loginUserIdx);
    }

    @Override
    public List<QuizResultDTO> getQuizResultDetail(int loginUserIdx, int quizId) throws Exception {
        return quizMapper.getQuizResultDetail(loginUserIdx, quizId);
    }

    @Transactional
    @Override
    public List<QuizVocaDTO> getBattleVocaList(int loginUserIdx, int oppoUserIdx) throws Exception {
        List<QuizVocaDTO> loginUserVocaList = quizMapper.getBattleVocaList(loginUserIdx);
        List<QuizVocaDTO> oppoUserVocaList = quizMapper.getBattleVocaList(oppoUserIdx);
        loginUserVocaList.addAll(oppoUserVocaList);
        return loginUserVocaList;
    }

    @Transactional
    @Override
    public int createBattle(BattleRequestDTO battleRequestDTO) throws Exception {
        log.info("createQuizId 호출 : 퀴즈 아이디 새로 생성");
        quizMapper.createQuizId(1);

        log.info("getQuizId 호출 : 생성한 퀴즈 아이디 가져오기");
        int quizId = quizMapper.getQuizId(1);
        battleRequestDTO.setQuizId(quizId);

        log.info("createBattleWordList 호출 : 퀴즈로 만들 단어 가져오기");
        List<QuizDTO> quizList = quizMapper.createBattleWordList
                (battleRequestDTO.getVocaListId(), battleRequestDTO.getWordCnt());
        log.info(Arrays.toString(quizList.toArray()));

        log.info("saveQuizWord 호출 : 퀴즈 아이디에 단어로 다시 저장");
        quizMapper.saveQuizWord(quizList, quizId);

        log.info("saveBattle 호출 : 대결 정보 저장");
        quizMapper.saveBattle(battleRequestDTO);

        return quizId;
    }

    @Transactional
    @Override
    public void saveBattleResult(QuizResultDTO quizResultDTO) throws Exception {
        log.info("saveQuizResult 호출 : 대결 퀴즈 결과 저장");
        quizMapper.saveQuizResult(quizResultDTO);
        log.info("saveQuizAnswerList 호출 : 대결 퀴즈 답안 저장");
        quizMapper.saveQuizAnswerList(quizResultDTO.getUserIdx(), quizResultDTO.getQuizId(), quizResultDTO.getQuizAnswerDTOList());
        log.info("saveBattleResult 호출 : 대결 정보 업데이트");
        quizMapper.saveBattleResult(quizResultDTO.getQuizId(), quizResultDTO.getUserIdx(), quizResultDTO.getScore());
    }

    @Override
    public List<BattleResultDTO> getBattleResultDetail(int loginUserIdx, int quizId) throws Exception {
        return quizMapper.getBattleResultDetail(loginUserIdx, quizId);
    }

    @Override
    public int getBattleUserIdx(int loginUserIdx, int quizId) throws Exception {
        return quizMapper.getBattleUserIdx(loginUserIdx, quizId);
    }


}
