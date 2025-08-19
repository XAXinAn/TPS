package com.ruoyi.tps.DTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeeTaskDTO {
    private Long taskId;

    private String title;

    private String description;

    private LocalDate deadline;

    private Integer priority;

    private Integer needConfirm;

    private Long haveAttachment;

    private String[] attachmentUrl;

    private String status;

    private String recipientType;

    private Long userId;

    private Long creatorId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
        this.attachmentUrl = attachmentUrl;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public EmployeeTaskDTO(Long taskId, String title, String description, LocalDate deadline, Integer priority, Integer needConfirm, Long haveAttachment, String[] attachmentUrl, String status, String recipientType, Long userId, Long creatorId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.needConfirm = needConfirm;
        this.haveAttachment = haveAttachment;
        this.attachmentUrl = attachmentUrl;
        this.status = status;
        this.recipientType = recipientType;
        this.userId = userId;
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "EmployeeTaskDTO{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", needConfirm=" + needConfirm +
                ", haveAttachment=" + haveAttachment +
                ", attachmentUrl=" + Arrays.toString(attachmentUrl) +
                ", status='" + status + '\'' +
                ", recipientType='" + recipientType + '\'' +
                ", userId=" + userId +
                ", creatorId=" + creatorId +
                '}';
    }
}
