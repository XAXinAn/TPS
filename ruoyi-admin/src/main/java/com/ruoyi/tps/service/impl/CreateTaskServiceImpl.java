package com.ruoyi.tps.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.tps.DTO.TaskCreateDTO;
import com.ruoyi.tps.domain.Task;
import com.ruoyi.tps.domain.TaskOrgUserAdminConfig;
import com.ruoyi.tps.domain.TaskRecipient;
import com.ruoyi.tps.mapper.TaskMapper;
import com.ruoyi.tps.mapper.TaskOrgUserAdminConfigMapper;
import com.ruoyi.tps.mapper.TaskRecipientMapper;
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

        // 插入 TaskRecipient
        if (taskCreateDTO.getRecipientType() == 2){
            for (Long id: taskCreateDTO.getRecipients()){
                TaskRecipient taskRecipient = new TaskRecipient();
                taskRecipient.setTaskId(task.getTaskId());
                taskRecipient.setRecipientType("普通员工");
                taskRecipient.setUserId(id);
                System.out.println(taskRecipient);
                taskRecipientMapper.insertTaskRecipient(taskRecipient);
            }
        }else if(taskCreateDTO.getRecipientType() == 1){
            for (Long orgId: taskCreateDTO.getRecipients()){
                TaskRecipient taskRecipient = new TaskRecipient();
                taskRecipient.setTaskId(task.getTaskId());
                taskRecipient.setRecipientType("支行管理层");
                TaskOrgUserAdminConfig taskOrgUserAdminConfig = new TaskOrgUserAdminConfig();
                taskOrgUserAdminConfig.setOrgId(orgId);
                List<TaskOrgUserAdminConfig> taskOrgUserAdminConfigList = taskOrgUserAdminConfigMapper.selectTaskOrgUserAdminConfigList(taskOrgUserAdminConfig);
                for (TaskOrgUserAdminConfig config: taskOrgUserAdminConfigList){
                    taskRecipient.setUserId(config.getUserId());
                    System.out.println(taskRecipient);
                    taskRecipientMapper.insertTaskRecipient(taskRecipient);
                }
            }
        }

        return row;
    }
}
