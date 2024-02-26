package com.ssafy.devoca.vocalist.service;

import com.ssafy.devoca.vocalist.model.VldetailDTO;
import com.ssafy.devoca.vocalist.model.VocalistDTO;
import com.ssafy.devoca.vocalist.model.mapper.VocalistMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class VocalistServiceImpl implements VocalistService{

    private final VocalistMapper vocalistMapper;

    @Override
    @Transactional
    public void createVocalist(VocalistDTO vocalistDTO) throws Exception {
        vocalistMapper.createVocaList(vocalistDTO);
//        if (vocalistDTO.getCardId() != null){
//            storeVocalist(vocalistDTO);
//        }
    }

    @Override
    public List<VocalistDTO> getVocalist(Integer userIdx) throws Exception {
        return vocalistMapper.getVocalist(userIdx);
    }

    @Override
    public void storeVocalist(VocalistDTO vocalistDTO) throws Exception {
        vocalistMapper.storeVocalist(vocalistDTO);
    }

    @Override
    public void deleteVocalist(Map<String, Integer> params) throws Exception {
        vocalistMapper.deleteVocalist(params);
    }

    @Override
    public List<VldetailDTO> getVocalistDetail(Integer vocaListId, Integer userIdx) throws Exception {
        return vocalistMapper.getVocalistDetail(vocaListId, userIdx);
    }

    @Override
    public List<VocalistDTO> checkVocalist(Integer cardId, Integer userIdx) throws Exception {
        return vocalistMapper.checkVocalist(cardId, userIdx);
    }

    @Override
    public void cancelVocalist(Integer vlId, Integer cardId) throws Exception {
        vocalistMapper.cancelVocalist(vlId, cardId);
    }


}
