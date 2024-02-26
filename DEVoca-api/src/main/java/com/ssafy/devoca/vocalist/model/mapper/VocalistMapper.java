package com.ssafy.devoca.vocalist.model.mapper;

import com.ssafy.devoca.vocalist.model.VldetailDTO;
import com.ssafy.devoca.vocalist.model.VocalistDTO;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface VocalistMapper {

    void createVocaList(VocalistDTO vocalistDTO) throws SQLException;
    List<VocalistDTO> getVocalist(Integer userIdx) throws SQLException;
    void storeVocalist(VocalistDTO vocalistDTO) throws SQLException;
    void deleteVocalist(Map<String, Integer> params) throws SQLException;
    List<VldetailDTO> getVocalistDetail(@Param("vocaListId") Integer vocaListId, @Param("userIdx") Integer userIdx) throws SQLException;
    List<VocalistDTO> checkVocalist(@Param("cardId") Integer cardId, @Param("userIdx") Integer userIdx) throws SQLException;
    void cancelVocalist(@Param("vlId") Integer vlId, @Param("cardId") Integer cardId) throws SQLException;
}