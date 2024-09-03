package com.stardata.observ.mapper.ch;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.stardata.observ.config.CoreTestConfiguration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class SpanMapperTest {
    @Autowired private SpanMapper spanMapper;

    @Test
    void testFindServerSoftwaresByServerHostnameSuccessToHandleEmptyResults() {
        Instant now = Instant.now();
        List<String> softwares = spanMapper.findServerSoftwaresByServerHostname(
                "starshop-online-local",
                "non-exists",
                now.minus(1, ChronoUnit.HOURS).toEpochMilli(), now.toEpochMilli());
        assertThat(softwares, empty());
    }
}
