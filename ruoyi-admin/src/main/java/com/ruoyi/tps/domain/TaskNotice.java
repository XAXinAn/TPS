package com.ruoyi.tps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务通知对象 task_notice
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
public class TaskNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知唯一标识（主键） */
    private Long noticeId;

    /** 关联任务ID */
    @Excel(name = "关联任务ID")
    private Long taskId;

    /** 通知接收人ID */
    @Excel(name = "通知接收人ID")
    private Long userId;

    /** 通知内容 */
    @Excel(name = "通知内容")
    private String content;

    /** 通知类型 */
    @Excel(name = "通知类型")
    private String noticeType;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "阅读时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date readTime;

    public void setNoticeId(Long noticeId) 
    {
        this.noticeId = noticeId;
    }

    public Long getNoticeId() 
    {
        return noticeId;
    }

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setNoticeType(String noticeType) 
    {
        this.noticeType = noticeType;
    }

    public String getNoticeType() 
    {
        return noticeType;
    }

    public void setReadTime(Date readTime) 
    {
        this.readTime = readTime;
    }

    public Date getReadTime() 
    {
        return readTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("noticeType", getNoticeType())
            .append("createTime", getCreateTime())
            .append("readTime", getReadTime())
            .toString();
    }
}
