-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务通知', '2000', '1', 'taskNotice', 'tps/taskNotice/index', 1, 0, 'C', '0', '0', 'tps:taskNotice:list', '#', 'admin', sysdate(), '', null, '任务通知菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务通知查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'tps:taskNotice:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务通知新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'tps:taskNotice:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务通知修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'tps:taskNotice:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务通知删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'tps:taskNotice:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务通知导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'tps:taskNotice:export',       '#', 'admin', sysdate(), '', null, '');