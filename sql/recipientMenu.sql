-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('下发对象', '2000', '1', 'recipient', 'tps/recipient/index', 1, 0, 'C', '0', '0', 'tps:recipient:list', '#', 'admin', sysdate(), '', null, '下发对象菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('下发对象查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'tps:recipient:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('下发对象新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'tps:recipient:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('下发对象修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'tps:recipient:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('下发对象删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'tps:recipient:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('下发对象导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'tps:recipient:export',       '#', 'admin', sysdate(), '', null, '');