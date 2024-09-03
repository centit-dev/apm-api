package com.stardata.observ.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.stardata.observ.common.MonitorType;
import com.stardata.observ.domain.MonitorQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class GrafanaMonitorDashboardServiceImpl implements MonitorDashboardService {
    private static final String QUERY_MARK = "?";
    private static final String QUERY_AND = "&";
    private static final String PARAMETER_CONFIG_KEY_FORMAT = "grafana_%s_dashboard";

    private final SysParameterService parameterService;
    private final LoadingCache<String, String> cache;

    public GrafanaMonitorDashboardServiceImpl(SysParameterService parameterService) {
        this.parameterService = parameterService;
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(@Nonnull String key) {
                        return getGrafanaUrl(key);
                    }
                });
    }

    @Override
    public String getUrl(String appType, MonitorQuery query) {
        if (appType == null || query == null) {
            return null;
        }
        String prefix = cache.getUnchecked(appType);
        if (StringUtils.isEmpty(prefix)) {
            return null;
        }

        if (prefix.contains(QUERY_MARK)) {
            return prefix + QUERY_AND + query.getQuery();
        } else {
            return prefix + QUERY_MARK + query.getQuery();
        }
    }

    @Nonnull
    private String getGrafanaUrl(String appType) {
        MonitorType type = MonitorType.fromValue(appType);
        if (type == null) {
            return StringUtils.EMPTY;
        }
        String configKey = String.format(PARAMETER_CONFIG_KEY_FORMAT, type.getValue());
        return parameterService.getStringParameter(configKey);
    }
}
