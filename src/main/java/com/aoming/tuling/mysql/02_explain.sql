 DROP TABLE IF EXISTS `actor`;
 CREATE TABLE `actor` (
 `id` int(11) NOT NULL,
 `name` varchar(45) DEFAULT NULL,
 `update_time` datetime DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 INSERT INTO `actor` (`id`, `name`, `update_time`) VALUES (1,'a',NOW()), 
 (2,'b',NOW()), (3,'c',NOW());

DROP TABLE IF EXISTS `film`;
 CREATE TABLE `film` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(10) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 INSERT INTO `film` (`id`, `name`) VALUES (3,'film0'),(1,'film1'),(2,'film2');

 DROP TABLE IF EXISTS `film_actor`;
 CREATE TABLE `film_actor` (
 `id` int(11) NOT NULL,
 `film_id` int(11) NOT NULL,
 `actor_id` int(11) NOT NULL,
 `remark` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `idx_film_actor_id` (`film_id`,`actor_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 INSERT INTO `film_actor` (`id`, `film_id`, `actor_id`) VALUES (1,1,1),(2,1,2),(3,2,1);
 
 
 
 
 
 #2. select_type列
  explain select * from film where id = 2;
 
  set session optimizer_switch='derived_merge=off';#关闭mysql5.7新特性对衍生表的合并优化

 explain select (select 1 from actor where id = 1) from (select * from film where id = 1) der;
 set session optimizer_switch='derived_merge=on'; #还原默认配置
 
 explain select 1 union all select 1;
 
 #3. table列;这一列表示 explain 的一行正在访问哪个表
 
 #4 type列
 #system > const > eq_ref > ref > range > index > ALL
  explain select min(id) from film;
	
  explain  select * from (select * from film where id = 1) tmp;
	show warnings;
	
	explain select * from film_actor left join film on film_actor.film_id = film.id;


 explain select * from film where name = 'film1';
 
 explain select film_id from film left join film_actor on film.id = film_actor.film_id;
 
  explain select * from actor where id > 1;
 
 
 explain select * from film;
 
 explain select * from actor;
 
 #5. possible_keys列 这一列显示查询可能使用哪些索引来查找。
 
 #6. key列 这一列显示mysql实际采用哪个索引来优化对该表的访问。
 
 #7. key_len列
 
 #8. ref列 		#这一列显示了在key列记录的索引中，表查找值所用到的列或常量，常见的有：const（常量），字段名（例：film.id）
 
 #9. rows列
#这一列是mysql估计要读取并检测的行数，注意这个不是结果集里的行数。

 #10. Extra列
 
 explain select film_id from film_actor where film_id = 1;
 
 explain select * from actor where name = 'a';
 
 explain select * from film_actor where film_id > 1;
 
 explain select distinct name from actor;
 
 explain select distinct name from film;
 
 explain select * from actor order by name;
 
  explain select * from film order by name;
	
	explain select min(id) from film;

 
 
 
 #索引实践
 CREATE TABLE `employees` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(24) NOT NULL DEFAULT '' COMMENT '姓名',
 `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
 `position` varchar(20) NOT NULL DEFAULT '' COMMENT '职位',
 `hire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
 PRIMARY KEY (`id`),
KEY `idx_name_age_position` (`name`,`age`,`position`) USING BTREE
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='员工记录表';

 INSERT INTO employees(name,age,position,hire_time) VALUES('LiLei',22,'manager',NOW());
 INSERT INTO employees(name,age,position,hire_time) VALUES('HanMeimei',23,'dev',NOW());
 INSERT INTO employees(name,age,position,hire_time) VALUES('Lucy',23,'dev',NOW());


#.1全值匹配
 EXPLAIN SELECT * FROM employees WHERE name= 'LiLei';
 EXPLAIN SELECT * FROM employees WHERE name= 'LiLei' AND age = 22;
 EXPLAIN SELECT * FROM employees WHERE name= 'LiLei' AND age = 22 AND position ='manager';

#2.最左前缀法则
 EXPLAIN SELECT * FROM employees WHERE name = 'Bill' and age = 31;
 EXPLAIN SELECT * FROM employees WHERE age = 30 AND position = 'dev';
 EXPLAIN SELECT * FROM employees WHERE position = 'manager';
 
 #3.不在索引列上做任何操作（计算、函数、（自动or手动）类型转换），会导致索引失效而转向全表扫描
 EXPLAIN SELECT * FROM employees WHERE name = 'LiLei';
 EXPLAIN SELECT * FROM employees WHERE left(name,3) = 'LiLei';
 #给hire_time增加一个普通索引：
 ALTER TABLE `employees` ADD INDEX `idx_hire_time` (`hire_time`) USING BTREE ;
 EXPLAIN select * from employees where date(hire_time) ='2022-04-19';
 #转化为日期范围查询，有可能会走索引：
 EXPLAIN select * from employees where hire_time >='2022-04-19 00:00:00' and
	 hire_time <='2022-04-19 23:59:59';
 #还原最初索引状态
 ALTER TABLE `employees` DROP INDEX `idx_hire_time`;


#4.存储引擎不能使用索引中范围条件右边的列
 EXPLAIN SELECT * FROM employees WHERE name= 'LiLei' AND age = 22 AND position ='manager';
 EXPLAIN SELECT * FROM employees WHERE name= 'LiLei' AND age > 22 AND position ='manager';


#5.尽量使用覆盖索引（只访问索引的查询（索引列包含查询列）），减少 select * 语句
EXPLAIN SELECT name,age FROM employees WHERE name= 'LiLei' AND age = 23 AND position='manager';
EXPLAIN SELECT * FROM employees WHERE name= 'LiLei' AND age = 23 AND position ='manager';

#6.mysql在使用不等于（！=或者<>），not in ，not exists 的时候无法使用索引会导致全表扫描
EXPLAIN SELECT * FROM employees WHERE name != 'LiLei';

 #7.is null,is not null 一般情况下也无法使用索引
 EXPLAIN SELECT * FROM employees WHERE name is null;
 
 #8.like以通配符开头（'$abc...'）mysql索引失效会变成全表扫描操作
EXPLAIN SELECT * FROM employees WHERE name like '%Lei';
EXPLAIN SELECT * FROM employees WHERE name like 'Lei%';
-- 问题：解决like'%字符串%'索引不被使用的方法？
-- a）使用覆盖索引，查询字段必须是建立覆盖索引字段
EXPLAIN SELECT name,age,position FROM employees WHERE name like '%Lei%';
-- b）如果不能使用覆盖索引则可能需要借助搜索引擎

#9.字符串不加单引号索引失效
EXPLAIN SELECT * FROM employees WHERE name = '1000';
EXPLAIN SELECT * FROM employees WHERE name = 1000;

#10.少用or或in，用它查询时，mysql不一定使用索引，mysql内部优化器会根据检索比例、表大小等多个因素整体评
#估是否使用索引，详见范围查询优化
EXPLAIN SELECT * FROM employees WHERE name = 'LiLei' or name = 'HanMeimei';

#11.范围查询优化
#给年龄添加单值索引
ALTER TABLE `employees` ADD INDEX `idx_age` (`age`) USING BTREE ;
explain select * from employees where age >=1 and age <=2000;#5.7版本这里不走索引;8.0要走索引
-- 没走索引原因：mysql内部优化器会根据检索比例、表大小等多个因素整体评估是否使用索引。比如这个例子，可能是
-- 由于单次数据量查询过大导致优化器最终选择不走索引
-- 优化方法：可以将大的范围拆分成多个小范围
explain select * from employees where age >=1 and age <=1000;
explain select * from employees where age >=1001 and age <=2000;
-- 还原最初索引状态
ALTER TABLE `employees` DROP INDEX `idx_age`;
 
 