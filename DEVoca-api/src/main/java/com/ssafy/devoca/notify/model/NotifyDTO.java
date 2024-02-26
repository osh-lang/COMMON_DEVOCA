package com.ssafy.devoca.notify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class NotifyDTO {

    String notificationId;
    // 알림을 받는 유저의 Idx
    int userIdx;
    // 타입 0:글 댓글, 1: 댓글 채택, 2: 카드 좋아요, 3: 카드 재게시, 4: DM, 5: 퀴즈
    int notificationType;
    // 타입 0,1: 원본 게시글 Id, 2: 해당 카드 Id, 3: 재게시로 생성된 카드 Id, 4: 해당 DR_IDX, 5: 해당 퀴즈 Id
    int notificationLinkId;
    // 알림 생성 일시
    Timestamp notificationDate;
    // 해당 유저가 읽었는지 여부
    Boolean isRead;

    @Builder
    public NotifyDTO(int userIdx, int notificationType, int notificationLinkId, Timestamp notificationDate, boolean isRead){
        this.notificationId = userIdx + "_" + getNotificationDate();
        this.userIdx = userIdx;
        this.notificationType = notificationType;
        this.notificationLinkId = notificationLinkId;
        this.notificationDate = notificationDate;
        this.isRead = isRead;
    }
}
