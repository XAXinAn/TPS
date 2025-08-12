package com.ruoyi.tps.service.impl;

import com.ruoyi.tps.domain.TaskOrg;
import com.ruoyi.tps.mapper.TaskOrgMapper;
import com.ruoyi.tps.service.ITaskOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskOrgServiceImpl implements ITaskOrgService {

    @Autowired
    private TaskOrgMapper taskOrgMapper;

    @Override
    public List<TaskOrg> selectTaskOrgList(TaskOrg taskOrg) {
        return taskOrgMapper.selectTaskOrgList(taskOrg);
    }
}

