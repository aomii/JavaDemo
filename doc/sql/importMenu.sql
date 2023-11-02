


drop procedure if exists insert_app;
delimiter ;;
create procedure insert_app()
begin

  declare i int;
	declare j int;
	-- 偏移量
	declare p int;


	set autocommit=false;
	#开启事务
START TRANSACTION;
	#暂时锁表
SELECT * from sys_app_menu for UPDATE;


if exists(SELECT id FROM  sys_app WHERE app_key='tenant_admin')
(
   set i = (SELECT id FROM  sys_app WHERE app_key='tenant_admin')
)
else
(
  set i = 1+(SELECT id FROM  sys_app ORDER BY id DESC LIMIT 1);
	-- 新增应用
	INSERT INTO `fkh_tenant_test`.`sys_app` (`id`, `app_name`, `app_desc`, `app_key`, `app_type`, `app_status`, `deleted`, `create_time`, `update_time`) VALUES (i, '分布式产业智能协同平台-管理端', '分布式产业智能协同平台管理端', 'tenant_admin', 1, 1, b'0', NOW(),NOW());
)

	-- 删除之前菜单
	if exists(SELECT id FROM  sys_app_menu WHERE app_id=i)
	(DELETE FROM sys_app_menu WHERE app_id=i)

	set j = 1+(SELECT id FROM sys_app_menu  ORDER BY id DESC LIMIT 1);
	set p =j-8104;


INSERT INTO `sys_app_menu` (`id`, `app_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_alias`, `menu_url`, `interface_url`, `menu_type`, `menu_icon`, `menu_sort`, `menu_origin`, `method`, `permission`, `component`, `hidden`, `remark`, `deleted`, `create_time`, `update_time`, `temp_id`) VALUES (8104+p, i, 0, '租户管理', '租户管理', '租户管理', '/', NULL, 0, '', 0, 0, 'GET', '', '', b'0', NULL, b'1', '2022-04-08 10:31:59', '2022-04-08 10:31:59', NULL);
INSERT INTO `sys_app_menu` (`id`, `app_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_alias`, `menu_url`, `interface_url`, `menu_type`, `menu_icon`, `menu_sort`, `menu_origin`, `method`, `permission`, `component`, `hidden`, `remark`, `deleted`, `create_time`, `update_time`, `temp_id`) VALUES (8105+p, i, 0, '日志管理', '日志管理', '日志管理', '/', NULL, 0, '', 0, 0, 'GET', '', '', b'0', NULL, b'1', '2022-04-08 11:03:59', '2022-04-08 11:03:59', NULL);
INSERT INTO `sys_app_menu` (`id`, `app_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_alias`, `menu_url`, `interface_url`, `menu_type`, `menu_icon`, `menu_sort`, `menu_origin`, `method`, `permission`, `component`, `hidden`, `remark`, `deleted`, `create_time`, `update_time`, `temp_id`) VALUES (8106+p, i, 8105, '登录日志', '登录日志', '登录日志', '/', NULL, 1, '', 0, 0, 'GET', '', '', b'0', NULL, b'1', '2022-04-08 11:04:14', '2022-04-08 11:04:14', NULL);
INSERT INTO `sys_app_menu` (`id`, `app_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_alias`, `menu_url`, `interface_url`, `menu_type`, `menu_icon`, `menu_sort`, `menu_origin`, `method`, `permission`, `component`, `hidden`, `remark`, `deleted`, `create_time`, `update_time`, `temp_id`) VALUES (8107+p, i, 8105, '操作日志', '操作日志', '操作日志', '/', NULL, 1, '', 0, 0, 'GET', '', '', b'0', NULL, b'1', '2022-04-08 11:04:33', '2022-04-08 11:04:33', NULL);
INSERT INTO `sys_app_menu` (`id`, `app_id`, `parent_id`, `menu_name`, `menu_desc`, `menu_alias`, `menu_url`, `interface_url`, `menu_type`, `menu_icon`, `menu_sort`, `menu_origin`, `method`, `permission`, `component`, `hidden`, `remark`, `deleted`, `create_time`, `update_time`, `temp_id`) VALUES (8108+p, i, 0, '日志管理', '日志管理', '日志管理', '/log-management', NULL, 0, '', 7, 0, 'GET', '', 'routerView', b'0', '', b'0', '2022-04-11 15:17:57', '2022-04-12 09:19:34', NULL);


-- 更新父id
UPDATE `sys_app_menu` set parent_id= parent_id+p  and create_time=NOW() and update_time=NOW() WHERE app_id=i and parent_id!=0;

commit;
#提交操作
set autocommit=true;

end;;
delimiter ;
call insert_app();
