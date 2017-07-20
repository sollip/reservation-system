--select product 전체 리스트
select product_id, name, description, place_name,save_file_name 
from  product as p,display_info as d, product_image as pi 
where p.id=d.product_id and pi.  ;

--select 카테고리별 product list 
select product_id, name, description, place_name from  product as p,display_info as d  where p.id=d.product_id  and p.category_id=1;

select * 
from product as a, product_image as b, file as c, display as d
where d.product_id=a.id and b.file_id=c.id and b.product_id= a.id;

select count(id) from product where category_id=3;
--=====
select count(id) from product;

select product_id, name, description, place_name 
from  product as p,display_info as d  
where p.id=d.product_id  and p.category_id=3 Limit 4 offset 1;

select product_id, name, description, place_name from  product p,display_info d where p.id=d.product_id limit 1, offset 1;

select p.id, name, description, place_name, save_file_name
from product as p, product_detail as pd, file as f, product_image as pi, display_info as di 
where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and pi.file_id=f.id;
--detail
select p.id, name, description, place_name, save_file_name, file_name
from product as p, product_detail as pd, file as f, product_image as pi, display_info as di
where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and pi.file_id=f.id and p.id=2;
--
insert into file (user_id,file_name,save_file_name,file_length,content_type,delete_flag)
values(1,"이미지13","C:/Users/sollip/img/7.jpg",0,0,0);
insert into file (user_id,file_name,save_file_name,file_length,content_type,delete_flag)
values(1,"이미지14","C:/Users/sollip/img/8.jpg",0,0,0);
insert into file (user_id,file_name,save_file_name,file_length,content_type,delete_flag)
values(1,"이미지15","C:/Users/sollip/img/9.jpg",0,0,0);
insert into file (user_id,file_name,save_file_name,file_length,content_type,delete_flag)
values(1,"이미지16","C:/Users/sollip/img/10.jpg",0,0,0);
insert into file (user_id,file_name,save_file_name,file_length,content_type,delete_flag)
values(1,"이미지17","C:/Users/sollip/img/11.jpg",0,0,0);
insert into file (user_id,file_name,save_file_name,file_length,content_type,delete_flag)
values(1,"이미지18","C:/Users/sollip/img/12.jpg",0,0,0);


insert into product_image(product_id,file_id,type)
values(7,13,1);
insert into product_image(product_id,file_id,type)
values(8,14,1);
insert into product_image(product_id,file_id,type)
values(9,15,1);
insert into product_image(product_id,file_id,type)
values(10,16,1);
insert into product_image(product_id,file_id,type)
values(11,17,1);
insert into product_image(product_id,file_id,type)
values(12,18,1);

-- 이미지 가져오기

select file_name,save_file_name,type,file_id from product_image as pi, file as f
where pi.product_id=1 and pi.file_id=f.id;

select count(file_id) from product_image as pi, file as f
where pi.product_id=1 and pi.file_id=f.id;

select type,file_id from product_image as pi
where pi.product_id=1 ;

select id, file_name, save_file_name, content_type from file where id=1;

select DISTINCT p.id, name, description, content, place_name, place_street, place_lot, homepage, email, tel, sales_flag, sales_end,file_id
from product as p, product_detail as pd, product_image as pi, display_info as di
where p.id=pd.product_id and p.id=di.product_id and p.id=pi.product_id and p.id=1 and pi.type=1 ;

select count(id) from reservation_user_comment where product_id=1;

select username,nickname,sns_id,email
from users
where id=2;


insert into users(username,email,tel,nickname,admin_flag,create_date)
values("sooollip","sooollip@naver.com","01012345678","소립","0",sysdate());

insert into users(id,admin_flag,sns_profile,sns_id,nickname,email,username,create_date)
values("123",0,"0","123","멍청이","123@123","바보",sysdate());