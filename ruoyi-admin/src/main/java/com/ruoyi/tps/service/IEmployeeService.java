package com.ruoyi.tps.service;

import com.ruoyi.tps.DTO.EmployeeTaskDTO;

import java.util.List;

public interface IEmployeeService {
    public List<EmployeeTaskDTO> selectEmployeeTasks();
}
