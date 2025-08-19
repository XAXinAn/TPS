package com.ruoyi.tps.DTO;

public class TaskFeedbackDTO {
    private Long taskId;
    private Long userId;
    private String status;
    private String comment;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskFeedbackDTO() {
    }

    public TaskFeedbackDTO(Long taskId, Long userId, String status, String comment) {
        this.taskId = taskId;
        this.userId = userId;
        this.status = status;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TaskFeedbackDTO{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
