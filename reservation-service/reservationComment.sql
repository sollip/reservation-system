--user insert
insert into users(username,email,tel,nickname,admin_flag,create_date)
values("sooollip","sooollip@naver.com","01012345678","소립","0",sysdate());
insert into users(username,email,tel,nickname,admin_flag,create_date)
values("s.e.o.2","s.e.o.2@naver.com","01012345678","서이","0",sysdate());
insert into users(username,email,tel,nickname,admin_flag,create_date)
values("_bbeeny_","_bbeeny_@naver.com","01012345678","김숩","0",sysdate());
insert into users(username,email,tel,nickname,admin_flag,create_date)
values("gonii_i","gonii_i@naver.com","01012345678","정곤","0",sysdate());
insert into users(username,email,tel,nickname,admin_flag,create_date)
values("min9x9","min9x9@naver.com","01012345678","민구","0",sysdate());

insert into users(username,email,tel,nickname,admin_flag,create_date)
values("jungin_ha","jungin_ha@naver.com","01012345678","정인","0",sysdate());


insert into users(username,email,tel,nickname,admin_flag,create_date)
values("jh.zip","jh.zip@naver.com","01012345678","지녕","0",sysdate());

--한줄평insert
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,2,4,"넘재밌습니다~~~",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,3,3,"우와~~~~~~",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,4,1,"또가고싶어요~~~",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,5,5,"괜찮네요.",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,6,4,"아이들이 너무 좋아합니다^^",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,7,3,"악 너무 좋았어요!",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,8,4,"재미가 없네요",sysdate());
insert into reservation_user_comment (product_id,user_id,score,comment,create_date)
values(1,2,4,"볼거없네요ㅜㅜ",sysdate());

--select
select DISTINCT product_id,username,score,comment,r.create_date,file_id
from reservation_user_comment as r, product as p, users as u, reservation_user_comment_image as ri
where p.id=r.product_id and u.id=r.user_id JOIN

select reservation_user_comment_id, file_id,count
from reservation_user_comment_image as ri, reservation_user_comment as r
where r.product_id=1 and r.id=ri.reservation_user_comment_id
group by reservation_user_comment_id;

select DISTINCT product_id,username,score,comment,r.create_date,file_id
from (select DISTINCT r.id,file_id,count(id)
from reservation_user_comment as r
left join reservation_user_comment_image as ri
on r.id=ri.reservation_user_comment_id group by id) as a,reservation_user_comment as r,users as u
where r.id=a.id  and u.id=r.user_id and r.product_id=1;

select DISTINCT r.id,file_id
from reservation_user_comment as r
left join reservation_user_comment_image as ri
on r.id=ri.reservation_user_comment_id;

select rr.id, product_id,username,score,comment,DATE_FORMAT(rr.create_date, '%Y-%m-%d') create_date,file_id,image_count
from(select DISTINCT r.id,product_id,user_id,score,comment,r.create_date,file_id,image_count
	from reservation_user_comment as r
	left join (select DISTINCT r2.id,file_id,count(r2.id) as image_count
				from reservation_user_comment as r2, reservation_user_comment_image as ri2
				where r2.id=ri2.reservation_user_comment_id group by r2.id)as a 
	on r.id=a.id)as rr, users as u 
where u.id=rr.user_id order by rr.id ;



SELECT DATE_FORMAT(create_date, '%Y-%m-%d') create_date
from reservation_user_comment;
