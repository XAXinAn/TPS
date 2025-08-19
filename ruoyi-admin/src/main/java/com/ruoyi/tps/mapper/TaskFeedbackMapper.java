package com.ruoyi.tps.mapper;

import java.util.List;
import com.ruoyi.tps.domain.TaskFeedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务反馈Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-19
 */
@Mapper
public interface TaskFeedbackMapper 
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
     * 删除任务反馈
     * 
     * @param feedbackId 任务反馈主键
     * @return 结果
     */
    public int deleteTaskFeedbackByFeedbackId(Long feedbackId);

    /**
     * 批量删除任务反馈
     * 
     * @param feedbackIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskFeedbackByFeedbackIds(Long[] feedbackIds);
}
