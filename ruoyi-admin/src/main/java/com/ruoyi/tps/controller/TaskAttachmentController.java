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
import com.ruoyi.tps.domain.TaskAttachment;
import com.ruoyi.tps.service.ITaskAttachmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务附件Controller
 * 
 * @author ruoyi
 * @date 2025-08-15
 */
@RestController
@RequestMapping("/tps/attachment")
public class TaskAttachmentController extends BaseController
{
    @Autowired
    private ITaskAttachmentService taskAttachmentService;

    /**
     * 查询任务附件列表
     */
    @PreAuthorize("@ss.hasPermi('tps:attachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskAttachment taskAttachment)
    {
        startPage();
        List<TaskAttachment> list = taskAttachmentService.selectTaskAttachmentList(taskAttachment);
        return getDataTable(list);
    }

    /**
     * 导出任务附件列表
     */
    @PreAuthorize("@ss.hasPermi('tps:attachment:export')")
    @Log(title = "任务附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskAttachment taskAttachment)
    {
        List<TaskAttachment> list = taskAttachmentService.selectTaskAttachmentList(taskAttachment);
        ExcelUtil<TaskAttachment> util = new ExcelUtil<TaskAttachment>(TaskAttachment.class);
        util.exportExcel(response, list, "任务附件数据");
    }

    /**
     * 获取任务附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('tps:attachment:query')")
    @GetMapping(value = "/{attachmentId}")
    public AjaxResult getInfo(@PathVariable("attachmentId") Long attachmentId)
    {
        return success(taskAttachmentService.selectTaskAttachmentByAttachmentId(attachmentId));
    }

    /**
     * 新增任务附件
     */
    @PreAuthorize("@ss.hasPermi('tps:attachment:add')")
    @Log(title = "任务附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskAttachment taskAttachment)
    {
        return toAjax(taskAttachmentService.insertTaskAttachment(taskAttachment));
    }

    /**
     * 修改任务附件
     */
    @PreAuthorize("@ss.hasPermi('tps:attachment:edit')")
    @Log(title = "任务附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskAttachment taskAttachment)
    {
        return toAjax(taskAttachmentService.updateTaskAttachment(taskAttachment));
    }

    /**
     * 删除任务附件
     */
    @PreAuthorize("@ss.hasPermi('tps:attachment:remove')")
    @Log(title = "任务附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attachmentIds}")
    public AjaxResult remove(@PathVariable Long[] attachmentIds)
    {
        return toAjax(taskAttachmentService.deleteTaskAttachmentByAttachmentIds(attachmentIds));
    }
}
