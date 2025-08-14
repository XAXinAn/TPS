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
import com.ruoyi.tps.domain.TaskRecipient;
import com.ruoyi.tps.service.ITaskRecipientService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 下发对象Controller
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@RestController
@RequestMapping("/tps/recipient")
public class TaskRecipientController extends BaseController
{
    @Autowired
    private ITaskRecipientService taskRecipientService;

    /**
     * 查询下发对象列表
     */
    @PreAuthorize("@ss.hasPermi('tps:recipient:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskRecipient taskRecipient)
    {
        startPage();
        List<TaskRecipient> list = taskRecipientService.selectTaskRecipientList(taskRecipient);
        return getDataTable(list);
    }

    /**
     * 导出下发对象列表
     */
    @PreAuthorize("@ss.hasPermi('tps:recipient:export')")
    @Log(title = "下发对象", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskRecipient taskRecipient)
    {
        List<TaskRecipient> list = taskRecipientService.selectTaskRecipientList(taskRecipient);
        ExcelUtil<TaskRecipient> util = new ExcelUtil<TaskRecipient>(TaskRecipient.class);
        util.exportExcel(response, list, "下发对象数据");
    }

    /**
     * 获取下发对象详细信息
     */
    @PreAuthorize("@ss.hasPermi('tps:recipient:query')")
    @GetMapping(value = "/{recipientId}")
    public AjaxResult getInfo(@PathVariable("recipientId") Long recipientId)
    {
        return success(taskRecipientService.selectTaskRecipientByRecipientId(recipientId));
    }

    /**
     * 新增下发对象
     */
    @PreAuthorize("@ss.hasPermi('tps:recipient:add')")
    @Log(title = "下发对象", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskRecipient taskRecipient)
    {
        return toAjax(taskRecipientService.insertTaskRecipient(taskRecipient));
    }

    /**
     * 修改下发对象
     */
    @PreAuthorize("@ss.hasPermi('tps:recipient:edit')")
    @Log(title = "下发对象", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskRecipient taskRecipient)
    {
        return toAjax(taskRecipientService.updateTaskRecipient(taskRecipient));
    }

    /**
     * 删除下发对象
     */
    @PreAuthorize("@ss.hasPermi('tps:recipient:remove')")
    @Log(title = "下发对象", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recipientIds}")
    public AjaxResult remove(@PathVariable Long[] recipientIds)
    {
        return toAjax(taskRecipientService.deleteTaskRecipientByRecipientIds(recipientIds));
    }
}
