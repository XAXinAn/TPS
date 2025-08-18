package com.ruoyi.tps.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.tps.DTO.EmployeeTaskDTO;
import com.ruoyi.tps.DTO.TaskCreateWithHaveAttachmentDTO;
import com.ruoyi.tps.domain.Task;
import com.ruoyi.tps.domain.TaskAttachment;
import com.ruoyi.tps.domain.TaskRecipient;
import com.ruoyi.tps.mapper.TaskAttachmentMapper;
import com.ruoyi.tps.mapper.TaskMapper;
import com.ruoyi.tps.mapper.TaskRecipientMapper;
import com.ruoyi.tps.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskRecipientMapper taskRecipientMapper;
    @Autowired
    private TaskAttachmentMapper taskAttachmentMapper;

    public List<EmployeeTaskDTO> selectEmployeeTasks() {
        //获取当前用户的userId
        Long userId = SecurityUtils.getUserId();
        TaskRecipient taskRecipient = new TaskRecipient();
        taskRecipient.setUserId(userId);
        List<TaskRecipient> taskRecipientList = taskRecipientMapper.selectTaskRecipientList(taskRecipient);
        List<EmployeeTaskDTO> employeeTaskDTOList = new ArrayList<>();
        for (TaskRecipient recipient : taskRecipientList){
            Task task = taskMapper.selectTaskByTaskId(recipient.getTaskId());
            TaskAttachment taskAttachment = new TaskAttachment();
            taskAttachment.setTaskId(task.getTaskId());
            List<TaskAttachment> taskAttachmentList = taskAttachmentMapper.selectTaskAttachmentList(taskAttachment);
            Long haveAttachment = taskAttachmentList.isEmpty() ? 0L : 1L;
            if (haveAttachment==1) {
                String[] attachmentUrl = new String[]{};
                List<String> list = new ArrayList<>(Arrays.asList(attachmentUrl));
                for (TaskAttachment item : taskAttachmentList) {
                    list.add(item.getAttachmentUrl());
                }
                String[] finalAttachmentUrl = list.toArray(new String[0]);
                employeeTaskDTOList.add(new EmployeeTaskDTO(task.getTaskId(),task.getTitle(),task.getDescription(),task.getDeadline(),task.getPriority(),task.getNeedConfirm(),haveAttachment,finalAttachmentUrl));
            }else {
                String[] attachmentUrl = new String[]{};
                employeeTaskDTOList.add(new EmployeeTaskDTO(task.getTaskId(),task.getTitle(),task.getDescription(),task.getDeadline(),task.getPriority(),task.getNeedConfirm(),haveAttachment,attachmentUrl));
            }
        }
        return employeeTaskDTOList;
    }
}
