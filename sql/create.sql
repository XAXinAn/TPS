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

CREATE TABLE task_org (
    ORG_ID BIGINT NOT NULL AUTO_INCREMENT COMMENT '支行ID',
    ORG_NAME VARCHAR(100) DEFAULT NULL COMMENT '支行名称',
    ORG1_ID VARCHAR(32) DEFAULT NULL COMMENT '支行机构号',
    PRIMARY KEY (ORG_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支行表';