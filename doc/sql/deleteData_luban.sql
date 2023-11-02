##########################分隔线################################

DROP PROCEDURE IF EXISTS deleteDataFromCompany;
/
DELIMITER $$
CREATE PROCEDURE deleteDataFromCompany(IN c_id varchar(36),IN c_name varchar(36))
BEGIN
# 物流资源
DELETE FROM logistics_driver_account WHERE id IN(SELECT d.account_id FROM logistics_provider_resource r INNER JOIN logistics_driver d on r.resource_id=d.id WHERE logistics_provider_id=c_id and resource_type=1);

DELETE FROM logistics_driver WHERE id in (SELECT resource_id FROM logistics_provider_resource WHERE  logistics_provider_id=c_id and resource_type=1);

DELETE FROM logistics_vehicle WHERE id in (SELECT resource_id FROM logistics_provider_resource WHERE  logistics_provider_id=c_id and resource_type=2);

DELETE FROM  warn_vehicle WHERE vehicle_id in (SELECT resource_id FROM logistics_provider_resource WHERE  logistics_provider_id=c_id and resource_type=2);

DELETE  FROM logistics_provider_resource WHERE  logistics_provider_id=c_id ;

DELETE FROM logistics_address WHERE customer_name=c_name;


#计划、运单、对账
DELETE FROM  logistics_plan_address WHERE plan_id in (SELECT id FROM logistics_plan WHERE  (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or shipper_customer_id=c_id));

DELETE FROM  logistics_plan_material WHERE plan_id in (SELECT id FROM logistics_plan WHERE  (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or shipper_customer_id=c_id));


DELETE FROM  logistics_waybill_material WHERE plan_id in (SELECT id FROM logistics_plan WHERE  (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or shipper_customer_id=c_id));

DELETE FROM logistics_plan WHERE (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or shipper_customer_id=c_id);


DELETE FROM logistics_settle_bill WHERE waybill_id in (SELECT id FROM logistics_waybill WHERE (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or attorney_customer_name=c_name));

DELETE FROM logistics_settle_bill_history WHERE waybill_id in (SELECT id FROM logistics_waybill WHERE (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or attorney_customer_name=c_name));

DELETE FROM logistics_waybill WHERE (send_customer_id=c_id or receive_customer_id=c_id or logistics_provider_id=c_id or attorney_customer_name=c_name);




#围栏
DELETE FROM warn_notifier WHERE warn_id  in (SELECT id FROM warn_area WHERE company_id=c_id ) and warn_type=3;

DELETE FROM warn_notifier WHERE warn_id  in (SELECT id FROM warn_early WHERE company_id=c_id )and warn_type=4;

DELETE FROM warn_notifier WHERE warn_id  in (SELECT id FROM warn_fence WHERE company_id=c_id )and warn_type=1;

DELETE FROM warn_notifier WHERE warn_id  in (SELECT id FROM warn_route WHERE company_id=c_id )and warn_type=2;


DELETE FROM  warn_area WHERE company_id=c_id;

DELETE FROM  warn_early WHERE company_id=c_id;

DELETE FROM  warn_fence WHERE company_id=c_id;

DELETE FROM  warn_record WHERE company_id=c_id;

DELETE FROM  warn_route WHERE company_id=c_id;

END $$
/
DELIMITER ;

#CALL deleteDataFromCompany("00133e78-368d-4dc0-b254-c39db386564d","劳务测试零七");


