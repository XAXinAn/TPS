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
import com.ruoyi.tps.domain.TaskOrgUserAdminConfig;
import com.ruoyi.tps.service.ITaskOrgUserAdminConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 支行管理员配置Controller
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
@RestController
@RequestMapping("/tps/orgConfig")
public class TaskOrgUserAdminConfigController extends BaseController
{
    @Autowired
    private ITaskOrgUserAdminConfigService taskOrgUserAdminConfigService;

    /**
     * 查询支行管理员配置列表
     */
    @PreAuthorize("@ss.hasPermi('tps:orgConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        startPage();
        List<TaskOrgUserAdminConfig> list = taskOrgUserAdminConfigService.selectTaskOrgUserAdminConfigList(taskOrgUserAdminConfig);
        return getDataTable(list);
    }

    /**
     * 导出支行管理员配置列表
     */
    @PreAuthorize("@ss.hasPermi('tps:orgConfig:export')")
    @Log(title = "支行管理员配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        List<TaskOrgUserAdminConfig> list = taskOrgUserAdminConfigService.selectTaskOrgUserAdminConfigList(taskOrgUserAdminConfig);
        ExcelUtil<TaskOrgUserAdminConfig> util = new ExcelUtil<TaskOrgUserAdminConfig>(TaskOrgUserAdminConfig.class);
        util.exportExcel(response, list, "支行管理员配置数据");
    }

    /**
     * 获取支行管理员配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('tps:orgConfig:query')")
    @GetMapping(value = "/{orgUserAdminId}")
    public AjaxResult getInfo(@PathVariable("orgUserAdminId") Long orgUserAdminId)
    {
        return success(taskOrgUserAdminConfigService.selectTaskOrgUserAdminConfigByOrgUserAdminId(orgUserAdminId));
    }

    /**
     * 新增支行管理员配置
     */
    @PreAuthorize("@ss.hasPermi('tps:orgConfig:add')")
    @Log(title = "支行管理员配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        return toAjax(taskOrgUserAdminConfigService.insertTaskOrgUserAdminConfig(taskOrgUserAdminConfig));
    }

    /**
     * 修改支行管理员配置
     */
    @PreAuthorize("@ss.hasPermi('tps:orgConfig:edit')")
    @Log(title = "支行管理员配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskOrgUserAdminConfig taskOrgUserAdminConfig)
    {
        return toAjax(taskOrgUserAdminConfigService.updateTaskOrgUserAdminConfig(taskOrgUserAdminConfig));
    }

    /**
     * 删除支行管理员配置
     */
    @PreAuthorize("@ss.hasPermi('tps:orgConfig:remove')")
    @Log(title = "支行管理员配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orgUserAdminIds}")
    public AjaxResult remove(@PathVariable Long[] orgUserAdminIds)
    {
        return toAjax(taskOrgUserAdminConfigService.deleteTaskOrgUserAdminConfigByOrgUserAdminIds(orgUserAdminIds));
    }
}
