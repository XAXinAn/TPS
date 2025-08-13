package com.ruoyi.tps.service;

import com.ruoyi.tps.DTO.TaskCreateDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICreateTaskService {
    public int insertTask(TaskCreateDTO taskCreateDTO);
}
