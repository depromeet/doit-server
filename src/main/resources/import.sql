insert into MEMBER (MID, KAKAO_ID, NAME, PROFILE_IMG_URL) values (1, 'kakaoid1', '지훈', 'image1')
insert into MEMBER (MID, KAKAO_ID, NAME, PROFILE_IMG_URL) values (2, 'kakaoid2', '규석', 'image2')

insert into PROGRESS_CHECK_TYPE (PCT_ID, DESCRIPTION) values (1, '주별 N회')
insert into PROGRESS_CHECK_TYPE (PCT_ID, DESCRIPTION) values (2, '요일별')
insert into PROGRESS_CHECK_TYPE (PCT_ID, DESCRIPTION) values (3, '매일')

insert into GOAL (GID, GOAL_NAME, CATEGORY, START_DATE, END_DATE, THEME_COLOR, PENALTY, PROGRESS_CHECK_COUNT, TIMER_CHECK, PCT_ID) values (1, '주 2회 하는 일', 'category1', '2019-7-11', '2019-7-30', '992211', 10000, 2, 1, 1)
insert into GOAL (GID, GOAL_NAME, CATEGORY, START_DATE, END_DATE, THEME_COLOR, PENALTY, PROGRESS_CHECK_COUNT, TIMER_CHECK, PCT_ID) values (2, '매일하는 일', 'category1', '2019-7-11', '2019-7-30', '992211', 10000, 0, 1, 3)
insert into GOAL (GID, GOAL_NAME, CATEGORY, START_DATE, END_DATE, THEME_COLOR, PENALTY, PROGRESS_CHECK_COUNT, TIMER_CHECK, PCT_ID) values (3, '수, 금, 일요일 하는 일', 'category1', '2019-7-11', '2019-7-30', '992211', 10000, 84, 1, 2)

insert into PARTICIPANT (PID, IS_HOST, GID, MID) values (1, 1, 1, 1)
insert into PARTICIPANT (PID, IS_HOST, GID, MID) values (2, 1, 2, 1)
insert into PARTICIPANT (PID, IS_HOST, GID, MID) values (3, 1, 3, 1)

insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (1, '2019-7-11', 1, 1, false, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (2, '2019-7-12', 1, 1, false, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (3, '2019-7-13', 1, 1, true, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (4, '2019-7-14', 1, 1, true, 1)

insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (5, '2019-7-11', 1, 2, false, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (6, '2019-7-12', 1, 2, false, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (7, '2019-7-13', 1, 2, false, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (8, '2019-7-13', 1, 2, true, 1)

insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (9, '2019-7-17', 1, 3, false, 1)
insert into SHOOT (SID, DATE, LIKE_COUNT, GID, IS_EXCEEDED, MID) values (9, '2019-7-18', 1, 3, true, 1)
