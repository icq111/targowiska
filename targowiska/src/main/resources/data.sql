insert into employee values (1,true, 'www','admin','admin','pass','ROLE_ADMIN', 'admin');




create or replace view product_price_list_view as 
select
ppl.price_list_id as price_list_id,
ppl.product_category_id as product_category_id,
ppl.product_id as product_id,
p.product_name as product_name,
pc.category_name as product_category_name,
ppl.price as product_price,
oppl.price as old_product_price,
((ppl.price * 100) / oppl.price) as percentual_difference
from product_price_list ppl
left outer join product p on ppl.product_id = p.id
left outer join product_category pc on ppl.product_category_id = pc.id
left outer join product_price_list oppl on ppl.product_id = oppl.product_id and ppl.product_category_id = oppl.product_category_id and ppl.price_list_id - 1 = oppl.price_list_id;

