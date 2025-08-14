package com.ruoyi.tps.domain;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务对象 task
 * 
 * @author ruoyi
 * @date 2025-08-13
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private Long taskId;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate deadline;

    /** 优先级  */
    @Excel(name = "优先级 ")
    private Integer priority;

    /** 是否需发起人确认（0=false，1=true） */
    @Excel(name = "是否需发起人确认", readConverterExp = "0==false，1=true")
    private Integer needConfirm;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String status;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long creatorId;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDeadline(LocalDate deadline)
    {
        this.deadline = deadline;
    }

    public LocalDate getDeadline()
    {
        return deadline;
    }

    public void setPriority(Integer priority) 
    {
        this.priority = priority;
    }

    public Integer getPriority() 
    {
        return priority;
    }

    public void setNeedConfirm(Integer needConfirm) 
    {
        this.needConfirm = needConfirm;
    }

    public Integer getNeedConfirm() 
    {
        return needConfirm;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("deadline", getDeadline())
            .append("priority", getPriority())
            .append("needConfirm", getNeedConfirm())
            .append("status", getStatus())
            .append("creatorId", getCreatorId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
