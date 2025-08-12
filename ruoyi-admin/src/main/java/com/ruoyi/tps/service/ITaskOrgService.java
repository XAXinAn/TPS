package com.ruoyi.tps.service;

import com.ruoyi.tps.domain.TaskOrg;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITaskOrgService {
    public List<TaskOrg> selectTaskOrgList(TaskOrg taskOrg);
}
