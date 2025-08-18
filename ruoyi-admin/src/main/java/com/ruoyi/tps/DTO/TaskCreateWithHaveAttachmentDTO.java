package com.ruoyi.tps.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.time.LocalDate;

public class TaskCreateWithHaveAttachmentDTO {
    private static final long serialVersionUID = 1L;

    private Long taskId;

    private String title;

    private String description;

    private LocalDate deadline;

    private Integer priority;

    private Integer needConfirm;

    private String status;

    private Long creatorId;

    private Long haveAttachment;

    private String[] attachmentUrl;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getNeedConfirm() {
        return needConfirm;
    }

    public void setNeedConfirm(Integer needConfirm) {
        this.needConfirm = needConfirm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getHaveAttachment() {
        return haveAttachment;
    }

    public void setHaveAttachment(Long haveAttachment) {
        this.haveAttachment = haveAttachment;
    }

    public String[] getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String[] attachmentUrl) {
        attachmentUrl = attachmentUrl;
    }

    public TaskCreateWithHaveAttachmentDTO() {
    }

    public TaskCreateWithHaveAttachmentDTO(Long taskId, String title, String description, LocalDate deadline, Integer priority, Integer needConfirm, String status, Long creatorId, Long haveAttachment, String[] attachmentUrl) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.needConfirm = needConfirm;
        this.status = status;
        this.creatorId = creatorId;
        this.haveAttachment = haveAttachment;
        this.attachmentUrl = attachmentUrl;
    }

    @Override
    public String toString() {
        return "TaskCreateWithHaveAttachmentDTO{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", needConfirm=" + needConfirm +
                ", status='" + status + '\'' +
                ", creatorId=" + creatorId +
                ", haveAttachment=" + haveAttachment +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                '}';
    }
}
