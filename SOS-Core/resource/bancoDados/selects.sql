
--seleciona as salas que nao estao reservadas na data 2012-11-07 das 06:00:00 as 07:30:00
select * from salas s where s.id not in (select sala_id from reservas r where not (('2012-11-07 06:00:00' < r.data_inicial and '2012-11-07 07:30:00' <= r.data_inicial) or ('2012-11-07 06:00:00' >= r.data_final and '2012-11-07 07:30:00' > r.data_final)))