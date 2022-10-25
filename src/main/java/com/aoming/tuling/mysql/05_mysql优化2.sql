CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  `position` varchar(20) NOT NULL DEFAULT '' COMMENT '职位',
  `hire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  PRIMARY KEY (`id`),
  KEY `idx_name_age_position` (`name`,`age`,`position`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='员工记录表';

INSERT INTO employees(name,age,position,hire_time) VALUES('LiLei',22,'manager',NOW());
INSERT INTO employees(name,age,position,hire_time) VALUES('HanMeimei', 23,'dev',NOW());
INSERT INTO employees(name,age,position,hire_time) VALUES('Lucy',23,'dev',NOW());

-- 插入一些示例数据
drop procedure if exists insert_emp; 
delimiter ;;
create procedure insert_emp()        
begin
  declare i int;                    
  set i=1;                          
  while(i<=100000)do                 
    insert into employees(name,age,position) values(CONCAT('zhuge',i),i,'dev');  
    set i=i+1;                       
  end while;
end;;
delimiter ;
call insert_emp();


TRUNCATE table employees;



CREATE TABLE employees_copy like employees;
INSERT INTO employees_copy(name,age,position,hire_time) VALUES('LiLei',22,'manager',NOW());
INSERT INTO employees_copy(name,age,position,hire_time) VALUES('HanMeimei', 23,'dev',NOW());
INSERT INTO employees_copy(name,age,position,hire_time) VALUES('Lucy',23,'dev',NOW());



---- 优化二
select * from employees limit 10000,10;

select * from employees limit 90000,5;

select * from employees where id > 90000 limit 5;

EXPLAIN select * from employees limit 90000,5;

EXPLAIN select * from employees where id > 90000 limit 5;

 select * from employees ORDER BY name limit 90000,5;
 
 EXPLAIN select * from employees ORDER BY name limit 90000,5;
 
 select * from employees e inner join (select id from employees order by name limit 90000,5) ed on e.id = ed.id;
 
 EXPLAIN  select * from employees e inner join (select id from employees order by name limit 90000,5) ed on e.id = ed.id;
 
 
 
 -- Join关联查询优化
 -- 示例表：
CREATE TABLE `t1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` int(11) DEFAULT NULL,
  `b` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_a` (`a`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table t2 like t1;

-- 插入一些示例数据
-- 往t1表插入1万行记录
drop procedure if exists insert_t1; 
delimiter ;;
create procedure insert_t1()        
begin
  declare i int;                    
  set i=1;                          
  while(i<=10000)do                 
    insert into t1(a,b) values(i,i);  
    set i=i+1;                       
  end while;
end;;
delimiter ;
call insert_t1();

-- 往t2表插入100行记录
drop procedure if exists insert_t2; 
delimiter ;;
create procedure insert_t2()        
begin
  declare i int;                    
  set i=1;                          
  while(i<=100)do                 
    insert into t2(a,b) values(i,i);  
    set i=i+1;                       
  end while;
end;;
delimiter ;
call insert_t2();
 


-- NLJ
EXPLAIN select * from t1 inner join t2 on t1.a= t2.a;

-- BNL
EXPLAIN select * from t1 inner join t2 on t1.b= t2.b;


-- 小表驱动大表
EXPLAIN select * from t2 straight_join t1 on t2.a = t1.a; 

EXPLAIN select * from t1 straight_join t2 on t2.a = t1.a; 



-- in和exsits优化
explain select * from t1 where id in (select id from t2) ; 

explain select * from  t2  where exists (select 1 from t1 where t1.id =t2.id);

-- count(*)查询优化

set global query_cache_size=0;
set global query_cache_type=0;

EXPLAIN select count(1) from employees;
EXPLAIN select count(id) from employees;
-- 以上4条sql只有根据某个字段count不会统计字段为null值的数据行
EXPLAIN select count(name) from employees;
EXPLAIN select count(*) from employees;

show table status like 'employees';
show table status like 'employees_copy';
show table status like 't1';
show table status like 't2';

