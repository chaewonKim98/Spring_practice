select * from tbl_board;
-- 스프링에서 디비를 직접 연결함
create table min_board(
	id varchar (30) not null,
	pw varchar (30) not null,
	name varchar (30) not null,
	age varchar (30) not null,
	primary key(id)
);
insert into min_board(id,pw,name,age) values("min","1234","coco","12");
select * from min_board;
DESCRIBE tbl_board;