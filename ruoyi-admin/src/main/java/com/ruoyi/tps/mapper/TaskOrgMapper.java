package com.ruoyi.tps.mapper;

import com.ruoyi.tps.domain.TaskOrg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskOrgMapper {
    List<TaskOrg> selectTaskOrgList(TaskOrg taskOrg);
}
