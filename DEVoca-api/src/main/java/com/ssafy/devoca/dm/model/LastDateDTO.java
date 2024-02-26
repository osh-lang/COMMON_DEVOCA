package com.ssafy.devoca.dm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class LastDateDTO {
    private int roomIdx;
    private String userId;
    private Timestamp lastDate;
}
