package com.ruoyi.tps.service;

import java.util.List;
import com.ruoyi.tps.domain.TaskAttachment;

/**
 * 任务附件Service接口
 * 
 * @author ruoyi
 * @date 2025-08-15
 */
public interface ITaskAttachmentService 
{
    /**
     * 查询任务附件
     * 
     * @param attachmentId 任务附件主键
     * @return 任务附件
     */
    public TaskAttachment selectTaskAttachmentByAttachmentId(Long attachmentId);

    /**
     * 查询任务附件列表
     * 
     * @param taskAttachment 任务附件
     * @return 任务附件集合
     */
    public List<TaskAttachment> selectTaskAttachmentList(TaskAttachment taskAttachment);

    /**
     * 新增任务附件
     * 
     * @param taskAttachment 任务附件
     * @return 结果
     */
    public int insertTaskAttachment(TaskAttachment taskAttachment);

    /**
     * 修改任务附件
     * 
     * @param taskAttachment 任务附件
     * @return 结果
     */
    public int updateTaskAttachment(TaskAttachment taskAttachment);

    /**
     * 批量删除任务附件
     * 
     * @param attachmentIds 需要删除的任务附件主键集合
     * @return 结果
     */
    public int deleteTaskAttachmentByAttachmentIds(Long[] attachmentIds);

    /**
     * 删除任务附件信息
     * 
     * @param attachmentId 任务附件主键
     * @return 结果
     */
    public int deleteTaskAttachmentByAttachmentId(Long attachmentId);
}
