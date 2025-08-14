package com.ruoyi.tps.service;

import java.util.List;
import com.ruoyi.tps.domain.TaskNotice;

/**
 * 任务通知Service接口
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
public interface ITaskNoticeService 
{
    /**
     * 查询任务通知
     * 
     * @param noticeId 任务通知主键
     * @return 任务通知
     */
    public TaskNotice selectTaskNoticeByNoticeId(Long noticeId);

    /**
     * 查询任务通知列表
     * 
     * @param taskNotice 任务通知
     * @return 任务通知集合
     */
    public List<TaskNotice> selectTaskNoticeList(TaskNotice taskNotice);

    /**
     * 新增任务通知
     * 
     * @param taskNotice 任务通知
     * @return 结果
     */
    public int insertTaskNotice(TaskNotice taskNotice);

    /**
     * 修改任务通知
     * 
     * @param taskNotice 任务通知
     * @return 结果
     */
    public int updateTaskNotice(TaskNotice taskNotice);

    /**
     * 批量删除任务通知
     * 
     * @param noticeIds 需要删除的任务通知主键集合
     * @return 结果
     */
    public int deleteTaskNoticeByNoticeIds(Long[] noticeIds);

    /**
     * 删除任务通知信息
     * 
     * @param noticeId 任务通知主键
     * @return 结果
     */
    public int deleteTaskNoticeByNoticeId(Long noticeId);
}
