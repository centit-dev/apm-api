package com.stardata.observ.mapper.pg;

import com.stardata.observ.config.CoreTestConfiguration;
import com.stardata.observ.config.TestUtil;
import com.stardata.observ.model.pg.UserQueryTemplate;
import com.stardata.observ.vo.QueryForType;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

@Disabled("for integration test")
@ActiveProfiles("container")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = CoreTestConfiguration.class)
public class UserQueryTemplateMapperTest {
    @Autowired private UserQueryTemplateMapper mapper;

    @Test
    void testCrud() {
        // given a user query template
        UserQueryTemplate template = new UserQueryTemplate();
        template.setUserId("1");
        template.setName("user#" + current().nextLong(1_000_000));
        template.setType(QueryForType.TRACE.getValue());
        template.setIsValid(1);
        template.setConditions(TestUtil.buildSnapshotCondition());

        // when create it
        mapper.insert(template);

        // then it is created
        assertNotNull(template.getId());
        assertNotNull(template.getCreateTime());
        assertNotNull(template.getUpdateTime());

        // when find it
        UserQueryTemplate actual = mapper.selectById(template.getId());

        // then it is found
        assertNotNull(actual);
        assertNotSame(template, actual);

        // when update it
        actual.setName("user#" + current().nextLong(1_000_000));
        actual.setConditions(TestUtil.buildSnapshotCondition());
        mapper.updateById(actual);

        // then it is updated
        UserQueryTemplate updated = mapper.selectById(actual.getId());
        assertEquals(actual.getName(), updated.getName());
        assertEquals(actual.getConditions(), updated.getConditions());
        assertNotEquals(template.getName(), actual.getName());

        // when delete it
        mapper.deleteById(template.getId());

        // then it is deleted
        assertNull(mapper.selectById(template.getId()));
    }
}
