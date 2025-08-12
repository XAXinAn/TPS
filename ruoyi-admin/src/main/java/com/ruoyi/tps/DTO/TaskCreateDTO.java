package com.ruoyi.tps.DTO;

import java.time.LocalDate;
import java.util.List;

public class TaskCreateDTO {
    private String title;

    private String description;

    private LocalDate deadline;

    private Integer recipientType;

    private List<Long> recipients;

    private Integer priority;

    private Boolean confirm;

    private String attachment;

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

    public Integer getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(Integer recipientType) {
        this.recipientType = recipientType;
    }

    public List<Long> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Long> recipients) {
        this.recipients = recipients;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "TaskCreateDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", recipientType=" + recipientType +
                ", recipients=" + recipients +
                ", priority=" + priority +
                ", confirm=" + confirm +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}

