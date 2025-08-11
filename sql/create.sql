-- 1. 任务主表（存储任务核心信息）
CREATE TABLE IF NOT EXISTS task_main (
    task_id VARCHAR(32) NOT NULL COMMENT '任务唯一标识（主键）',
    title VARCHAR(255) NOT NULL COMMENT '任务标题（必填项）',
    description TEXT NOT NULL COMMENT '任务描述（必填项）',
    deadline DATETIME NOT NULL COMMENT '截止日期（必填项）',
    priority TINYINT NOT NULL COMMENT '优先级（1=高，2=中，3=低）',
    need_confirm TINYINT NOT NULL COMMENT '是否需发起人确认（0=否，1=是）',
    status VARCHAR(20) NOT NULL DEFAULT '进行中' COMMENT '任务状态（进行中/待确认/已完成）',
    creator_id bigint(20) NOT NULL COMMENT '创建人ID（关联sys_user表）',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (task_id),
    KEY idx_creator (creator_id),
    CONSTRAINT fk_task_creator FOREIGN KEY (creator_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务主表';

-- 2. 任务下发对象表（记录任务接收者信息）
CREATE TABLE IF NOT EXISTS task_recipient (
    recipient_id VARCHAR(32) NOT NULL COMMENT '下发记录唯一标识（主键）',
    task_id VARCHAR(32) NOT NULL COMMENT '关联任务ID',
    recipient_type VARCHAR(20) NOT NULL COMMENT '接收对象类型（支行管理层/普通员工）',
    user_id bigint(20) NOT NULL COMMENT '接收人用户ID（关联sys_user表）',
    receive_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接收时间',
    is_read TINYINT NOT NULL DEFAULT 0 COMMENT '是否已读（0=未读，1=已读）',
    PRIMARY KEY (recipient_id),
    KEY idx_task (task_id),
    KEY idx_user (user_id),
    CONSTRAINT fk_recipient_task FOREIGN KEY (task_id) REFERENCES task_main (task_id),
    CONSTRAINT fk_recipient_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务下发对象表';

-- 3. 任务附件表（存储任务相关附件）
CREATE TABLE IF NOT EXISTS task_attachment (
    attachment_id VARCHAR(32) NOT NULL COMMENT '附件唯一标识（主键）',
    task_id VARCHAR(32) NOT NULL COMMENT '关联任务ID',
    file_name VARCHAR(255) NOT NULL COMMENT '附件原始文件名',
    file_path VARCHAR(512) NOT NULL COMMENT '附件存储路径',
    file_size BIGINT NOT NULL COMMENT '附件大小（字节）',
    file_type VARCHAR(50) NOT NULL COMMENT '附件类型（如doc、jpg）',
    upload_user_id bigint(20) NOT NULL COMMENT '上传人ID（关联sys_user表）',
    upload_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
    PRIMARY KEY (attachment_id),
    KEY idx_task_attach (task_id),
    KEY idx_upload_user (upload_user_id),
    CONSTRAINT fk_attach_task FOREIGN KEY (task_id) REFERENCES task_main (task_id),
    CONSTRAINT fk_attach_user FOREIGN KEY (upload_user_id) REFERENCES sys_user (user_id),
    CONSTRAINT ck_file_size CHECK (file_size <= 52428800) -- 限制最大50MB
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务附件表';

-- 4. 系统通知表（任务创建后自动生成通知）
CREATE TABLE IF NOT EXISTS sys_notice (
    notice_id VARCHAR(32) NOT NULL COMMENT '通知唯一标识（主键）',
    task_id VARCHAR(32) NOT NULL COMMENT '关联任务ID',
    user_id bigint(20) NOT NULL COMMENT '通知接收人ID（关联sys_user表）',
    content VARCHAR(512) NOT NULL COMMENT '通知内容（如：你有一个新任务：XXX）',
    notice_type VARCHAR(20) NOT NULL DEFAULT '任务下发' COMMENT '通知类型',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '通知创建时间',
    read_time DATETIME DEFAULT NULL COMMENT '阅读时间',
    PRIMARY KEY (notice_id),
    KEY idx_task_notice (task_id),
    KEY idx_user_notice (user_id),
    CONSTRAINT fk_notice_task FOREIGN KEY (task_id) REFERENCES task_main (task_id),
    CONSTRAINT fk_notice_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统通知表';

-- 5. 支行管理员配置表：通过员工ID和机构ID关联，明确员工对应的管理支行
CREATE TABLE IF NOT EXISTS branch_admin_relation (
    user_id BIGINT(20) NOT NULL COMMENT '员工ID（关联sys_user表）',
    dept_id BIGINT(20) NOT NULL COMMENT '机构ID（关联sys_dept表，代表所属支行）',
    PRIMARY KEY (user_id, dept_id) COMMENT '联合主键，确保员工与支行的唯一关联',
    CONSTRAINT fk_admin_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id),
    CONSTRAINT fk_admin_dept FOREIGN KEY (dept_id) REFERENCES sys_dept (dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支行管理员配置表';