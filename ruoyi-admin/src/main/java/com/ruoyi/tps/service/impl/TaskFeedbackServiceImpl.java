package com.ruoyi.tps.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tps.mapper.TaskFeedbackMapper;
import com.ruoyi.tps.domain.TaskFeedback;
import com.ruoyi.tps.service.ITaskFeedbackService;

/**
 * 任务反馈Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-19
 */
@Service
public class TaskFeedbackServiceImpl implements ITaskFeedbackService 
{
    @Autowired
    private TaskFeedbackMapper taskFeedbackMapper;

    /**
     * 查询任务反馈
     * 
     * @param feedbackId 任务反馈主键
     * @return 任务反馈
     */
    @Override
    public TaskFeedback selectTaskFeedbackByFeedbackId(Long feedbackId)
    {
        return taskFeedbackMapper.selectTaskFeedbackByFeedbackId(feedbackId);
    }

    /**
     * 查询任务反馈列表
     * 
     * @param taskFeedback 任务反馈
     * @return 任务反馈
     */
    @Override
    public List<TaskFeedback> selectTaskFeedbackList(TaskFeedback taskFeedback)
    {
        return taskFeedbackMapper.selectTaskFeedbackList(taskFeedback);
    }

    /**
     * 新增任务反馈
     * 
     * @param taskFeedback 任务反馈
     * @return 结果
     */
    @Override
    public int insertTaskFeedback(TaskFeedback taskFeedback)
    {
        taskFeedback.setCreateTime(DateUtils.getNowDate());
        return taskFeedbackMapper.insertTaskFeedback(taskFeedback);
    }

    /**
     * 修改任务反馈
     * 
     * @param taskFeedback 任务反馈
     * @return 结果
     */
    @Override
    public int updateTaskFeedback(TaskFeedback taskFeedback)
    {
        return taskFeedbackMapper.updateTaskFeedback(taskFeedback);
    }

    /**
     * 批量删除任务反馈
     * 
     * @param feedbackIds 需要删除的任务反馈主键
     * @return 结果
     */
    @Override
    public int deleteTaskFeedbackByFeedbackIds(Long[] feedbackIds)
    {
        return taskFeedbackMapper.deleteTaskFeedbackByFeedbackIds(feedbackIds);
    }

    /**
     * 删除任务反馈信息
     * 
     * @param feedbackId 任务反馈主键
     * @return 结果
     */
    @Override
    public int deleteTaskFeedbackByFeedbackId(Long feedbackId)
    {
        return taskFeedbackMapper.deleteTaskFeedbackByFeedbackId(feedbackId);
    }
}
