package com.ruoyi.tps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tps.mapper.TaskRecipientMapper;
import com.ruoyi.tps.domain.TaskRecipient;
import com.ruoyi.tps.service.ITaskRecipientService;

/**
 * 下发对象Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@Service
public class TaskRecipientServiceImpl implements ITaskRecipientService 
{
    @Autowired
    private TaskRecipientMapper taskRecipientMapper;

    /**
     * 查询下发对象
     * 
     * @param recipientId 下发对象主键
     * @return 下发对象
     */
    @Override
    public TaskRecipient selectTaskRecipientByRecipientId(Long recipientId)
    {
        return taskRecipientMapper.selectTaskRecipientByRecipientId(recipientId);
    }

    /**
     * 查询下发对象列表
     * 
     * @param taskRecipient 下发对象
     * @return 下发对象
     */
    @Override
    public List<TaskRecipient> selectTaskRecipientList(TaskRecipient taskRecipient)
    {
        return taskRecipientMapper.selectTaskRecipientList(taskRecipient);
    }

    /**
     * 新增下发对象
     * 
     * @param taskRecipient 下发对象
     * @return 结果
     */
    @Override
    public int insertTaskRecipient(TaskRecipient taskRecipient)
    {
        return taskRecipientMapper.insertTaskRecipient(taskRecipient);
    }

    /**
     * 修改下发对象
     * 
     * @param taskRecipient 下发对象
     * @return 结果
     */
    @Override
    public int updateTaskRecipient(TaskRecipient taskRecipient)
    {
        return taskRecipientMapper.updateTaskRecipient(taskRecipient);
    }

    /**
     * 批量删除下发对象
     * 
     * @param recipientIds 需要删除的下发对象主键
     * @return 结果
     */
    @Override
    public int deleteTaskRecipientByRecipientIds(Long[] recipientIds)
    {
        return taskRecipientMapper.deleteTaskRecipientByRecipientIds(recipientIds);
    }

    /**
     * 删除下发对象信息
     * 
     * @param recipientId 下发对象主键
     * @return 结果
     */
    @Override
    public int deleteTaskRecipientByRecipientId(Long recipientId)
    {
        return taskRecipientMapper.deleteTaskRecipientByRecipientId(recipientId);
    }
}
