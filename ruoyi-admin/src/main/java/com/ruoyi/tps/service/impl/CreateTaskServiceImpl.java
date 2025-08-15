package com.ruoyi.tps.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.tps.DTO.TaskCreateDTO;
import com.ruoyi.tps.domain.*;
import com.ruoyi.tps.mapper.*;
import com.ruoyi.tps.service.ICreateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateTaskServiceImpl implements ICreateTaskService {

    @Autowired
    private TaskRecipientMapper taskRecipientMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskOrgUserAdminConfigMapper taskOrgUserAdminConfigMapper;
    @Autowired
    private TaskNoticeMapper taskNoticeMapper;
    @Autowired
    private TaskAttachmentMapper taskAttachmentMapper;

    public int insertTask(TaskCreateDTO taskCreateDTO){
        //TODO
        int row = 0;
        Task task = new Task();

        System.out.println(taskCreateDTO);
        //插入 Task
        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        task.setDeadline(taskCreateDTO.getDeadline());
        task.setNeedConfirm(taskCreateDTO.getConfirm()?1:0);
        task.setPriority(taskCreateDTO.getPriority());
        task.setCreatorId(SecurityUtils.getUserId());
        System.out.println(task);
        row = taskMapper.insertTask(task);

        // 插入 TaskRecipient 和 TaskNotice
        if (taskCreateDTO.getRecipientType() == 2){
            for (Long userId: taskCreateDTO.getRecipients()){
                TaskRecipient taskRecipient = new TaskRecipient();
                taskRecipient.setTaskId(task.getTaskId());
                taskRecipient.setRecipientType("普通员工");
                taskRecipient.setUserId(userId);
                System.out.println(taskRecipient);
                taskRecipientMapper.insertTaskRecipient(taskRecipient);

                TaskNotice taskNotice = new TaskNotice();
                taskNotice.setTaskId(task.getTaskId());
                taskNotice.setUserId(userId);
                taskNotice.setContent("你有一个新任务：" + task.getTitle());
                taskNotice.setNoticeType("总行管理部门任务下发");
                System.out.println(taskNotice);
                taskNoticeMapper.insertTaskNotice(taskNotice);
            }
        }else if(taskCreateDTO.getRecipientType() == 1){
            for (Long orgId: taskCreateDTO.getRecipients()){
                TaskRecipient taskRecipient = new TaskRecipient();
                taskRecipient.setTaskId(task.getTaskId());
                taskRecipient.setRecipientType("支行管理层");

                TaskNotice taskNotice = new TaskNotice();
                taskNotice.setTaskId(task.getTaskId());
                taskNotice.setContent("你有一个新任务：" + task.getTitle());
                taskNotice.setNoticeType("总行管理部门任务下发");

                TaskOrgUserAdminConfig taskOrgUserAdminConfig = new TaskOrgUserAdminConfig();
                taskOrgUserAdminConfig.setOrgId(orgId);
                List<TaskOrgUserAdminConfig> taskOrgUserAdminConfigList = taskOrgUserAdminConfigMapper.selectTaskOrgUserAdminConfigList(taskOrgUserAdminConfig);
                for (TaskOrgUserAdminConfig config: taskOrgUserAdminConfigList){
                    taskRecipient.setUserId(config.getUserId());
                    taskNotice.setUserId(config.getUserId());
                    System.out.println(taskRecipient);
                    System.out.println(taskNotice);
                    taskRecipientMapper.insertTaskRecipient(taskRecipient);
                    taskNoticeMapper.insertTaskNotice(taskNotice);
                }
            }
        }

        //插入 TaskAttachment
        TaskAttachment taskAttachment = new TaskAttachment();
        taskAttachment.setTaskId(task.getTaskId());
        //将taskCreateDTO.getAttachment()根据逗号分割存入String[] attachmentUrl
        String[] attachmentUrl = taskCreateDTO.getAttachment().split(",");
        for (String url: attachmentUrl){
            System.out.println(url);
            taskAttachment.setAttachmentUrl(url);
            taskAttachmentMapper.insertTaskAttachment(taskAttachment);
        }

        return row;
    }
}
