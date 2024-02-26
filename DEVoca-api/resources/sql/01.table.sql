CREATE TABLE `users` (
    `user_idx`	int	NOT NULL	auto_increment,
    `user_id`	varchar(20)	NOT NULL unique,
    `user_name`	varchar(20)	NOT NULL,
    `user_birth`	char(10)	NOT NULL,
    `user_email`	varchar(40)	NOT NULL,
    `user_nickname`	varchar(20)	NOT NULL	COMMENT 'null 시 user_name으로',
    `user_intro`	varchar(100)	NULL,
    `user_exp`	int	NOT NULL	DEFAULT 0,
    `user_img`	varchar(2000)	NOT NULL	DEFAULT '/profile.png',
    `user_join_date`	datetime	NOT NULL	DEFAULT now(),
    `user_update_date`	datetime	NULL,
    `user_revoke_date`	datetime	NULL,
    `user_refresh_token`	varchar(300)	NULL,
    CONSTRAINT `PK_USERS` PRIMARY KEY (`user_idx`)
)default character set utf8mb4;

CREATE TABLE `badges` (
    `badge_id`	int	NOT NULL	auto_increment,
    `badge_name`	varchar(20)	NOT NULL,
    `badge_info`	varchar(30)	NOT NULL,
    `badge_img_name`	varchar(20) NULL,
    CONSTRAINT `PK_BADGES` PRIMARY KEY (`badge_id`)
)default character set utf8mb4;

CREATE TABLE `user_badges` (
    `ub_user_idx`	int	NOT NULL,
    `ub_badge_id`	int	NOT NULL,
    CONSTRAINT `PK_USER_BADGES` PRIMARY KEY (`ub_user_idx`,	`ub_badge_id`)
)default character set utf8mb4;

CREATE TABLE `follows` (
    `follow_from`	int	NOT NULL,
    `follow_to`	int	NOT NULL,
    `follow_date`	datetime	NOT NULL	DEFAULT now(),
    CONSTRAINT `PK_FOLLOWS` PRIMARY KEY (`follow_from`,	`follow_to`)
)default character set utf8mb4;

CREATE TABLE `words` (
    `word_id`	int	NOT NULL	auto_increment,
    `word_name_kr`	varchar(100)	NOT NULL,
    `word_name_en`	varchar(100)	NOT NULL,
    `word_def`	varchar(1000)	NOT NULL,
    `word_summ`	varchar(500)	NOT NULL,
    `word_link`	varchar(2000)	NULL,
    `word_searched_cnt`	int	NOT NULL	DEFAULT 0,
    CONSTRAINT `PK_WORDS` PRIMARY KEY (`word_id`)
)default character set utf8mb4;

CREATE TABLE `cards` (
    `card_id`	int	NOT NULL	auto_increment,
    `fk_card_user_idx`	int	NOT NULL,
    `card_repost_cnt`	int	NULL	DEFAULT 0,
    `card_word_id`	int	NULL,
    `card_origin_card_id`	int	NULL,
    `card_content`	varchar(300)	NULL,
    `card_link`	varchar(2000)	NULL,
    `card_regist_date`	datetime	NOT NULL	DEFAULT now(),
    `card_update_date`	datetime	NULL,
    `card_delete_date`	datetime	NULL,
    `card_related_keyword`	varchar(100)	NULL,
    CONSTRAINT `PK_CARDS` PRIMARY KEY (`card_id`)
)default character set utf8mb4;

CREATE TABLE `categories` (
    `category_id`	int	NOT NULL	auto_increment,
    `category_name`	varchar(20)	NOT NULL,
    CONSTRAINT `PK_CATEGORIES` PRIMARY KEY (`category_id`)
)default character set utf8mb4;

CREATE TABLE `likes` (
    `like_from`	int	NOT NULL,
    `like_to`	int	NOT NULL,
    `like_date`	datetime	NOT NULL	DEFAULT now(),
    CONSTRAINT `PK_LIKES` PRIMARY KEY (`like_from`, `like_to`)
)default character set utf8mb4;

CREATE TABLE `words_categories` (
    `wc_word_id`	int	NOT NULL,
    `wc_category_id`	int	NOT NULL,
    CONSTRAINT `PK_WORDS_CATEGORIES` PRIMARY KEY (`wc_word_id`,	`wc_category_id`)
)default character set utf8mb4;

CREATE TABLE `users_favorite_categories` (
    `uf_user_idx`	int	NOT NULL,
    `uf_category_id`	int	NOT NULL,
    CONSTRAINT `PK_USERS_FAVORITE_CATEGORIES` PRIMARY KEY (`uf_user_idx`,	`uf_category_id`)
)default character set utf8mb4;

CREATE TABLE `boards` (
    `board_id`	int	NOT NULL	auto_increment,
    `fk_board_user_idx`	int	NOT NULL,
    `board_title`	varchar(50)	NOT NULL,
    `board_content`	varchar(500)	NOT NULL,
    `board_type`	int	NOT NULL	COMMENT '0 : 일반, 1: Q&A, 2:건의',
    `board_regist_date`	datetime	NOT NULL default now(),
    `board_update_date`	datetime	NULL,
    `board_delete_date`	datetime	NULL,
    CONSTRAINT `PK_BOARDS` PRIMARY KEY (`board_id`)
)default character set utf8mb4;

CREATE TABLE `view_words` (
    `vw_word_id`	int	NOT NULL,
    `vw_user_idx`	int	NOT NULL,
    `vw_view_date`	datetime	NOT NULL	DEFAULT now(),
    CONSTRAINT `PK_VIEW_WORDS` PRIMARY KEY (`vw_word_id`,	`vw_user_idx`)
)default character set utf8mb4;

CREATE TABLE `dm_rooms` (
    `dr_idx`	int	NOT NULL	auto_increment,
    `dr_uuid`	varchar(45)	NOT NULL,
    `dr_create_date`	datetime	NOT NULL	DEFAULT now(),
    CONSTRAINT `PK_DM_ROOMS` PRIMARY KEY (`dr_idx`)
)default character set utf8mb4;

CREATE TABLE `quizzes` (
    `quiz_id`	int	NOT NULL	auto_increment,
    `quiz_create_date`	datetime	NOT NULL	DEFAULT now(),
    `quiz_type`	int	NOT NULL	COMMENT '0: quiz, 1: battle',
    CONSTRAINT `PK_QUIZZES` PRIMARY KEY (`quiz_id`)
)default character set utf8mb4;

CREATE TABLE `battles` (
    `battle_id`	int	NOT NULL	auto_increment,
    `fk_battle_from_user_idx`	int	NOT NULL,
    `fk_battle_to_user_idx`	int	NOT NULL,
    `fk_battle_sl_id`	int	NOT NULL,
    `battle_from_date`	datetime	NOT NULL default now(),
    `battle_to_date`	datetime	NULL,
    `battle_from_score`	int	NULL,
    `battle_to_score`	int	NULL,
    `battle_word_cnt`	int	NULL,
    `fk_battle_quiz_id`	int	NOT NULL,
    CONSTRAINT `PK_BATTLES` PRIMARY KEY (`battle_id`)
)default character set utf8mb4;

CREATE TABLE `searched_keywords` (
    `sk_user_idx`	int	NOT NULL,
    `sk_searched_keyword`	varchar(40)	NOT NULL,
    `sk_searched_date`	datetime	NOT NULL	DEFAULT now(),
    CONSTRAINT `PK_SEARCHED_KEYWORDS` PRIMARY KEY (`sk_user_idx`, `sk_searched_keyword`)
)default character set utf8mb4;

CREATE TABLE `voca_lists` (
    `vl_id`	int	NOT NULL	auto_increment,
    `fk_vl_user_idx`	int	NOT NULL,
    `vl_name`	varchar(20)	NOT NULL,
    CONSTRAINT `PK_VOCA_LISTS` PRIMARY KEY (`vl_id`)
)default character set utf8mb4;

CREATE TABLE `voca_list_cards` (
    `vlc_id`	int	NOT NULL	auto_increment,
    `vlc_sl_id`	int	NOT NULL,
    `vlc_wc_id`	int	NOT NULL,
    `vlc_wc_select`	int	NOT NULL	COMMENT '1 : 단어, 2 : 카드',
    `vlc_store_date`	datetime	NULL,
    CONSTRAINT `PK_VOCA_LIST_CARDS` PRIMARY KEY (`vlc_id`,	`vlc_sl_id`)
)default character set utf8mb4;

CREATE TABLE `comments` (
    `comment_id`	int	NOT NULL	auto_increment,
    `fk_comment_user_idx`	int	NOT NULL,
    `comment_board_id`	int	NULL,
    `comment_card_id`	int	NULL,
    `comment_content`	varchar(100)	NOT NULL,
    `comment_picked`	boolean	NOT NULL	DEFAULT false	COMMENT 'true : 채택',
    `comment_regist_date`	datetime	NOT NULL default now(),
    `comment_delete_date`	datetime	NULL,
    CONSTRAINT `PK_COMMENTS` PRIMARY KEY (`comment_id`)
)default character set utf8mb4;

CREATE TABLE `dm_participants` (
    `dp_dr_idx`	int	NOT NULL,
    `dp_user_idx`	int	NOT NULL,
    `dp_last_date`	datetime	NOT NULL	DEFAULT now(),
    CONSTRAINT `PK_DM_PARTICIPANTS` PRIMARY KEY (`dp_dr_idx`, `dp_user_idx`)
)default character set utf8mb4;

CREATE TABLE `dm_messages` (
    `dm_id`	int	NOT NULL	auto_increment,
    `fk_dm_dr_idx`	int	NOT NULL,
    `fk_dm_user_idx`	int	NOT NULL,
    `dm_content`	varchar(300)	NOT NULL,
    `dm_send_date`	datetime	NOT NULL	DEFAULT now(),
    `dm_battle_quiz_id`	int	NOT NULL	DEFAULT 0,
    CONSTRAINT `PK_DM_MESSAGES` PRIMARY KEY (`dm_id`)
)default character set utf8mb4;

CREATE TABLE `quiz_words` (
    `qw_id`	int	NOT NULL	auto_increment,
    `qw_word_id`	int	NOT NULL,
    `qw_quiz_id`	int	NOT NULL,
    CONSTRAINT `PK_QUIZ_WORDS` PRIMARY KEY (`qw_id`)
)default character set utf8mb4;

CREATE TABLE `quiz_participants` (
    `qp_quiz_id`	int	NOT NULL,
    `fk_qp_user_idx`	int	NOT NULL,
    `qp_participate_date`	datetime	NOT NULL	DEFAULT now(),
    `qp_score`	int	NOT NULL,
    CONSTRAINT `PK_QUIZ_PARTICIPANTS` PRIMARY KEY (`qp_quiz_id`, `fk_qp_user_idx`)
)default character set utf8mb4;

CREATE TABLE `quiz_answers` (
    `qw_id`	int	NOT NULL,
    `qa_user_idx`	int	NOT NULL,
    `qa_answer`	varchar(50)	NULL,
    `qa_yn`	int	NULL	COMMENT '0: 오답, 1: 정답',
    CONSTRAINT `PK_QUIZ_ANSWERS` PRIMARY KEY (`qw_id`,	`qa_user_idx`)
)default character set utf8mb4;

CREATE TABLE `share_imgs` (
    `sm_id`	int	NOT NULL	auto_increment,
    `fk_sm_user_idx`	int	NOT NULL,
    `sm_create_date`	datetime	NOT NULL	DEFAULT now(),
    `sm_img`	varchar(2000)	NOT NULL,
    CONSTRAINT `PK_SHARE_IMGS` PRIMARY KEY (`sm_id`)
)default character set utf8mb4;

CREATE TABLE `notifications` (
    `notifications_id`	varchar(30)	NOT NULL,
    `fk_notifications_user_idx`	int	NOT NULL,
    `notifications_type`	int	NOT NULL,
    `notifications_link_id`	int	NOT NULL,
    `notifications_date`	datetime	NOT NULL,
    `notifications_read_yn`	boolean	NOT NULL,
    CONSTRAINT `PK_NOTIFICATIONS` PRIMARY KEY (`notifications_id`)
)default character set utf8mb4;

ALTER TABLE `follows` ADD CONSTRAINT `FK_users_TO_follows_1` FOREIGN KEY (
    `follow_from`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `follows` ADD CONSTRAINT `FK_users_TO_follows_2` FOREIGN KEY (
    `follow_to`
)
REFERENCES `users` (
    `user_idx`
    );

ALTER TABLE `user_badges` ADD CONSTRAINT `FK_users_TO_user_badges_1` FOREIGN KEY (
    `ub_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `user_badges` ADD CONSTRAINT `FK_badges_TO_user_badges_1` FOREIGN KEY (
    `ub_badge_id`
)
REFERENCES `badges` (
    `badge_id`
);

ALTER TABLE `cards` ADD CONSTRAINT `FK_users_TO_cards_1` FOREIGN KEY (
    `fk_card_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `likes` ADD CONSTRAINT `FK_users_TO_likes_1` FOREIGN KEY (
    `like_from`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `likes` ADD CONSTRAINT `FK_cards_TO_likes_1` FOREIGN KEY (
    `like_to`
)
REFERENCES `cards` (
    `card_id`
);

ALTER TABLE `words_categories` ADD CONSTRAINT `FK_words_TO_words_categories_1` FOREIGN KEY (
    `wc_word_id`
)
REFERENCES `words` (
    `word_id`
);

ALTER TABLE `words_categories` ADD CONSTRAINT `FK_categories_TO_words_categories_1` FOREIGN KEY (
    `wc_category_id`
)
REFERENCES `categories` (
    `category_id`
);

ALTER TABLE `users_favorite_categories` ADD CONSTRAINT `FK_users_TO_users_favorite_categories_1` FOREIGN KEY (
    `uf_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `users_favorite_categories` ADD CONSTRAINT `FK_categories_TO_users_favorite_categories_1` FOREIGN KEY (
    `uf_category_id`
)
REFERENCES `categories` (
    `category_id`
);

ALTER TABLE `boards` ADD CONSTRAINT `FK_users_TO_boards_1` FOREIGN KEY (
    `fk_board_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `view_words` ADD CONSTRAINT `FK_words_TO_view_words_1` FOREIGN KEY (
    `vw_word_id`
)
REFERENCES `words` (
    `word_id`
);

ALTER TABLE `view_words` ADD CONSTRAINT `FK_users_TO_view_words_1` FOREIGN KEY (
    `vw_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `battles` ADD CONSTRAINT `FK_users_TO_battles_1` FOREIGN KEY (
    `fk_battle_from_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `battles` ADD CONSTRAINT `FK_users_TO_battles_2` FOREIGN KEY (
    `fk_battle_to_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `battles` ADD CONSTRAINT `FK_voca_lists_TO_battles_1` FOREIGN KEY (
    `fk_battle_sl_id`
)
REFERENCES `voca_lists` (
    `vl_id`
);

ALTER TABLE `battles` ADD CONSTRAINT `FK_quizzes_TO_battles_1` FOREIGN KEY (
    `fk_battle_quiz_id`
)
REFERENCES `quizzes` (
    `quiz_id`
);

ALTER TABLE `searched_keywords` ADD CONSTRAINT `FK_users_TO_searched_keywords_1` FOREIGN KEY (
    `sk_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `voca_lists` ADD CONSTRAINT `FK_users_TO_voca_lists_1` FOREIGN KEY (
    `fk_vl_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `voca_list_cards` ADD CONSTRAINT `FK_voca_lists_TO_voca_list_cards_1` FOREIGN KEY (
    `vlc_sl_id`
)
REFERENCES `voca_lists` (
    `vl_id`
);

ALTER TABLE `comments` ADD CONSTRAINT `FK_users_TO_comments_1` FOREIGN KEY (
    `fk_comment_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `dm_participants` ADD CONSTRAINT `FK_dm_rooms_TO_dm_participants_1` FOREIGN KEY (
    `dp_dr_idx`
)
REFERENCES `dm_rooms` (
    `dr_idx`
);

ALTER TABLE `dm_participants` ADD CONSTRAINT `FK_users_TO_dm_participants_1` FOREIGN KEY (
    `dp_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `dm_messages` ADD CONSTRAINT `FK_users_TO_dm_messages_1` FOREIGN KEY (
    `fk_dm_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `dm_messages` ADD CONSTRAINT `FK_dm_rooms_TO_dm_messages_1` FOREIGN KEY (
    `fk_dm_dr_idx`
)
REFERENCES `dm_rooms` (
    `dr_idx`
);

ALTER TABLE `quiz_words` ADD CONSTRAINT `FK_words_TO_quiz_words_1` FOREIGN KEY (
    `qw_word_id`
)
REFERENCES `words` (
    `word_id`
);

ALTER TABLE `quiz_words` ADD CONSTRAINT `FK_quizzes_TO_quiz_words_1` FOREIGN KEY (
    `qw_quiz_id`
)
REFERENCES `quizzes` (
    `quiz_id`
);

ALTER TABLE `quiz_participants` ADD CONSTRAINT `FK_quizzes_TO_quiz_participants_1` FOREIGN KEY (
    `qp_quiz_id`
)
REFERENCES `quizzes` (
    `quiz_id`
);

ALTER TABLE `quiz_participants` ADD CONSTRAINT `FK_users_TO_quiz_participants_1` FOREIGN KEY (
    `fk_qp_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `quiz_answers` ADD CONSTRAINT `FK_quiz_words_TO_quiz_answers_1` FOREIGN KEY (
    `qw_id`
)
REFERENCES `quiz_words` (
    `qw_id`
);

ALTER TABLE `quiz_answers` ADD CONSTRAINT `FK_users_TO_quiz_answers_1` FOREIGN KEY (
    `qa_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `share_imgs` ADD CONSTRAINT `FK_users_TO_share_imgs_1` FOREIGN KEY (
    `fk_sm_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE `notifications` ADD CONSTRAINT `FK_users_TO_Notifications_1` FOREIGN KEY (
    `fk_notifications_user_idx`
)
REFERENCES `users` (
    `user_idx`
);

ALTER TABLE voca_list_cards DROP FOREIGN KEY FK_voca_lists_TO_voca_list_cards_1;
ALTER TABLE voca_list_cards ADD CONSTRAINT FK_voca_lists_TO_voca_list_cards_1 FOREIGN KEY (vlc_sl_id) REFERENCES voca_lists (vl_id) ON DELETE CASCADE;