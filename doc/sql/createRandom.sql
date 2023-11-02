DROP PROCEDURE IF EXISTS randomUpdate;
create procedure randomUpdate()
begin
 declare i int;
 declare n int;
 declare p int;
 declare q int ; 
 declare r1 int;
 
 declare str VARCHAR(12) DEFAULT 'ABCD' ;
 declare a VARCHAR(12);
 declare b VARCHAR(12);
 declare c VARCHAR(12);
 declare d VARCHAR(12);
 
 set i = 1;
 set n=(SELECT id FROM b_driver ORDER BY id DESC LIMIT 1);
 
while(i<=n) do
	set r1=1+floor(RAND()*4);
	set a=SUBSTRING(str,r1,1);
	set q=10;
	if r1=1
      then
           set p = 90;
    elseif  r1 = 2
      then
          set p = 80;
    elseif r1 = 3
       then 
           set p = 70;
     else
        set p = 0;
				set q=70;
  end if;
	set b=CONCAT(p+floor(RAND()*(q)),'%');
	set c=CONCAT(p+floor(RAND()*(q)),'%');
	set d=CONCAT(p+floor(RAND()*(q)),'%');
	
	
  UPDATE  `b_driver` set grade=a,timeliness_rate=b,intact_rate=c,reception_rate=d WHERE id=i;
  set i = i+1;
end while;
end;
call randomUpdate();

-- b_carrier随机数
DROP PROCEDURE IF EXISTS randomUpdate;
create procedure randomUpdate()
begin
 declare i int;
 declare n int;
 
 declare p int;
 declare q int ; 
 
 
 declare r1 int;
 declare r2 int;
 declare r3 int;
 declare r4 int;
 
 declare str VARCHAR(12) DEFAULT 'ABCD' ;
 declare a VARCHAR(12);
 declare b VARCHAR(12);
 declare c VARCHAR(12);
 declare d VARCHAR(12);
 
 set i = 1;
 set n=(SELECT id FROM b_carrier ORDER BY id DESC LIMIT 1);
 
while(i<=n) do
	set r1=1+floor(RAND()*4);
	set a=SUBSTRING(str,r1,1);
	set q=10;
	if r1=1
      then
           set p = 90;
    elseif  r1 = 2
      then
          set p = 80;
    elseif r1 = 3
       then 
           set p = 70;
     else
        set p = 0;
				set q=70;
  end if;
	set b=CONCAT(p+floor(RAND()*(q)),'%');
	set c=CONCAT(p+floor(RAND()*(q)),'%');
	set d=CONCAT(p+floor(RAND()*(q)),'%');
	
	
  UPDATE  `b_carrier` set grade=a,timeliness_rate=b,intact_rate=c,reception_rate=d WHERE id=i;
  set i = i+1;
end while;
end;
call randomUpdate();