package com.ruoyi.tps.controller;

import com.ruoyi.tps.DTO.TaskCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tps/org")
public class TaskController {

    @PostMapping("/createTask")
    public void createTask(TaskCreateDTO taskCreateDTO) {
        System.out.println(taskCreateDTO);
    }
}
