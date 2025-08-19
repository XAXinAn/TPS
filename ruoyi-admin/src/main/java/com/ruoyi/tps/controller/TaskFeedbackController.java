package com.ruoyi.tps.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.tps.domain.TaskFeedback;
import com.ruoyi.tps.service.ITaskFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务反馈Controller
 * 
 * @author ruoyi
 * @date 2025-08-19
 */
@RestController
@RequestMapping("/tps/feedback")
public class TaskFeedbackController extends BaseController
{
    @Autowired
    private ITaskFeedbackService taskFeedbackService;

    /**
     * 查询任务反馈列表
     */
    @PreAuthorize("@ss.hasPermi('tps:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskFeedback taskFeedback)
    {
        startPage();
        List<TaskFeedback> list = taskFeedbackService.selectTaskFeedbackList(taskFeedback);
        return getDataTable(list);
    }

    /**
     * 导出任务反馈列表
     */
    @PreAuthorize("@ss.hasPermi('tps:feedback:export')")
    @Log(title = "任务反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskFeedback taskFeedback)
    {
        List<TaskFeedback> list = taskFeedbackService.selectTaskFeedbackList(taskFeedback);
        ExcelUtil<TaskFeedback> util = new ExcelUtil<TaskFeedback>(TaskFeedback.class);
        util.exportExcel(response, list, "任务反馈数据");
    }

    /**
     * 获取任务反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('tps:feedback:query')")
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId)
    {
        return success(taskFeedbackService.selectTaskFeedbackByFeedbackId(feedbackId));
    }

    /**
     * 新增任务反馈
     */
    @PreAuthorize("@ss.hasPermi('tps:feedback:add')")
    @Log(title = "任务反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskFeedback taskFeedback)
    {
        return toAjax(taskFeedbackService.insertTaskFeedback(taskFeedback));
    }

    /**
     * 修改任务反馈
     */
    @PreAuthorize("@ss.hasPermi('tps:feedback:edit')")
    @Log(title = "任务反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskFeedback taskFeedback)
    {
        return toAjax(taskFeedbackService.updateTaskFeedback(taskFeedback));
    }

    /**
     * 删除任务反馈
     */
    @PreAuthorize("@ss.hasPermi('tps:feedback:remove')")
    @Log(title = "任务反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds)
    {
        return toAjax(taskFeedbackService.deleteTaskFeedbackByFeedbackIds(feedbackIds));
    }
}
