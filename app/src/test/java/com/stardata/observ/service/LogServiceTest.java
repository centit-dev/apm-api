package com.stardata.observ.service;

import java.util.List;

import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.vo.AppLogDetail;
import com.stardata.observ.vo.AppLogDetailListResponse;

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
public class LogServiceTest {
    @Autowired
    private LogService service;

    @Test
    void testFindLogsBySpan() {
        AppLogDetailListResponse response = service.findLogsBySpan("13cb4f2c07312f2301fb7e4a1fe3db23", "dfbc1899aafd4c05");
        List<AppLogDetail> logs = response.getData();
        assertThat(logs.size(), greaterThan(0));
    }
}
