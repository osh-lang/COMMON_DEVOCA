-- 데이터 생성 sql문
INSERT INTO `DEVOCA`.`badges` 
    (`badge_id`, `badge_name`, `badge_info`, `badge_img_name`)
VALUES 
    ('1', '첫 답변 채택', '첫 Q&A 답변으로 채택받았을 때 받을 수 있는 뱃지', 'good'),
    ('2', '출석왕', '연속 접속 100일 이상', 'calendar'),
    ('3', '첫 걸음', 'DEVoca 첫 접속', 'first'),
    ('4', 'IT 트랜드 헌터', 'IT 뉴스 30회 이상 조회', 'news'),
    ('5', '단어 듣기 마스터', '단어장 재생 30회 이상', 'guitar'),
    ('6', '걸어다니는 DEVoca', '없는 단어 알리기 누적 50회', 'megaphone'),
    ('7', '첫 DM', '첫 DM 보낸 기념으로 받는 배지', 'live');

INSERT INTO `DEVOCA`.`users`
    (`user_id`, `user_name`, `user_birth`, `user_email`, `user_nickname`, `user_intro`, `user_exp`, `user_img`)
VALUES
    ('3332004002', 'æ-따거', '2024-02-16', 'æ-Devoca@gmail.com', 'æ-데보카', 'ææææ', '0', 'https://raw.githubusercontent.com/Ryujy/TIL_java/main/ae_devoca(1).png');
