select (
    case 
        when month(DIFFERENTIATION_DATE) BETWEEN 1 AND 3 then '1Q'
        when month(DIFFERENTIATION_DATE) BETWEEN 4 AND 6 then '2Q'
        when month(DIFFERENTIATION_DATE) BETWEEN 7 AND 9 then '3Q'
        else '4Q'
    end
) as quarter, count(id) as ecoli_count
from ecoli_data
group by quarter
order by quarter;