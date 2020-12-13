insert into employee values (1,true, 'www','admin','admin','pass','ROLE_ADMIN', 'admin');




CREATE EXTENSION IF NOT EXISTS "uuid-ossp";




create or replace view product_price_list_view as 
select
uuid_generate_v4() as id,
coalesce(ppl.price_list_id, (select max(price_list_id) from product_price_list), 0) as price_list_id,
p.product_category as product_category,
p.id as product_id,
p.product_name as product_name,
ppl.price as product_price,
oppl.price as old_product_price,
(((ppl.price/oppl.price)*100)-100) as percentual_price_difference,
ppl.insert_stamp as price_list_date,
p.status as product_status
from product p
left outer join product_price_list ppl on p.id = ppl.product_id
left outer join product_price_list oppl on ppl.product_id = oppl.product_id and ppl.price_list_id = oppl.price_list_id +1 ;
