
-- 存储过程加游标

-- 释放司机和车辆—单条
DROP PROCEDURE IF EXISTS free_driver_vehicle;
/
DELIMITER $$
CREATE PROCEDURE free_driver_vehicle(IN d_id varchar(255)) 
BEGIN
	declare w_id VARCHAR(255);
	declare c_no VARCHAR(255);  				
  set w_id = (SELECT id FROM  logistics_waybill WHERE driver_id=d_id ORDER BY created_time DESC  LIMIT 1);
  set c_no = (SELECT vehicle_plate_no FROM  logistics_waybill WHERE driver_id=d_id ORDER BY created_time DESC  LIMIT 1);
	UPDATE logistics_waybill SET delete_status='1' WHERE id=w_id;
	UPDATE logistics_settle_bill SET delete_status='1' WHERE waybill_id=w_id;
	UPDATE logistics_driver SET is_free='1' WHERE id=d_id; 
	UPDATE logistics_vehicle SET is_free='1' WHERE car_no=c_no ; 
END $$
/
DELIMITER ;



-- 根据物流商code批量释放司机和车辆
DROP PROCEDURE IF EXISTS free_driver_vehicle_batch;
/
DELIMITER $$
CREATE PROCEDURE free_driver_vehicle_batch(IN provider_code varchar(255)) 
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE a CHAR(255);
  DECLARE cur CURSOR FOR SELECT lv.id  FROM logistics_driver lv LEFT JOIN logistics_provider_resource lpr ON lv.id = lpr.resource_id WHERE lpr.logistics_provider_code = provider_code AND lv.driver_status = 1 AND lv.is_free = 2 AND lv.delete_status = 0 AND lpr.delete_status = 0;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO a;
    IF done THEN
      LEAVE read_loop;
    END IF;
		-- 调用单条释放
		call free_driver_vehicle(a);
  END LOOP;
  CLOSE cur;
END $$
/
DELIMITER ;
CALL free_driver_vehicle_batch('00063275');


