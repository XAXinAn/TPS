package com.ruoyi.tps.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tps.mapper.TaskAttachmentMapper;
import com.ruoyi.tps.domain.TaskAttachment;
import com.ruoyi.tps.service.ITaskAttachmentService;

/**
 * 任务附件Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-15
 */
@Service
public class TaskAttachmentServiceImpl implements ITaskAttachmentService 
{
    @Autowired
    private TaskAttachmentMapper taskAttachmentMapper;

    /**
     * 查询任务附件
     * 
     * @param attachmentId 任务附件主键
     * @return 任务附件
     */
    @Override
    public TaskAttachment selectTaskAttachmentByAttachmentId(Long attachmentId)
    {
        return taskAttachmentMapper.selectTaskAttachmentByAttachmentId(attachmentId);
    }

    /**
     * 查询任务附件列表
     * 
     * @param taskAttachment 任务附件
     * @return 任务附件
     */
    @Override
    public List<TaskAttachment> selectTaskAttachmentList(TaskAttachment taskAttachment)
    {
        return taskAttachmentMapper.selectTaskAttachmentList(taskAttachment);
    }

    /**
     * 新增任务附件
     * 
     * @param taskAttachment 任务附件
     * @return 结果
     */
    @Override
    public int insertTaskAttachment(TaskAttachment taskAttachment)
    {
        taskAttachment.setCreateTime(DateUtils.getNowDate());
        return taskAttachmentMapper.insertTaskAttachment(taskAttachment);
    }

    /**
     * 修改任务附件
     * 
     * @param taskAttachment 任务附件
     * @return 结果
     */
    @Override
    public int updateTaskAttachment(TaskAttachment taskAttachment)
    {
        return taskAttachmentMapper.updateTaskAttachment(taskAttachment);
    }

    /**
     * 批量删除任务附件
     * 
     * @param attachmentIds 需要删除的任务附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskAttachmentByAttachmentIds(Long[] attachmentIds)
    {
        return taskAttachmentMapper.deleteTaskAttachmentByAttachmentIds(attachmentIds);
    }

    /**
     * 删除任务附件信息
     * 
     * @param attachmentId 任务附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskAttachmentByAttachmentId(Long attachmentId)
    {
        return taskAttachmentMapper.deleteTaskAttachmentByAttachmentId(attachmentId);
    }
}
