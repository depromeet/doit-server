insert into MEMBER (MID, KAKAO_ID, NAME, PROFILE_IMG_URL) values (1, 'kakaoid1', '지훈', 'image1')
insert into MEMBER (MID, KAKAO_ID, NAME, PROFILE_IMG_URL) values (2, 'kakaoid2', '규석', 'image2')

insert into PROGRESS_CHECK_TYPE (PCT_ID, DESCRIPTION) values (1, '주별 N회')
insert into PROGRESS_CHECK_TYPE (PCT_ID, DESCRIPTION) values (2, '요일별')

insert into GOAL (GID, GOAL_NAME, CATEGORY, START_DATE, END_DATE, THEME_COLOR, PENALTY, PROGRESS_CHECK_COUNT, TIMER_CHECK, PCT_ID) values (1, 'goal1', 'category1', '2019-10-22', '2019-10-29', '992211', 10000, 2, 1, 1)

insert into PARTICIPANT (PID, IS_HOST, GID, MID) values (1, 1, 1, 1);

insert into SHOOT (SID, DATE, LIKE_COUNT, GID) values (1, '2019-10-23', 1, 1);

