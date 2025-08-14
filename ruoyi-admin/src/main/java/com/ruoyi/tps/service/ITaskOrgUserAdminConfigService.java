package com.ruoyi.tps.service;

import java.util.List;
import com.ruoyi.tps.domain.TaskOrgUserAdminConfig;

/**
 * 支行管理员配置Service接口
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
public interface ITaskOrgUserAdminConfigService 
{
    /**
     * 查询支行管理员配置
     * 
     * @param orgUserAdminId 支行管理员配置主键
     * @return 支行管理员配置
     */
    public TaskOrgUserAdminConfig selectTaskOrgUserAdminConfigByOrgUserAdminId(Long orgUserAdminId);

    /**
     * 查询支行管理员配置列表
     * 
     * @param taskOrgUserAdminConfig 支行管理员配置
     * @return 支行管理员配置集合
     */
    public List<TaskOrgUserAdminConfig> selectTaskOrgUserAdminConfigList(TaskOrgUserAdminConfig taskOrgUserAdminConfig);

    /**
     * 新增支行管理员配置
     * 
     * @param taskOrgUserAdminConfig 支行管理员配置
     * @return 结果
     */
    public int insertTaskOrgUserAdminConfig(TaskOrgUserAdminConfig taskOrgUserAdminConfig);

    /**
     * 修改支行管理员配置
     * 
     * @param taskOrgUserAdminConfig 支行管理员配置
     * @return 结果
     */
    public int updateTaskOrgUserAdminConfig(TaskOrgUserAdminConfig taskOrgUserAdminConfig);

    /**
     * 批量删除支行管理员配置
     * 
     * @param orgUserAdminIds 需要删除的支行管理员配置主键集合
     * @return 结果
     */
    public int deleteTaskOrgUserAdminConfigByOrgUserAdminIds(Long[] orgUserAdminIds);

    /**
     * 删除支行管理员配置信息
     * 
     * @param orgUserAdminId 支行管理员配置主键
     * @return 结果
     */
    public int deleteTaskOrgUserAdminConfigByOrgUserAdminId(Long orgUserAdminId);
}
