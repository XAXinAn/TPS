package com.ruoyi.tps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务反馈对象 task_feedback
 * 
 * @author ruoyi
 * @date 2025-08-19
 */
public class TaskFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈ID (主键) */
    private Long feedbackId;

    /** 关联的任务ID */
    @Excel(name = "关联的任务ID")
    private Long taskId;

    /** 反馈状态 (例如：已完成, 未完成) */
    @Excel(name = "反馈状态 (例如：已完成, 未完成)")
    private String status;

    /** 反馈内容或原因 */
    @Excel(name = "反馈内容或原因")
    private String comment;

    /** 附件URL列表 (多个用逗号分隔) */
    @Excel(name = "附件URL列表 (多个用逗号分隔)")
    private String attachments;

    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    public void setAttachments(String attachments) 
    {
        this.attachments = attachments;
    }

    public String getAttachments() 
    {
        return attachments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feedbackId", getFeedbackId())
            .append("taskId", getTaskId())
            .append("status", getStatus())
            .append("comment", getComment())
            .append("attachments", getAttachments())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
