--admin user insert
insert into `users` (`id`, `username`, `email`, `tel`, `nickname`, `sns_id`, `sns_type`, `sns_profile`, `admin_flag`, `create_date`, `modify_date`) values(null, 'admin', null, null, null, null, null, null, 1, null, null);

--카테고리 insert
insert into category (name) values('전시');
insert into category (name) values('뮤지컬');
insert into category (name) values('콘서트');
insert into category (name) values('클래식');
insert into category (name) values('연극');
insert into category (name) values('클래스');
insert into category (name) values('체험');
insert into category (name) values('키즈');

--전시 product insert
insert into `product` (`id`, `category_id`, `name`, `description`, `sales_start`, `sales_end`, `sales_flag`, `event`, `create_date`, `modify_date`) values(null, 1, '카림 라시드전', '', '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0', 1, '네이버 예매자만의 행복!<br>
꽝없는 뽑기이벤트!', '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');
insert into `product` (`id`, `category_id`, `name`, `description`, `sales_start`, `sales_end`, `sales_flag`, `event`, `create_date`, `modify_date`) values(null, 1, '픽사 애니메이션 30주년 특별전', '* 얼리버드 티켓 이용기간 후 취소, 환불 x <br>
* 당일구매 당일사용 x(다음날 사용 가능)', '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0', 1, '관람시간 안내<br>
평일, 주말, 공휴일 : 오전 10시부터 오후 9시까지<br>
*매표 및 입장은 오후 8시에 마감됩니다.<br>', '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');

insert into `product_detail` (`id`, `product_id`, `content`, `create_date`, `modify_date`) values(null,'1','세계 3대 디자이너, 카림 라시드展 - Design Your Self<br>
2017.06.30(금) - 10.07(토) / 예술의전당 한가람미술관<br>
홈페이지: www.karimrashid2017.modoo.at<br>
<br>
화려한 색감과 독특한 미래지향 디자인으로 세상을 바꾸는 디자이너 카림 라시드!<br>
<br>
미국, 독일, 이탈리아, 크로아티아, 브라질 등 세계 유수 미술관과 갤러리에서 카림 라시드의 전시가 열린 적이 있지만, 아시아에서 대규모 전시로는 처음입니다. <br>
<br>
이번 전시는 그동안 ‘알레산드로 멘디니전’, ‘앤서니 브라운의 행복한 미술관전’을 기획한 아트센터이다가 뉴욕 소재 카림 라시드 스튜디오와 공동으로 한국 관람객을 위해 특별히 기획했습니다. <br>
<br>
카림 라시드가 직접 디자인한 전시장에는 뉴욕 스튜디오에서 소장하고 있는 디자인 스케치 원본과 국내 최초로 공개되는 조형물, 가구, 오브제, 미디어 작품까지 350여 점의 작품이 전시될 예정이다. 수려한 곡선을 뽐내는 유기적이고 독창적인 작품들을 만나며 카림 라시드의 디자인 세계를 들여다보는 시간이 될 것입니다.<br>
<br>
Photo by George Whiteside<br>','2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');

insert into `product_detail` (`id`, `product_id`, `content`, `create_date`, `modify_date`) values(null,'2','도슨트 안내 (관람객 폭주시 도슨트는 취소 될 수 있습니다.)<br>
평일 오전 12시, 오후 3시, 5시, 7시<br>
* 주말 및 공휴일은 도슨트를운영하지 않습니다.<br>
* 당일구매 당일사용 불가(다음날부터 사용 가능)<br>','2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');

insert into `display_info` (`id`, `product_id`, `observation_time`, `display_start`, `display_end`, `place_name`, `place_lot`, `place_street`, `tel`, `homepage`, `email`, `create_date`, `modify_date`) values(null, 1, '비고', '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0', '예술의전당', '서울특별시 서초구 남부순환로 2406 예술의전당', '서울특별시 중구 을지로7가 2-1 동대문디자인플라자M배움터', '02-3143-4360', 'http://www.karimrash...', null, '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');
insert into `display_info` (`id`, `product_id`, `observation_time`, `display_start`, `display_end`, `place_name`, `place_lot`, `place_street`, `tel`, `homepage`, `email`, `create_date`, `modify_date`) values(null, 2, '비고', '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0', 'DDP', '서울특별시 중구 을지로7가 2-1 동대문디자인플라자M배움터', '서울특별시 중구 을지로 281 동대문디자인플라자M배움터', '02-325-1077', null, null, '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');

insert into `file` (`id`, `user_id`, `file_name`, `save_file_name`, `file_length`, `content_type`, `delete_flag`, `create_date`, `modify_date`) values(null, 1, '카림 라시드전', 'http://naverbooking.phinf.naver.net/20170704_132/1499148450612FVOIS_JPEG/1242x1242px.jpg?type=ff1242_1242', 0, 'jpg', 0, '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');
insert into `file` (`id`, `user_id`, `file_name`, `save_file_name`, `file_length`, `content_type`, `delete_flag`, `create_date`, `modify_date`) values(null, 1, '픽사 특별전', 'http://naverbooking.phinf.naver.net/20170319_199/148984980736207XEa_JPEG/%B3%D7%C0%CC%B9%F6_%BF%B9%BE%E0_%B4%EB%C7%A5_%BB%E7%C0%CC%C1%EE-01.jpg?type=ff1242_1242', 0, 'jpg', 0, '2017-07-10 11:00:00.0', '2017-07-10 11:00:00.0');


insert into `product_image` (`id`, `product_id`, `file_id`, `type`) values(null, 1, 1, 1);
insert into `product_image` (`id`, `product_id`, `file_id`, `type`) values(null, 2, 2, 1);



--뮤지컬======================================================================================

insert into product(category_id, name, sales_start, sales_flag, create_date)
values (3, '뮤지컬 브로드웨이 42번가',  '2017/07/11',  1, sysdate());

insert into product_detail(product_id, content, create_date)
values (3, '국내 초연 21주년, 브로드웨이 스테디셀러 뮤지컬<br/>
브로드웨이 5,000회 이상 정기공연 기록!<br/> 
1996년 국내 초연 이후 21년 동안 써내려간 흥행불패의 신화<br/>
2016년 20주년 공연의 성공적인 피날레에 힘입어 <br/>
격이 다른 뮤지컬의 진화판 ‘21년산’으로 되돌아오다.<br/>
<br/>
경쾌하고 짜릿한 쇼 뮤지컬의 진수, 더욱 완벽해진 무대로 찾아오다! <br/>
20주년 기념공연에서 새롭게 선보인 장면들의 안무, 세트, 조명을 보완해 <br/>
압도적인 입체감과 역대 최고 레벨의 탭댄스를 선보인다. <br/>
올 여름 누구나 즐길 수 있는 짜릿한 퍼포먼스와 차원이 다른 고품격 무대가 펼쳐진다. <br/>

BEST CASTING MATCH, 믿고 보는 배우들의 환상적인 조합<br/>
카리스마 넘치는 ‘외강내유’ 형 줄리안 마쉬, 김석훈!<br/>
시크하면서도 반전 있는 ‘외유내강’형 줄리안마쉬, 이종혁!<br/>
21년 만에 뭉친 초연콤비, 최정원, 전수경!<br/>
새롭게 합류한 NEW CAST, 배해선, 오소연!<br/>
환상호흡의 최정예 멤버, 전예지, 에녹, 전재홍! <br/>
새롭고 품격 있는 캐스트로 차원이 다른 21주년 무대를 선보인다.', sysdate());


insert into file(user_id, file_name, save_file_name, file_length, content_type, delete_flag, create_date)
values (1, '뮤지컬브로드웨어42번가대표이미지', 'http://naverbooking.phinf.naver.net/20170627_70/1498539663641rFmXS_JPEG/42%B9%F8%B0%A12017-%BC%BC%B7%CE%B9%F6%C0%FC-%C3%D6%C1%BE-1242.jpg', 0, 'jpg', 0, sysdate());

insert into `product_image` (`id`, `product_id`, `file_id`, `type`) values(null, 3, 3, 1);

insert into display_info(product_id, observation_time, display_start, display_end, place_name, place_street, tel, create_date)
values(3, 'observation time', sysdate(), sysdate()+5, '디큐브아트센터', '서울특별시 구로구 경인로 662 디큐브시티', '010-2542-9972', sysdate());
--뮤지컬2
insert into product(category_id, name, sales_start, sales_flag, create_date, description, event)
values (2, '뮤지컬-김종욱찾기',  sysdate(),  1, sysdate(), '대학로 로맨틱 코미디 NO.1 뮤지컬 김종욱 찾기', '자는 동안 새로 태어나는 보습 필링 크림<br/>  "브라이트닝 필링 크림(by.닥터지)"을 선물합니다.');

insert into product_detail(product_id, content, create_date)
values (4, '<공연 정보><br/>
- 공연명 : 뮤지컬-김종욱 찾기<br/>
- 공연 기간 : OPEN RUN<br/>
- 장소 : 대학로 쁘띠첼씨어터<br/>
<br>
<가격><br/>
- 평일(월~금) : 15,800원<br/>
- 주말(토~일) : 19,800원<br/>
- 일요일 7시30분 : 12,800원<br/>
- 문화의 날 : 12,800원 (매월 마지막 주 수요일)<br/>
<br>
<공연시간><br/>
- 평일 (월~화) 2시, 5시, 8시<br/>
- 주말 (토~일) 1시, 3시10분, 5시20분, 7시30분<br/>
<br>
<관람안내><br/>
- 관람등급 : 만 7세 이상 관람가<br/>
- 러닝타임 : 100분<br/>
- 주차안내 : 주차불가 / 대중교통 이용 권장<br/>
- 공연문의 : 02-766-7667<br/>
- 제작 : (주)네오, CJ E&M', sysdate());


insert into file(user_id, file_name, save_file_name, file_length, content_type, delete_flag, create_date)
values (1, '뮤지컬김종욱찾기대표이미지', 'https://ssl.phinf.net/naverbooking/20170116_141/1484533726176puxYl_JPEG/%B3%D7%C0%CC%B9%F6_%B1%E8%C1%BE%BF%ED2.jpg', 0, 'jpg', 0, sysdate());

-- type : 대표이미지 - 1 , 부가이미지 - 2
insert into product_image(product_id, file_id, type) values (4, 4, 1);

insert into display_info(product_id, observation_time, display_start, display_end, place_name, place_street, tel, create_date)
values(4, 'observation time', sysdate(), sysdate()+5, '대학로', '서울특별시 종로구 대학로12길 73 낙산재', '02-766-7667', sysdate());
--=========================================================================
--콘서트=======
insert into product(category_id, name, sales_start, sales_flag, event, create_date, modify_date) 
values(3, '2017 지산 밸리록 뮤직앤드아츠 페스티벌', '2017-07-11', 1, '7월 5일(수) 17:00 7차 라인업 발표! 5차 티켓 오픈!', '2017-07-11', '2017-07-11');

insert into product_detail(product_id, content, create_date, modify_date) 
values(5, '아름다운 자연환경 속에서 ‘밸리록’만의<br> ‘MUSIC & ARTS’를 함께 만들어주실<br> 관객 여러분들을 기다립니다.<br>', '2017-07-11', '2017-07-11');

insert into display_info(product_id, observation_time, display_start, display_end, place_name,place_lot, place_street, tel, homepage, create_date, modify_date)
values(5, '비고', '2017-07-11', '2017-08-11', '지산리조트', '경기도 이천시', '경기도 이천시 마장면 해월리 231<br>지산리조트', '02-446-2690', 'http://www.valleyrockfestival.com', '2017-07-11', '2017-07-11');

insert into file(user_id, file_name, save_file_name, file_length, content_type, delete_flag, create_date)
values (1, '콘서트지산밸리록대표이미지', 'http://naverbooking.phinf.naver.net/20170705_42/14992471899496RF4F_JPEG/%B9%EB%B8%AE%B7%CF_%B6%F3%C0%CE%BE%F7_%C6%F7%BD%BA%C5%CD_%C3%D6%C1%BE.jpg?type=ff1242_1242', 0, 'jpg', 0, sysdate()); 

insert into product_image(product_id, file_id, type) values(5,5,1);
--=====
insert into product(category_id, name, sales_start, sales_flag, create_date, modify_date) 
values(3, '2017 로이킴 LIVETOUR 開花期 in BUSAN', sysdate(), 1, sysdate(), '2017-07-11');

insert into product_detail(product_id, content, create_date, modify_date) 
values(6, '일시 : 2017년 7월 15일(토) 오후 6시<br>장소 : KBS부산홀<br>가격 : R석 110,000원 / S석 99,000원 <br>등급 : 만 7세이상<br>관람시간 : 120분<br><br>따뜻하고 포근한 계절에 만발하는 꽃처럼<br>강렬하고 풍부한 감성으로 충만한 로이킴이<br>2017 로이킴 LIVE TOUR로 여러분을 찾아갑니다.<br><br>로이킴만의 유니크한 향기를 지닌 그의 새로운 음악이<br>흩날리는 꽃잎처럼 당신의 귓가를 맴돌 때,<br>봄의 생기와 여름의 열정 사이에서 잠시 멈춰선 채<br>그의 음악과 꽃 피는 계절을 느껴보세요.<br><br>가장 사랑스러운 계절에, 가장 아름다운 당신을,<br>가장 매력적인 음악으로 유혹합니다.', sysdate(), '2017-07-11');

insert into display_info(product_id, observation_time, display_start, display_end, place_name,place_lot, place_street, tel, homepage, create_date, modify_date)
values(6, '비고', '2017-07-11', '2017-08-11', 'KBS부산홀', '부산광역시 수영구 남천동 63', '부산광역시 수영구 수영로 429', '1566-5490', 'http://www.wsmi.co.kr', sysdate(), '2017-07-11');

insert into file(user_id, file_name, save_file_name, file_length, content_type, delete_flag, create_date)
values (1, '콘서트지로이킴대표이미지', 'http://naverbooking.phinf.naver.net/20170609_223/1496971850314Bg6mU_JPEG/%B7%CE%C0%CC%C5%B42017_1242X1242_1.jpg?type=ff1242_1242', 0, 'jpg', 0, sysdate()); 

insert into product_image(product_id, file_id, type) values(6,6,1);