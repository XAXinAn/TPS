package com.ruoyi.tps.service;

import com.ruoyi.tps.DTO.EmployeeTaskDTO;
import com.ruoyi.tps.DTO.TaskCreateDTO;
import com.ruoyi.tps.DTO.TaskFeedbackDTO;

import java.util.List;

public interface IEmployeeService {
    public List<EmployeeTaskDTO> selectEmployeeTasks();


    public int taskFeedback(TaskFeedbackDTO taskFeedbackDTO);
}
