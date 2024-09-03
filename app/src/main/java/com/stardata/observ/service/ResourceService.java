package com.stardata.observ.service;

import java.util.Date;

import com.stardata.observ.common.MonitorType;
import com.stardata.observ.domain.MonitorQuery;
import com.stardata.observ.vo.AppRelatedResource;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ResourceService {
    private final MonitorDashboardService dashboardService;
    private final SoftwareService softwareService;
    private final QueryDictionaryService dictionaryService;

    public AppRelatedResource buildServiceResource(String instanceName, String serviceName, Date from, Date to) {
        MonitorQuery query = new MonitorQuery();
        query.setK8sPodName(instanceName);
        query.setServiceName(serviceName);
        if (from != null) {
            query.setFrom(from.getTime());
        }
        if (to != null) {
            query.setTo(to.getTime());
        }
        String type = MonitorType.SERVICE.getValue();
        return new AppRelatedResource()
                .id(serviceName)
                .name(serviceName)
                .displayName(serviceName)
                .type(type)
                .logo(softwareService.getSoftwareLogo(type))
                .monitorURL(dashboardService.getUrl(type, query));
    }

    public AppRelatedResource buildAppResource(String instanceName, String runtime, Date from, Date to) {
        return buildResource(instanceName, runtime, runtime, from, to);
    }

    public AppRelatedResource buildK8sResource(String instanceName, Date from, Date to) {
        return buildResource(instanceName, instanceName, MonitorType.K8S.getValue(), from, to);
    }

    public AppRelatedResource buildNodeResource(String instanceName, Date from, Date to) {
        MonitorQuery query = new MonitorQuery();
        query.setK8sNodeName(instanceName);
        if (from != null) {
            query.setFrom(from.getTime());
        }
        if (to != null) {
            query.setTo(to.getTime());
        }
        String type = MonitorType.NODE.getValue();
        return new AppRelatedResource()
                .id(instanceName)
                .name(instanceName)
                .displayName(dictionaryService.getDisplayNameByName(instanceName))
                .type(type)
                .logo(softwareService.getSoftwareLogo(type))
                .monitorURL(dashboardService.getUrl(type, query));
    }

    public AppRelatedResource buildServerResource(String hostname, String type, Date from, Date to) {
        MonitorQuery query = new MonitorQuery();
        query.setHostname(hostname);
        if (from != null) {
            query.setFrom(from.getTime());
        }
        if (to != null) {
            query.setTo(to.getTime());
        }

        return new AppRelatedResource()
                .id(hostname)
                .name(hostname)
                .displayName(hostname)
                .type(type)
                .logo(softwareService.getSoftwareLogo(type))
                .monitorURL(dashboardService.getUrl(type, query));
    }

    private AppRelatedResource buildResource(String instanceName, String name, String type, Date from, Date to) {
        MonitorQuery query = new MonitorQuery();
        query.setK8sPodName(instanceName);
        if (from != null) {
            query.setFrom(from.getTime());
        }
        if (to != null) {
            query.setTo(to.getTime());
        }
        return new AppRelatedResource()
                .id(name)
                .name(name)
                .displayName(dictionaryService.getDisplayNameByName(name))
                .type(type)
                .logo(softwareService.getSoftwareLogo(type))
                .monitorURL(dashboardService.getUrl(type, query));
    }
}
