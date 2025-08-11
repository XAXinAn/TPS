package com.ruoyi.tps.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tps.mapper.TaskMainMapper;
import com.ruoyi.tps.domain.TaskMain;
import com.ruoyi.tps.service.ITaskMainService;

/**
 * 创建任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-11
 */
@Service
public class TaskMainServiceImpl implements ITaskMainService 
{
    @Autowired
    private TaskMainMapper taskMainMapper;

    /**
     * 查询创建任务
     * 
     * @param taskId 创建任务主键
     * @return 创建任务
     */
    @Override
    public TaskMain selectTaskMainByTaskId(String taskId)
    {
        return taskMainMapper.selectTaskMainByTaskId(taskId);
    }

    /**
     * 查询创建任务列表
     * 
     * @param taskMain 创建任务
     * @return 创建任务
     */
    @Override
    public List<TaskMain> selectTaskMainList(TaskMain taskMain)
    {
        return taskMainMapper.selectTaskMainList(taskMain);
    }

    /**
     * 新增创建任务
     * 
     * @param taskMain 创建任务
     * @return 结果
     */
    @Override
    public int insertTaskMain(TaskMain taskMain)
    {
        taskMain.setCreateTime(DateUtils.getNowDate());
        return taskMainMapper.insertTaskMain(taskMain);
    }

    /**
     * 修改创建任务
     * 
     * @param taskMain 创建任务
     * @return 结果
     */
    @Override
    public int updateTaskMain(TaskMain taskMain)
    {
        taskMain.setUpdateTime(DateUtils.getNowDate());
        return taskMainMapper.updateTaskMain(taskMain);
    }

    /**
     * 批量删除创建任务
     * 
     * @param taskIds 需要删除的创建任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskMainByTaskIds(String[] taskIds)
    {
        return taskMainMapper.deleteTaskMainByTaskIds(taskIds);
    }

    /**
     * 删除创建任务信息
     * 
     * @param taskId 创建任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskMainByTaskId(String taskId)
    {
        return taskMainMapper.deleteTaskMainByTaskId(taskId);
    }
}
