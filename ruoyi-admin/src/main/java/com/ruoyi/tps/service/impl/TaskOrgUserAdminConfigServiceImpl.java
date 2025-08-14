package com.ruoyi.tps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tps.mapper.TaskOrgUserAdminConfigMapper;
import com.ruoyi.tps.domain.TaskOrgUserAdminConfig;
import com.ruoyi.tps.service.ITaskOrgUserAdminConfigService;

/**
 * 支行管理员配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@Service
public class TaskOrgUserAdminConfigServiceImpl implements ITaskOrgUserAdminConfigService 
{
    @Autowired
    private TaskOrgUserAdminConfigMapper taskOrgUserAdminConfigMapper;

    /**
     * 查询支行管理员配置
     * 
     * @param orgUserAdminId 支行管理员配置主键
     * @return 支行管理员配置
     */
    @Override
    public TaskOrgUserAdminConfig selectTaskOrgUserAdminConfigByOrgUserAdminId(Long orgUserAdminId)
    {
        return taskOrgUserAdminConfigMapper.selectTaskOrgUserAdminConfigByOrgUserAdminId(orgUserAdminId);
    }

    /**
     * 查询支行管理员配置列表
     * 
     * @param taskOrgUserAdminConfig 支行管理员配置
     * @return 支行管理员配置
     */
    @Override
    public List<TaskOrgUserAdminConfig> selectTaskOrgUserAdminConfigList(TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        return taskOrgUserAdminConfigMapper.selectTaskOrgUserAdminConfigList(taskOrgUserAdminConfig);
    }

    /**
     * 新增支行管理员配置
     * 
     * @param taskOrgUserAdminConfig 支行管理员配置
     * @return 结果
     */
    @Override
    public int insertTaskOrgUserAdminConfig(TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        return taskOrgUserAdminConfigMapper.insertTaskOrgUserAdminConfig(taskOrgUserAdminConfig);
    }

    /**
     * 修改支行管理员配置
     * 
     * @param taskOrgUserAdminConfig 支行管理员配置
     * @return 结果
     */
    @Override
    public int updateTaskOrgUserAdminConfig(TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        return taskOrgUserAdminConfigMapper.updateTaskOrgUserAdminConfig(taskOrgUserAdminConfig);
    }

    /**
     * 批量删除支行管理员配置
     * 
     * @param orgUserAdminIds 需要删除的支行管理员配置主键
     * @return 结果
     */
    @Override
    public int deleteTaskOrgUserAdminConfigByOrgUserAdminIds(Long[] orgUserAdminIds)
    {
        return taskOrgUserAdminConfigMapper.deleteTaskOrgUserAdminConfigByOrgUserAdminIds(orgUserAdminIds);
    }

    /**
     * 删除支行管理员配置信息
     * 
     * @param orgUserAdminId 支行管理员配置主键
     * @return 结果
     */
    @Override
    public int deleteTaskOrgUserAdminConfigByOrgUserAdminId(Long orgUserAdminId)
    {
        return taskOrgUserAdminConfigMapper.deleteTaskOrgUserAdminConfigByOrgUserAdminId(orgUserAdminId);
    }
}
