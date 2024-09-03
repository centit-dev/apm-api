package com.stardata.observ.service;

import com.stardata.observ.domain.MonitorQuery;

import org.springframework.stereotype.Component;

@Component
public class CmdbMonitorDashboardServiceImpl implements MonitorDashboardService {
    static final String PARAMETER_CONFIG_KEY_FORMAT = "cmdb_%s_dashboard";

    @Override
    public String getUrl(String appType, MonitorQuery query) {
        throw new UnsupportedOperationException("CMDB not implemented yet.");
    }
}
