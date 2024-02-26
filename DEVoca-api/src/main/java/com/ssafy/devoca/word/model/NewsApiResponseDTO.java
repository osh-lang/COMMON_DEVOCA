package com.ssafy.devoca.word.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewsApiResponseDTO {
    private String lastBuildDate;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("display")
    private Integer display;
    @JsonProperty("items")
    private List<NewsDTO> items;
}
