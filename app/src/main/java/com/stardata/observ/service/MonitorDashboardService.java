package com.stardata.observ.service;

import com.stardata.observ.domain.MonitorQuery;

public interface MonitorDashboardService {
    String getUrl(String appType, MonitorQuery query);
}
