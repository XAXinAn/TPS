package com.ruoyi.tps.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tps.mapper.TaskNoticeMapper;
import com.ruoyi.tps.domain.TaskNotice;
import com.ruoyi.tps.service.ITaskNoticeService;

/**
 * 任务通知Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@Service
public class TaskNoticeServiceImpl implements ITaskNoticeService 
{
    @Autowired
    private TaskNoticeMapper taskNoticeMapper;

    /**
     * 查询任务通知
     * 
     * @param noticeId 任务通知主键
     * @return 任务通知
     */
    @Override
    public TaskNotice selectTaskNoticeByNoticeId(Long noticeId)
    {
        return taskNoticeMapper.selectTaskNoticeByNoticeId(noticeId);
    }

    /**
     * 查询任务通知列表
     * 
     * @param taskNotice 任务通知
     * @return 任务通知
     */
    @Override
    public List<TaskNotice> selectTaskNoticeList(TaskNotice taskNotice)
    {
        return taskNoticeMapper.selectTaskNoticeList(taskNotice);
    }

    /**
     * 新增任务通知
     * 
     * @param taskNotice 任务通知
     * @return 结果
     */
    @Override
    public int insertTaskNotice(TaskNotice taskNotice)
    {
        taskNotice.setCreateTime(DateUtils.getNowDate());
        return taskNoticeMapper.insertTaskNotice(taskNotice);
    }

    /**
     * 修改任务通知
     * 
     * @param taskNotice 任务通知
     * @return 结果
     */
    @Override
    public int updateTaskNotice(TaskNotice taskNotice)
    {
        return taskNoticeMapper.updateTaskNotice(taskNotice);
    }

    /**
     * 批量删除任务通知
     * 
     * @param noticeIds 需要删除的任务通知主键
     * @return 结果
     */
    @Override
    public int deleteTaskNoticeByNoticeIds(Long[] noticeIds)
    {
        return taskNoticeMapper.deleteTaskNoticeByNoticeIds(noticeIds);
    }

    /**
     * 删除任务通知信息
     * 
     * @param noticeId 任务通知主键
     * @return 结果
     */
    @Override
    public int deleteTaskNoticeByNoticeId(Long noticeId)
    {
        return taskNoticeMapper.deleteTaskNoticeByNoticeId(noticeId);
    }
}
