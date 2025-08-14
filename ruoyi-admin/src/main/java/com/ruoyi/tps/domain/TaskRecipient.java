package com.ruoyi.tps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 下发对象对象 task_recipient
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
public class TaskRecipient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 下发记录唯一标识（主键） */
    private Long recipientId;

    /** 关联任务ID */
    @Excel(name = "关联任务ID")
    private Long taskId;

    /** 接收对象类型（支行管理层/普通员工） */
    @Excel(name = "接收对象类型", readConverterExp = "支=行管理层/普通员工")
    private String recipientType;

    /** 接收人用户ID */
    @Excel(name = "接收人用户ID")
    private Long userId;

    /** 接收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /** 是否已读（0=未读，1=已读） */
    @Excel(name = "是否已读", readConverterExp = "0==未读，1=已读")
    private Integer isRead;

    public void setRecipientId(Long recipientId) 
    {
        this.recipientId = recipientId;
    }

    public Long getRecipientId() 
    {
        return recipientId;
    }

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setRecipientType(String recipientType) 
    {
        this.recipientType = recipientType;
    }

    public String getRecipientType() 
    {
        return recipientType;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setReceiveTime(Date receiveTime) 
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() 
    {
        return receiveTime;
    }

    public void setIsRead(Integer isRead) 
    {
        this.isRead = isRead;
    }

    public Integer getIsRead() 
    {
        return isRead;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recipientId", getRecipientId())
            .append("taskId", getTaskId())
            .append("recipientType", getRecipientType())
            .append("userId", getUserId())
            .append("receiveTime", getReceiveTime())
            .append("isRead", getIsRead())
            .toString();
    }
}
