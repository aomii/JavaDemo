
-- 涉及批量和编码
-- 根据运单号修改 运单单价、结算单单价、结算应付、结算实付
DROP PROCEDURE IF EXISTS update_waybill_price;
/
DELIMITER $$
CREATE PROCEDURE update_waybill_price(IN w_id varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci) 
BEGIN

	declare plan_price_temp decimal(10,2);
	
	SELECT transport_unit_price INTO plan_price_temp FROM logistics_plan l1 INNER JOIN logistics_waybill_material l2 on l1.id=l2.plan_id WHERE l2.waybill_id=w_id limit 1;
	
	UPDATE logistics_waybill SET transport_unit_price=plan_price_temp WHERE id = w_id;
	UPDATE logistics_settle_bill set unit_price=plan_price_temp WHERE waybill_id =w_id;
	UPDATE logistics_settle_bill set pay_price=plan_price_temp * settle_weight WHERE waybill_id =w_id and pay_price is not null;
	UPDATE logistics_settle_bill set real_price=plan_price_temp * settle_weight- IFNULL(abnormal_fine,0.00) + IFNULL(subsidy_price ,0.00)  WHERE waybill_id =w_id and real_price is not null;
END $$
/
DELIMITER ;


-- 批量更新异常单价
DROP PROCEDURE IF EXISTS update_waybill_price_batch;
/
DELIMITER $$
CREATE PROCEDURE update_waybill_price_batch() 
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE a CHAR(255);
  DECLARE cur CURSOR FOR SELECT DISTINCT waybill_id FROM  logistics_waybill_material WHERE plan_id in (select id FROM  logistics_plan WHERE ( transport_unit_price != CEIL(transport_unit_price)));
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO a;
    IF done THEN
      LEAVE read_loop;
    END IF;
		-- 调用单条
		call update_waybill_price(a);
  END LOOP;
  CLOSE cur;
END $$
/
DELIMITER ;

CALL update_waybill_price_batch();




-- 修改对账单金额-单条
DROP PROCEDURE IF EXISTS update_recon;
/
DELIMITER $$
CREATE PROCEDURE update_recon(IN r_id varchar(255)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci) 
BEGIN

	declare transport_price_temp decimal(10,2);
	SELECT sum(l2.real_price)  INTO transport_price_temp FROM logistics_recon_settle l1 INNER JOIN logistics_settle_bill l2 on l1.settle_bill_id=l2.id WHERE l1.recon_bill_id=r_id;
	update logistics_recon_bill  set transport_price = transport_price_temp WHERE id =r_id;
END $$
/
DELIMITER ;


-- 批量重新计算所有对账单金额
DROP PROCEDURE IF EXISTS update_recon_batch;
/
DELIMITER $$
CREATE PROCEDURE update_recon_batch() 
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE a CHAR(255);
  DECLARE cur CURSOR FOR SELECT id FROM logistics_recon_bill;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO a;
    IF done THEN
      LEAVE read_loop;
    END IF;
		-- 调用单条
		call update_recon(a);
  END LOOP;
  CLOSE cur;
END $$
/
DELIMITER ;

CALL update_recon_batch();

