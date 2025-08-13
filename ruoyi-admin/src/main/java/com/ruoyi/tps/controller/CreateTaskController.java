package com.ruoyi.tps.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.tps.DTO.TaskCreateDTO;
import com.ruoyi.tps.service.ICreateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/tps/task")
public class CreateTaskController extends BaseController {

    @Autowired
    private ICreateTaskService createTaskService;

    @PostMapping("/createTask")
    public AjaxResult createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        return toAjax(createTaskService.insertTask(taskCreateDTO));
    }

}
