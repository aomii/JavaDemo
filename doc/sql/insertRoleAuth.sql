-- 批量插入管理员角色对应菜单
DELIMITER $$
CREATE PROCEDURE batchInsert_role_func(IN start INT, IN end INT)
BEGIN
    DECLARE i INT DEFAULT start;
    WHILE i <= end DO
        INSERT INTO b_role_func (`role_id`, `func_id`, `create_time`)
        VALUES (1,i,NOW());
        SET i = i + 1;
END WHILE;
END$$
DELIMITER ;


-- 最小的id
SELECT @minId := (SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'ml_network_freight' AND TABLE_NAME = 'b_auth_func');

-- APP更新管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='upgrade_app');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'upgrade_app_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 服务费用查询
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='useage_log');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'useage_log_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 计划二维码
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='plan_qrcode');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'plan_qrcode_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 计划列表
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='plan_list');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'plan_list_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 计划派单
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='plan_range');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'plan_range_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 运单管理列表
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='waybill_list');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'waybill_list_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 单证管理列表
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='documents_check');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'documents_check_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 历史单证
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='documents_history');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'documents_history_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 待支付运单
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jsgl_topay');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jsgl_topay_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 已支付运单
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jsgl_paid');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jsgl_paid_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 支付记录
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jsgl_record');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jsgl_record_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 开票申请列表页
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='receipt_apply');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'receipt_apply_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 开票记录列表
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='receipt_records');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'receipt_records_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 运输合同
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='contract_list');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'contract_list_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 企业印章管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='contract_stamp');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'contract_stamp_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 客户合同
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='contract_customer');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'contract_customer_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 物流商合同
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='contract_logistics');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'contract_logistics_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 物流商档案
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jcda_supplier');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jcda_supplier_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 客户管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jcda_client');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jcda_client_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 地址管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jcda_addr');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jcda_addr_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 车辆档案
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jcda_car');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jcda_car_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 司机档案
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jcda_driver');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jcda_driver_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 物资档案
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='jcda_materials');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'jcda_materials_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());



-- 反馈列表
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='yjfk_call');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'yjfk_call_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 反馈处理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='yjfk_deal');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'yjfk_deal_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 评价管理记录
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='pjgl_record');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'pjgl_record_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 运量统计
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='statistics_freightVolume');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'statistics_freightVolume_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 运单统计
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='statistics_waybill');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'statistics_waybill_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 运费支付统计
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='statistics_transportPay');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'statistics_transportPay_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 司机运费统计
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='statistics_driverPrice');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'statistics_driverPrice_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 开票统计
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='statistics_invoice');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'statistics_invoice_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 车辆上报
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='report_vehicle');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'report_vehicle_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());

-- 司机上报
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='report_driver');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'report_driver_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());



-- 运单上报
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='report_waybill');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'report_waybill_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());



-- 资金流水上报
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='report_found');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'report_found_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());



-- 组织机构管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='zzgl_bDept');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'zzgl_bDept_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());



-- 岗位角色管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='zzgl_bRole');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'zzgl_bRole_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());



-- 账号管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='zzgl_bUser');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'zzgl_bUser_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 托运方管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='zzgl_shipper');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'zzgl_shipper_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 承运方管理
SET @parentId := (SELECT id FROM b_auth_func WHERE func_key='zzgl_carrier');
INSERT INTO `b_auth_func` (`parent_id`, `func_name`, `func_key`, `func_desc`, `func_url`, `func_api`, `func_type`, `func_sort`, `func_icon`, `func_status`, `create_time`, `update_time`) VALUES (@parentId, '列表', 'zzgl_carrier_page', '列表', NULL, NULL, 2, 0, NULL, 0, now(), now());


-- 最大的id
SELECT @maxId := LAST_INSERT_ID();


CALL batchInsert_role_func(@minId,@maxId);


-- =======================================================

-- 删除多余的按钮
SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='lngmonitor_trace' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;

SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='receipt_set' and func_type= 2);
SET @parentId := (SELECT id FROM `b_auth_func` WHERE func_key='receipt' and func_type= 0);
UPDATE  b_auth_func set func_type= 1 , parent_id = @parentId WHERE id =@id;

SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='contract_stamp_page' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;
SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='contract_stamp_view' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;

SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='statistics_bigScreen' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;

SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='log_login' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;
SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='log_operate' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;


SET @id := (SELECT id FROM `b_auth_func` WHERE func_key='plan_qrcode_view' and func_type= 2);
DELETE  FROM `b_auth_func` WHERE id=@id;
DELETE  FROM `b_role_func` WHERE func_id=@id;


-- =======================================================

-- 单条插入托运方角色对应菜单
DROP PROCEDURE IF EXISTS insert_shipper_role_func;
DELIMITER $$
CREATE PROCEDURE insert_shipper_role_func(IN funckey varchar(255) CHARACTER  SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    DECLARE roleId INT ;
		DECLARE funcId INT ;
		set roleId = (SELECT id FROM b_role WHERE code='shipper');
		set funcId = (SELECT id FROM b_auth_func WHERE func_key = funckey);
INSERT INTO b_role_func (`role_id`, `func_id`, `create_time`) VALUES (roleId,funcId,NOW());
END$$
DELIMITER ;


-- 删除原托运方角色对应菜单
DELETE from b_role_func WHERE role_id = (SELECT id FROM b_role WHERE code='shipper');

-- 新增托运方角色的权限
CALL insert_shipper_role_func('plan_list_view');
CALL insert_shipper_role_func('plan_range_view');
CALL insert_shipper_role_func('waybill_list_view');
CALL insert_shipper_role_func('waybill_list_trace');
CALL insert_shipper_role_func('waybill_list_contract');
CALL insert_shipper_role_func('yjfk_call_submit');
CALL insert_shipper_role_func('yjfk_call_view');
CALL insert_shipper_role_func('yjfk_call_delete');
CALL insert_shipper_role_func('zzgl_bDept_edit');
CALL insert_shipper_role_func('zzgl_bDept_create');
CALL insert_shipper_role_func('zzgl_bDept_delete');
CALL insert_shipper_role_func('zzgl_bRole_edit');
CALL insert_shipper_role_func('zzgl_bRole_create');
CALL insert_shipper_role_func('zzgl_bRole_delete');
CALL insert_shipper_role_func('zzgl_bUser_edit');
CALL insert_shipper_role_func('zzgl_bUser_create');
CALL insert_shipper_role_func('zzgl_bUser_activate');
CALL insert_shipper_role_func('zzgl_bUser_deactivate');
CALL insert_shipper_role_func('zzgl_bUser_resetpwd');
CALL insert_shipper_role_func('zzgl_bRole_view');
CALL insert_shipper_role_func('zzgl_bRole_copy');
CALL insert_shipper_role_func('plan_qrcode_page');
CALL insert_shipper_role_func('plan_list_page');
CALL insert_shipper_role_func('plan_range_page');
CALL insert_shipper_role_func('waybill_list_page');
CALL insert_shipper_role_func('documents_check_page');
CALL insert_shipper_role_func('documents_history_page');
CALL insert_shipper_role_func('jcda_car_page');
CALL insert_shipper_role_func('jcda_driver_page');
CALL insert_shipper_role_func('yjfk_call_page');
CALL insert_shipper_role_func('zzgl_bDept_page');
CALL insert_shipper_role_func('zzgl_bRole_page');
CALL insert_shipper_role_func('zzgl_bUser_page');


-- =======================================================

-- 单条插入承运方角色对应菜单
DROP PROCEDURE IF EXISTS insert_carrier_role_func;
DELIMITER $$
CREATE PROCEDURE insert_carrier_role_func(IN funckey varchar(255)  CHARACTER  SET utf8mb4 COLLATE utf8mb4_general_ci )
BEGIN
    DECLARE roleId INT ;
		DECLARE funcId INT ;
		set roleId = (SELECT id FROM b_role WHERE code='carrier');
		set funcId = (SELECT id FROM b_auth_func WHERE func_key = funckey);
INSERT INTO b_role_func (`role_id`, `func_id`, `create_time`) VALUES (roleId,funcId,NOW());
END$$
DELIMITER ;

-- 删除原承运方角色对应菜单
DELETE from b_role_func WHERE role_id = (SELECT id FROM b_role WHERE code='carrier');


CALL insert_carrier_role_func('plan_list_view');
CALL insert_carrier_role_func('plan_range_view');
CALL insert_carrier_role_func('waybill_list_view');
CALL insert_carrier_role_func('waybill_list_trace');
CALL insert_carrier_role_func('waybill_list_contract');
CALL insert_carrier_role_func('yjfk_call_submit');
CALL insert_carrier_role_func('yjfk_call_view');
CALL insert_carrier_role_func('yjfk_call_delete');
CALL insert_carrier_role_func('zzgl_bDept_edit');
CALL insert_carrier_role_func('zzgl_bDept_create');
CALL insert_carrier_role_func('zzgl_bDept_delete');
CALL insert_carrier_role_func('zzgl_bRole_edit');
CALL insert_carrier_role_func('zzgl_bRole_create');
CALL insert_carrier_role_func('zzgl_bRole_delete');
CALL insert_carrier_role_func('zzgl_bUser_edit');
CALL insert_carrier_role_func('zzgl_bUser_create');
CALL insert_carrier_role_func('zzgl_bUser_activate');
CALL insert_carrier_role_func('zzgl_bUser_deactivate');
CALL insert_carrier_role_func('zzgl_bUser_resetpwd');
CALL insert_carrier_role_func('zzgl_bRole_view');
CALL insert_carrier_role_func('zzgl_bRole_copy');
CALL insert_carrier_role_func('plan_qrcode_page');
CALL insert_carrier_role_func('plan_range_page');
CALL insert_carrier_role_func('waybill_list_page');
CALL insert_carrier_role_func('documents_check_page');
CALL insert_carrier_role_func('documents_history_page');
CALL insert_carrier_role_func('jcda_car_page');
CALL insert_carrier_role_func('jcda_driver_page');
CALL insert_carrier_role_func('yjfk_call_page');
CALL insert_carrier_role_func('zzgl_bDept_page');
CALL insert_carrier_role_func('zzgl_bRole_page');
CALL insert_carrier_role_func('zzgl_bUser_page');

