package com.stardata.observ.service;

import com.stardata.observ.config.CoreTestConfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class QueryDictionaryServiceITest {
    @Autowired
    private QueryDictionaryService service;

    @BeforeEach
    void setUp() {
        service.init();
    }

    @Test
    void testGetDisplayNameByName() {
        String displayName = service.getDisplayNameByName("ResourceAttributes['k8s.pod.name']");
        assertEquals("KubernetesPod名称", displayName);

        displayName = service.getDisplayNameByName("ResourceAttributes['service.platform']");
        assertEquals("服务平台", displayName);

        displayName = service.getDisplayNameByName("SpanAttributes['db.system']");
        assertEquals("数据库系统", displayName);

        displayName = service.getDisplayNameByName("SpanAttributes['fault.kind']");
        assertEquals("故障类型", displayName);
    }
}
