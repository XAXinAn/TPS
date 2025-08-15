-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务附件', '2000', '1', 'attachment', 'tps/attachment/index', 1, 0, 'C', '0', '0', 'tps:attachment:list', '#', 'admin', sysdate(), '', null, '任务附件菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务附件查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'tps:attachment:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务附件新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'tps:attachment:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务附件修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'tps:attachment:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务附件删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'tps:attachment:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('任务附件导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'tps:attachment:export',       '#', 'admin', sysdate(), '', null, '');