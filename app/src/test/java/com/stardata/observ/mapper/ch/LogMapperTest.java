package com.stardata.observ.mapper.ch;

import java.util.Collections;
import java.util.List;

import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.model.ch.Log;
import com.stardata.observ.model.ch.LogCount;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class LogMapperTest {

    @Autowired
    private LogMapper mapper;

    @Test
    void testFindLogByTraceId() {
        List<Log> logs = mapper.findLogByTraceId("424208c8076a6c2196b3d60241ded81a");
        assertThat(logs.size(), greaterThan(0));
    }

    @Test
    void testCountLogsBySpanIds() {
        List<LogCount> counts = mapper.countLogsBySpanIds(
            Collections.singleton("432b94abdb69d391693fcb94d2b45353"),
            Collections.singleton("851f95313a6df205"));
        assertThat(counts.size(), greaterThan(0));
        assertThat(counts.get(0).getCount(), greaterThan(0));
    }

}
