package com.ssafy.devoca.quiz.model.mapper;

import com.ssafy.devoca.quiz.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface QuizMapper {
    public int createQuizId(int type) throws SQLException;

    public int getQuizId(int type) throws SQLException;

    public List<QuizDTO> createQuizWordList() throws SQLException;

    public void saveQuizWord(@Param("quizList") List<QuizDTO> quizList, @Param("quizId") int QuizId)
            throws SQLException;

    public List<QuizDTO> getQuizWordList(int quizId) throws SQLException;

    public void saveQuizResult(QuizResultDTO quizResultDTO) throws SQLException;

    public void saveQuizAnswerList(@Param("userIdx") int userIdx, @Param("quizId") int quizId,
                                   @Param("list") List<QuizAnswerDTO> quizAnswerDTOList)
            throws SQLException;

    public int getQuizCnt(int loginUserIdx) throws SQLException;

    public List<QuizListDTO> getQuizResultList(int loginUserIdx) throws SQLException;

    public List<QuizResultDTO> getQuizResultDetail(@Param("loginUserIdx") int loginUserIdx, @Param("quizId") int quizId)
            throws SQLException;
    public List<QuizVocaDTO> getBattleVocaList(int userIdx) throws SQLException;

    public List<QuizDTO> createBattleWordList(@Param("vocaListId") int vocaListId, @Param("wordCnt") int wordCnt)
        throws SQLException;

    public void saveBattle(BattleRequestDTO battleRequestDTO) throws SQLException;

    public void saveBattleResult(@Param("quizId") int quizId, @Param("userIdx") int userIdx, @Param("score") int score)
            throws SQLException;

    public List<BattleResultDTO> getBattleResultDetail(@Param("loginUserIdx") int loginUserIdx, @Param("quizId") int quizId)
        throws SQLException;

    public int getBattleUserIdx(@Param("loginUserIdx") int loginUserIdx, @Param("quizId") int quizId) throws SQLException;

}
