select Year(YM) as YEAR,
    round(AVG(PM_VAL1), 2) as pm10,
    round(AVG(PM_VAL2), 2) as 'pm2.5'
from air_pollution
where location2 = '수원'
group by year
order by year;