package com.ruoyi.tps.DTO;

public class TaskFeedbackDTO {
    private Long taskId;
    private String status;
    private String comment;
    private Long createBy;
    private String attachments;

    public TaskFeedbackDTO() {
    }

    public TaskFeedbackDTO(Long taskId, String status, String comment, Long createBy, String attachments) {
        this.taskId = taskId;
        this.status = status;
        this.comment = comment;
        this.createBy = createBy;
        this.attachments = attachments;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "TaskFeedbackDTO{" +
                "taskId=" + taskId +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", createBy=" + createBy +
                ", attachments='" + attachments + '\'' +
                '}';
    }
}
