package com.ruoyi.tps.service;

import java.util.List;
import com.ruoyi.tps.domain.TaskMain;

/**
 * 创建任务Service接口
 * 
 * @author ruoyi
 * @date 2025-08-11
 */
public interface ITaskMainService 
{
    /**
     * 查询创建任务
     * 
     * @param taskId 创建任务主键
     * @return 创建任务
     */
    public TaskMain selectTaskMainByTaskId(String taskId);

    /**
     * 查询创建任务列表
     * 
     * @param taskMain 创建任务
     * @return 创建任务集合
     */
    public List<TaskMain> selectTaskMainList(TaskMain taskMain);

    /**
     * 新增创建任务
     * 
     * @param taskMain 创建任务
     * @return 结果
     */
    public int insertTaskMain(TaskMain taskMain);

    /**
     * 修改创建任务
     * 
     * @param taskMain 创建任务
     * @return 结果
     */
    public int updateTaskMain(TaskMain taskMain);

    /**
     * 批量删除创建任务
     * 
     * @param taskIds 需要删除的创建任务主键集合
     * @return 结果
     */
    public int deleteTaskMainByTaskIds(String[] taskIds);

    /**
     * 删除创建任务信息
     * 
     * @param taskId 创建任务主键
     * @return 结果
     */
    public int deleteTaskMainByTaskId(String taskId);
}
