package com.ruoyi.tps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务附件对象 task_attachment
 * 
 * @author ruoyi
 * @date 2025-08-15
 */
public class TaskAttachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Long attachmentId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 附件URL */
    @Excel(name = "附件URL")
    private String attachmentUrl;

    public void setAttachmentId(Long attachmentId) 
    {
        this.attachmentId = attachmentId;
    }

    public Long getAttachmentId() 
    {
        return attachmentId;
    }

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setAttachmentUrl(String attachmentUrl) 
    {
        this.attachmentUrl = attachmentUrl;
    }

    public String getAttachmentUrl() 
    {
        return attachmentUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attachmentId", getAttachmentId())
            .append("taskId", getTaskId())
            .append("attachmentUrl", getAttachmentUrl())
            .append("createTime", getCreateTime())
            .toString();
    }
}
