package com.ssafy.devoca.word.model;

import com.ssafy.devoca.card.model.CardDTO;
import lombok.Data;

import java.util.List;

@Data
public class WordDetailDTO {
    private WordDTO wordDTO;
    private List<NewsDTO> newsList;
}
