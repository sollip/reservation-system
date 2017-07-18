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
