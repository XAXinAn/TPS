-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支行管理员配置', '2000', '1', 'orgConfig', 'tps/orgConfig/index', 1, 0, 'C', '0', '0', 'tps:orgConfig:list', '#', 'admin', sysdate(), '', null, '支行管理员配置菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支行管理员配置查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'tps:orgConfig:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支行管理员配置新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'tps:orgConfig:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支行管理员配置修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'tps:orgConfig:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支行管理员配置删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'tps:orgConfig:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('支行管理员配置导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'tps:orgConfig:export',       '#', 'admin', sysdate(), '', null, '');