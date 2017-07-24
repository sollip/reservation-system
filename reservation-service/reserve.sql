select id,product_id,price,price_type,discount_rate
from product_price
where product_id=1;

update users
set username="바보"
output inserted.id 
where id=2;

insert into users(id,admin_flag,sns_profile,sns_id,nickname,email,username,create_date)
			 values(1234,0,"dd","dd","dd","dd","dd",sysdate());