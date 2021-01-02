insert into employee values (1,true, 'www','admin','admin','pass','ROLE_ADMIN', 'admin');




CREATE EXTENSION IF NOT EXISTS "uuid-ossp";




create or replace view product_price_list_view as 
select
uuid_generate_v4() as id,
coalesce(ppl.price_list_id, (select max(price_list_id) from product_price_list), 0) as price_list_id,
p.product_category as product_category,
p.id as product_id,
p.product_name as product_name,
ppl.minimum_price as minimum_product_price,
ppl.maximum_price as maximum_product_price,
oppl.minimum_price as old_product_price,
ppl.insert_stamp as price_list_date,
p.status as product_status,
u.short_name as unit
from product p
left outer join unit u on p.unit_id = u.id
left outer join product_price_list ppl on p.id = ppl.product_id
left outer join product_price_list oppl on ppl.product_id = oppl.product_id and ppl.price_list_id = oppl.price_list_id +1 ;





INSERT INTO public.unit
(insert_stamp, status, update_stamp, "name", short_name)
VALUES(CURRENT_DATE, 'ACTIVE',CURRENT_DATE, 'Sztuki', 'SZT');

INSERT INTO public.unit
(insert_stamp, status, update_stamp, "name", short_name)
VALUES(CURRENT_DATE, 'ACTIVE',CURRENT_DATE, 'Kilogram', 'KG');



INSERT INTO public.slider (insert_stamp, status, update_stamp, big_text, file_name, slide_id, small_text) 
VALUES(current_date, 'ACTIVE', current_date, 'Big tekst 1', '1.jpg', 1, 'Small text 1');

INSERT INTO public.slider (insert_stamp, status, update_stamp, big_text, file_name, slide_id, small_text) 
VALUES(current_date, 'ACTIVE', current_date, 'Big tekst 2', '2.jpg', 2, 'Small text 2');

INSERT INTO public.slider (insert_stamp, status, update_stamp, big_text, file_name, slide_id, small_text) 
VALUES(current_date, 'ACTIVE', current_date, 'Big tekst 3', '3.jpg', 3, 'Small text 3');

INSERT INTO public.slider (insert_stamp, status, update_stamp, big_text, file_name, slide_id, small_text) 
VALUES(current_date, 'ACTIVE', current_date, 'Big tekst 4', '4.jpg', 4, 'Small text 4');
