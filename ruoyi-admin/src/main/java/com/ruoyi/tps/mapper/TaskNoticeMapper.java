package com.ruoyi.tps.mapper;

import java.util.List;
import com.ruoyi.tps.domain.TaskNotice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务通知Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@Mapper
public interface TaskNoticeMapper 
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
     * 删除任务通知
     * 
     * @param noticeId 任务通知主键
     * @return 结果
     */
    public int deleteTaskNoticeByNoticeId(Long noticeId);

    /**
     * 批量删除任务通知
     * 
     * @param noticeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskNoticeByNoticeIds(Long[] noticeIds);
}
