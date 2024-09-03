package com.stardata.observ.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.stardata.observ.vo.FieldCondition;
import com.stardata.observ.common.ApplicationValidationException;
import com.stardata.observ.config.WhereGeneratorProperties;
import com.stardata.observ.config.WhereGeneratorProperties.WhereConfig;
import com.stardata.observ.model.ch.SnapshotCondition.SnapshotFieldCondition;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2023/12/26 23:23
 */
@EnableConfigurationProperties(WhereGeneratorProperties.class)
@Component
public class CHWhereHelper {
    private final Map<String, WhereGenerator> whereGenerators;
    private final WhereGenerator emptyGenerator;

    public CHWhereHelper(
            List<WhereGenerator> whereGenerators,
            EmptyWhereGenerator emptyGenerator,
            WhereGeneratorProperties whereGeneratorProperties) {
        this.whereGenerators = new HashMap<>();
        Map<String, String> properties = whereGeneratorProperties.getClickhouse().stream()
                .collect(Collectors.toMap(WhereConfig::getGenerator, WhereConfig::getOperator));
        whereGenerators.forEach(generator -> {
            String generatorName = generator.getClass().getName();
            String operator = properties.get(generatorName);
            if (StringUtils.isBlank(operator)) {
                return;
            }
            this.whereGenerators.put(operator, generator);
        });
        this.emptyGenerator = emptyGenerator;
    }

    public String generateWhereString(List<FieldCondition> fieldConditions) {
        if (CollectionUtils.isEmpty(fieldConditions)) return StringUtils.EMPTY;

        List<SnapshotFieldCondition> conditions = fieldConditions.stream()
                .map(condition -> new SnapshotFieldCondition(condition.getName(), condition.getOperator(), condition.getValue()))
                .collect(Collectors.toList());
        return generateWhereStringFromSnapshot(conditions);
    }

    public String generateWhereStringFromSnapshot(List<SnapshotFieldCondition> fieldConditions) {
        if (CollectionUtils.isEmpty(fieldConditions)) return StringUtils.EMPTY;

        return fieldConditions.stream()
                .map(condition -> {
                    if (StringUtils.isBlank(condition.getField())) {
                        throw new ApplicationValidationException("The field of the condition is empty.");
                    }
                    WhereGenerator generator = whereGenerators.getOrDefault(condition.getOperator(), emptyGenerator);
                    return generator.generate(condition);
                })
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" AND "));
    }
}
