package com.stardata.observ.service;

import com.stardata.observ.common.MonitorType;
import com.stardata.observ.domain.MonitorQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class MonitorDashboardServiceDelegate implements MonitorDashboardService {
    private final MonitorDashboardService delegate;

    public MonitorDashboardServiceDelegate(
            CmdbMonitorDashboardServiceImpl cmdbService,
            GrafanaMonitorDashboardServiceImpl grafanaService,
            SysParameterService parameterService) {
        String value = String.format(CmdbMonitorDashboardServiceImpl.PARAMETER_CONFIG_KEY_FORMAT, MonitorType.JAVA.getValue());
        boolean cmdbEnabled = StringUtils.isNotBlank(parameterService.getStringParameter(value));
        if (cmdbEnabled) {
            this.delegate = cmdbService;
        } else {
            this.delegate = grafanaService;
        }
    }

    @Override
    public String getUrl(String appType, MonitorQuery query) {
        return delegate.getUrl(appType, query);
    }
}
