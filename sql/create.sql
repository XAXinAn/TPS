CREATE TABLE IF NOT EXISTS task (
    task_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '任务唯一标识（主键）',
    title VARCHAR(255) NOT NULL COMMENT '任务标题（必填项）',
    description TEXT NOT NULL COMMENT '任务描述（必填项）',
    deadline DATETIME NOT NULL COMMENT '截止日期（必填项）',
    priority TINYINT NOT NULL COMMENT '优先级 ',
    need_confirm TINYINT(1) NOT NULL COMMENT '是否需发起人确认（0=false，1=true）',
    status VARCHAR(20) NOT NULL DEFAULT '进行中' COMMENT '任务状态（进行中/待确认/已完成）',
    creator_id bigint(20) NOT NULL COMMENT '创建人ID（关联sys_user表）',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (task_id),
    KEY idx_creator (creator_id),
    CONSTRAINT fk_task_creator FOREIGN KEY (creator_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务主表';

CREATE TABLE IF NOT EXISTS task_org_user_admin_config (
    org_user_admin_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '管理员配置唯一标识（主键）',
    org_id BIGINT(20) NOT NULL COMMENT '支行ID（关联task_org表，代表支行）',
    user_id BIGINT(20) NOT NULL COMMENT '用户ID（关联sys_user表，代表该支行的管理员）',
    PRIMARY KEY (org_user_admin_id),
    KEY idx_org (org_id),
    KEY idx_user (user_id),
    CONSTRAINT fk_config_org FOREIGN KEY (org_id) REFERENCES task_org (org_id),
    CONSTRAINT fk_config_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支行管理员配置表';

CREATE TABLE IF NOT EXISTS task_recipient (
    recipient_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '下发记录唯一标识（主键）',
    task_id BIGINT(20) NOT NULL COMMENT '关联任务ID（关联task表）',
    recipient_type VARCHAR(20) NOT NULL COMMENT '接收对象类型（支行管理层/普通员工）',
    user_id bigint(20) NOT NULL COMMENT '接收人用户ID（关联sys_user表）',
    receive_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接收时间',
    is_read TINYINT NOT NULL DEFAULT 0 COMMENT '是否已读（0=未读，1=已读）',
    PRIMARY KEY (recipient_id),
    KEY idx_task (task_id),
    KEY idx_user (user_id),
    CONSTRAINT fk_recipient_task FOREIGN KEY (task_id) REFERENCES task (task_id),
    CONSTRAINT fk_recipient_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务下发对象表';

CREATE TABLE IF NOT EXISTS task_notice (
    notice_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '通知唯一标识（主键）',
    task_id BIGINT(20) NOT NULL COMMENT '关联任务ID',
    user_id bigint(20) NOT NULL COMMENT '通知接收人ID',
    content VARCHAR(512) NOT NULL COMMENT '通知内容（如：你有一个新任务：XXX）',
    notice_type VARCHAR(20) NOT NULL DEFAULT '总行管理部门任务下发' COMMENT '通知类型',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '通知创建时间',
    read_time DATETIME DEFAULT NULL COMMENT '阅读时间',
    PRIMARY KEY (notice_id),
    KEY idx_task_notice (task_id),
    KEY idx_user_notice (user_id),
    CONSTRAINT fk_notice_task FOREIGN KEY (task_id) REFERENCES task (task_id),
    CONSTRAINT fk_notice_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务通知表';

CREATE TABLE task_org (
    ORG_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT '支行ID',
    ORG_NAME VARCHAR(100) DEFAULT NULL COMMENT '支行名称',
    ORG1_ID VARCHAR(32) DEFAULT NULL COMMENT '支行机构号',
    PRIMARY KEY (ORG_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支行表';