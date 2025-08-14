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
import com.ruoyi.tps.domain.TaskNotice;
import com.ruoyi.tps.service.ITaskNoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务通知Controller
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@RestController
@RequestMapping("/tps/taskNotice")
public class TaskNoticeController extends BaseController
{
    @Autowired
    private ITaskNoticeService taskNoticeService;

    /**
     * 查询任务通知列表
     */
    @PreAuthorize("@ss.hasPermi('tps:taskNotice:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskNotice taskNotice)
    {
        startPage();
        List<TaskNotice> list = taskNoticeService.selectTaskNoticeList(taskNotice);
        return getDataTable(list);
    }

    /**
     * 导出任务通知列表
     */
    @PreAuthorize("@ss.hasPermi('tps:taskNotice:export')")
    @Log(title = "任务通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskNotice taskNotice)
    {
        List<TaskNotice> list = taskNoticeService.selectTaskNoticeList(taskNotice);
        ExcelUtil<TaskNotice> util = new ExcelUtil<TaskNotice>(TaskNotice.class);
        util.exportExcel(response, list, "任务通知数据");
    }

    /**
     * 获取任务通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('tps:taskNotice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return success(taskNoticeService.selectTaskNoticeByNoticeId(noticeId));
    }

    /**
     * 新增任务通知
     */
    @PreAuthorize("@ss.hasPermi('tps:taskNotice:add')")
    @Log(title = "任务通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskNotice taskNotice)
    {
        return toAjax(taskNoticeService.insertTaskNotice(taskNotice));
    }

    /**
     * 修改任务通知
     */
    @PreAuthorize("@ss.hasPermi('tps:taskNotice:edit')")
    @Log(title = "任务通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskNotice taskNotice)
    {
        return toAjax(taskNoticeService.updateTaskNotice(taskNotice));
    }

    /**
     * 删除任务通知
     */
    @PreAuthorize("@ss.hasPermi('tps:taskNotice:remove')")
    @Log(title = "任务通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(taskNoticeService.deleteTaskNoticeByNoticeIds(noticeIds));
    }
}
