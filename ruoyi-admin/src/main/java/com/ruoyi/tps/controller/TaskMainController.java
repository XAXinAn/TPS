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
import com.ruoyi.tps.domain.TaskMain;
import com.ruoyi.tps.service.ITaskMainService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 创建任务Controller
 * 
 * @author ruoyi
 * @date 2025-08-11
 */
@RestController
@RequestMapping("/tps/taskMain")
public class TaskMainController extends BaseController
{
    @Autowired
    private ITaskMainService taskMainService;

    /**
     * 查询创建任务列表
     */
    @PreAuthorize("@ss.hasPermi('tps:taskMain:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskMain taskMain)
    {
        startPage();
        List<TaskMain> list = taskMainService.selectTaskMainList(taskMain);
        return getDataTable(list);
    }

    /**
     * 导出创建任务列表
     */
    @PreAuthorize("@ss.hasPermi('tps:taskMain:export')")
    @Log(title = "创建任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskMain taskMain)
    {
        List<TaskMain> list = taskMainService.selectTaskMainList(taskMain);
        ExcelUtil<TaskMain> util = new ExcelUtil<TaskMain>(TaskMain.class);
        util.exportExcel(response, list, "创建任务数据");
    }

    /**
     * 获取创建任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('tps:taskMain:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") String taskId)
    {
        return success(taskMainService.selectTaskMainByTaskId(taskId));
    }

    /**
     * 新增创建任务
     */
    @PreAuthorize("@ss.hasPermi('tps:taskMain:add')")
    @Log(title = "创建任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskMain taskMain)
    {
        return toAjax(taskMainService.insertTaskMain(taskMain));
    }

    /**
     * 修改创建任务
     */
    @PreAuthorize("@ss.hasPermi('tps:taskMain:edit')")
    @Log(title = "创建任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskMain taskMain)
    {
        return toAjax(taskMainService.updateTaskMain(taskMain));
    }

    /**
     * 删除创建任务
     */
    @PreAuthorize("@ss.hasPermi('tps:taskMain:remove')")
    @Log(title = "创建任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable String[] taskIds)
    {
        return toAjax(taskMainService.deleteTaskMainByTaskIds(taskIds));
    }
}
