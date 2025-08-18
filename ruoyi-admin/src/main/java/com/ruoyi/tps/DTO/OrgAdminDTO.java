package com.ruoyi.tps.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrgAdminDTO {
    private String title;

    private String description;

    private LocalDate deadline;

    private Integer recipientType;

    private List<Long> recipients;

    private Integer priority;

    private Boolean confirm;

    private String attachment;
}
