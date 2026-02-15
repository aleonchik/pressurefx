create table if not exists patient (
	id    serial primary key ,
	name  varchar(50),
	birth date);

insert into patient values (DEFAULT, 'Алексей', '1971-04-16');
insert into patient values (DEFAULT, 'Ольга',   '1960-05-17');
insert into patient values (DEFAULT, 'Лена',    '1980-09-18');

create table if not exists pressure (
    id    serial primary key,
    pid   integer, -- patient.id
    sys   integer,
    dia   integer,
    pulse integer,
    dt    timestamp);


