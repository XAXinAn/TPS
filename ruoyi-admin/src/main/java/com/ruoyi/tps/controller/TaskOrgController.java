package com.ruoyi.tps.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.tps.domain.TaskOrg;
import com.ruoyi.tps.service.ITaskOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task/org")
public class TaskOrgController {

    @Autowired
    private ITaskOrgService taskOrgService;

    @GetMapping("/listAllSimple")
    public AjaxResult listAllSimple() {
        List<TaskOrg> orgs = taskOrgService.selectTaskOrgList(new TaskOrg());
        List<Map<String, Object>> result = orgs.stream()
                .map(org -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("org_id", org.getOrgId());
                    map.put("org_name", org.getOrgName());
                    map.put("org1_id", org.getOrg1Id());
                    return map;
                })
                .collect(Collectors.toList());
        return AjaxResult.success(result);
    }
}
