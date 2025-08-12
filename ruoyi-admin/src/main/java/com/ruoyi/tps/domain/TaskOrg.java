package com.ruoyi.tps.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class TaskOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long orgId;

    private String orgName;

    private String org1Id;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrg1Id() {
        return org1Id;
    }

    public void setOrg1Id(String org1Id) {
        this.org1Id = org1Id;
    }

    @Override
    public String toString() {
        return "TaskOrg{" +
                "orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", org1Id='" + org1Id + '\'' +
                '}';
    }
}