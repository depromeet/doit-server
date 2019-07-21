insert into member (MID, KAKAO_ID, NAME, PROFILE_IMG_URL) values (1, 'kakaoid1', '지훈', 'image1')
insert into member (MID, KAKAO_ID, NAME, PROFILE_IMG_URL) values (2, 'kakaoid2', '규석', 'image2')

insert into progress_check_type (PCT_ID, DESCRIPTION) values (1, '주별 N회')
insert into progress_check_type (PCT_ID, DESCRIPTION) values (2, '요일별')

insert into goal (GID, GOAL_NAME, CATEGORY, START_DATE, END_DATE, THEME_COLOR, PENALTY, PROGRESS_CHECK_COUNT, TIMER_CHECK, PCT_ID) values (1, 'goal1', 'category1', '2019-10-22', '2019-10-29', '992211', 10000, 2, 1, 1)

insert into participant (PID, IS_HOST, GID, MID) values (1, 1, 1, 1);