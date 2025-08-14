package com.ruoyi.tps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支行管理员配置对象 task_org_user_admin_config
 * 
 * @author ruoyi
 * @date 2025-08-14
 */
public class TaskOrgUserAdminConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 管理员配置唯一标识（主键） */
    private Long orgUserAdminId;

    /** 支行ID */
    @Excel(name = "支行ID")
    private Long orgId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public void setOrgUserAdminId(Long orgUserAdminId) 
    {
        this.orgUserAdminId = orgUserAdminId;
    }

    public Long getOrgUserAdminId() 
    {
        return orgUserAdminId;
    }

    public void setOrgId(Long orgId) 
    {
        this.orgId = orgId;
    }

    public Long getOrgId() 
    {
        return orgId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orgUserAdminId", getOrgUserAdminId())
            .append("orgId", getOrgId())
            .append("userId", getUserId())
            .toString();
    }
}
