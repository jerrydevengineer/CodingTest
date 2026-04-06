select h.history_id, 
(
    case
        when datediff(h.end_date, h.start_date) + 1 >= 90 then c.daily_fee * (datediff(h.end_date, h.start_date) + 1) * (1 - (select REGEXP_REPLACE(DISCOUNT_RATE, '[^0-9]', '') from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where car_type = '트럭' and DURATION_TYPE like '90%') * 0.01)
        when datediff(h.end_date, h.start_date) + 1 >= 30 then c.daily_fee * (datediff(h.end_date, h.start_date) + 1) * (1 - (select REGEXP_REPLACE(DISCOUNT_RATE, '[^0-9]', '') from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where car_type = '트럭' and DURATION_TYPE like '30%') * 0.01)
        when datediff(h.end_date, h.start_date) + 1 >= 7 then c.daily_fee * (datediff(h.end_date, h.start_date) + 1) * (1 - ((select REGEXP_REPLACE(DISCOUNT_RATE, '[^0-9]', '') from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where car_type = '트럭' and DURATION_TYPE like '7%') * 0.01))
        else c.daily_fee * (datediff(h.end_date, h.start_date) + 1)
    end
) as fee
from car_rental_company_rental_history h
join car_rental_company_car c
on c.car_id = h.car_id
where c.car_type = '트럭'
order by 2 desc, 1 desc;