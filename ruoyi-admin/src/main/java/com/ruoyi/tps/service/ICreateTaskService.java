package com.ruoyi.tps.service;

import com.ruoyi.tps.DTO.TaskCreateDTO;
import com.ruoyi.tps.domain.Task;

import java.util.List;


public interface ICreateTaskService {
    public int insertTask(TaskCreateDTO taskCreateDTO);

    public List<Task> selectAllTasks();
}
