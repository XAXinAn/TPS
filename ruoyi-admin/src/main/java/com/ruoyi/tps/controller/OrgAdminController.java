package com.ruoyi.tps.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.tps.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tps/orgAdmin")
public class OrgAdminController extends BaseController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/listOrgAdminTasks")
    public AjaxResult listOrgAdminTasks() {
        return AjaxResult.success(employeeService.selectEmployeeTasks());
    }

}
