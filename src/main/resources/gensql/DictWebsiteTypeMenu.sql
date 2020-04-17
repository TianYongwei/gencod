-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('网站类型管理', '2283', '1', '/bus/DictWebsiteType', 'C', '0', 'bus:DictWebsiteType:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '网站类型管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('网站类型管理查询', @parentId, '1',  '#',  'F', '0', 'bus:DictWebsiteType:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('网站类型管理新增', @parentId, '2',  '#',  'F', '0', 'bus:DictWebsiteType:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('网站类型管理修改', @parentId, '3',  '#',  'F', '0', 'bus:DictWebsiteType:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('网站类型管理删除', @parentId, '4',  '#',  'F', '0', 'bus:DictWebsiteType:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('网站类型管理导出', @parentId, '5',  '#',  'F', '0', 'bus:DictWebsiteType:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
