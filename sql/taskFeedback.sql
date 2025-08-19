DROP TABLE IF EXISTS `task_feedback`;

CREATE TABLE `task_feedback` (
  `feedback_id`   bigint(20)    NOT NULL AUTO_INCREMENT    COMMENT '反馈ID',
  `task_id`       bigint(20)    NOT NULL                   COMMENT '关联的任务ID',
  `status`        varchar(64)   DEFAULT ''                 COMMENT '反馈状态 (例如：已完成, 未完成)',
  `comment`       varchar(500)  DEFAULT ''                 COMMENT '反馈内容或原因',
  `create_by`     bigint(20)    DEFAULT NULL               COMMENT '创建人ID（反馈人用户ID）',
  `create_time`   datetime      NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（反馈时间）',
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='任务反馈表';
