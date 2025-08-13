package com.ruoyi.tps.controller;

import com.ruoyi.tps.DTO.TaskCreateDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tps/task")
public class CreateTaskController {

    @PostMapping("/createTask")
    public void createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        System.out.println(taskCreateDTO);
    }
}
