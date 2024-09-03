package com.stardata.observ.service;

import com.google.common.collect.Lists;
import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.config.TestUtil;
import com.stardata.observ.model.ch.SnapshotCondition;
import com.stardata.observ.model.ch.SpanSnapshot;
import com.stardata.observ.vo.InitialCauseAppInstanceListResponse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class TraceFaultServiceIntegrationTest {
    @Autowired
    private TraceFaultService service;

    @Autowired
    private SpanSnapshotService snapshotService;

    @Test
    void testGroupBySnapshotIds() {
        SpanSnapshot snapshot = createSnapshot();

        InitialCauseAppInstanceListResponse response = service.groupByAppInstance(Lists.newArrayList(snapshot.getSnapshotId()));
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    private SpanSnapshot createSnapshot() {
        SnapshotCondition condition = TestUtil.buildSnapshotCondition();
        return snapshotService.createSpanSnapshot("1", null, condition);
    }
}
