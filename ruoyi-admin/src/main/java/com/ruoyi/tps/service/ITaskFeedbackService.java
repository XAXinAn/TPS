package com.ruoyi.tps.service;

import java.util.List;
import com.ruoyi.tps.domain.TaskFeedback;

/**
 * 任务反馈Service接口
 * 
 * @author ruoyi
 * @date 2025-08-19
 */
public interface ITaskFeedbackService 
{
    /**
     * 查询任务反馈
     * 
     * @param feedbackId 任务反馈主键
     * @return 任务反馈
     */
    public TaskFeedback selectTaskFeedbackByFeedbackId(Long feedbackId);

    /**
     * 查询任务反馈列表
     * 
     * @param taskFeedback 任务反馈
     * @return 任务反馈集合
     */
    public List<TaskFeedback> selectTaskFeedbackList(TaskFeedback taskFeedback);

    /**
     * 新增任务反馈
     * 
     * @param taskFeedback 任务反馈
     * @return 结果
     */
    public int insertTaskFeedback(TaskFeedback taskFeedback);

    /**
     * 修改任务反馈
     * 
     * @param taskFeedback 任务反馈
     * @return 结果
     */
    public int updateTaskFeedback(TaskFeedback taskFeedback);

    /**
     * 批量删除任务反馈
     * 
     * @param feedbackIds 需要删除的任务反馈主键集合
     * @return 结果
     */
    public int deleteTaskFeedbackByFeedbackIds(Long[] feedbackIds);

    /**
     * 删除任务反馈信息
     * 
     * @param feedbackId 任务反馈主键
     * @return 结果
     */
    public int deleteTaskFeedbackByFeedbackId(Long feedbackId);
}
