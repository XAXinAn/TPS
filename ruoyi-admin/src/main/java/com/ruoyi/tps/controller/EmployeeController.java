package com.ruoyi.tps.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.tps.DTO.TaskCreateDTO;
import com.ruoyi.tps.DTO.TaskFeedbackDTO;
import com.ruoyi.tps.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tps/employee")
public class EmployeeController extends BaseController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/listEmployeeTasks")
    public AjaxResult listEmployeeTasks() {
        return AjaxResult.success(employeeService.selectEmployeeTasks());
    }

    @PostMapping("/submitFeedback")
    public AjaxResult submitFeedback(@RequestBody TaskFeedbackDTO taskFeedbackDTO) {
        return toAjax(employeeService.taskFeedback(taskFeedbackDTO));
    }

}
