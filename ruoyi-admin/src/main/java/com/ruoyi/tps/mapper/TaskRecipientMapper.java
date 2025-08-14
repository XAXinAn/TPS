package com.ruoyi.tps.mapper;

import java.util.List;
import com.ruoyi.tps.domain.TaskRecipient;
import org.apache.ibatis.annotations.Mapper;

/**
 * 下发对象Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@Mapper
public interface TaskRecipientMapper 
{
    /**
     * 查询下发对象
     * 
     * @param recipientId 下发对象主键
     * @return 下发对象
     */
    public TaskRecipient selectTaskRecipientByRecipientId(Long recipientId);

    /**
     * 查询下发对象列表
     * 
     * @param taskRecipient 下发对象
     * @return 下发对象集合
     */
    public List<TaskRecipient> selectTaskRecipientList(TaskRecipient taskRecipient);

    /**
     * 新增下发对象
     * 
     * @param taskRecipient 下发对象
     * @return 结果
     */
    public int insertTaskRecipient(TaskRecipient taskRecipient);

    /**
     * 修改下发对象
     * 
     * @param taskRecipient 下发对象
     * @return 结果
     */
    public int updateTaskRecipient(TaskRecipient taskRecipient);

    /**
     * 删除下发对象
     * 
     * @param recipientId 下发对象主键
     * @return 结果
     */
    public int deleteTaskRecipientByRecipientId(Long recipientId);

    /**
     * 批量删除下发对象
     * 
     * @param recipientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskRecipientByRecipientIds(Long[] recipientIds);
}
