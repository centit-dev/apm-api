package com.stardata.observ.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

import org.apache.commons.math3.stat.regression.SimpleRegression;

@Data
public class FaultTrendRateDto {
    public enum Trending {
        UP("up"), DOWN("down");

        private static final Map<String, Trending> values = Stream.of(values())
                .collect(Collectors.toMap(Trending::getValue, Function.identity()));

        private final String value;

        Trending(String value) {
            this.value = value;
        }

        public static Trending fromValue(String value) {
            return values.get(value);
        }

        public String getValue() {
            return value;
        }
    }

    private SimpleRegression regression;

    public FaultTrendRateDto addRate(Float errorRate) {
        if (errorRate == null) {
            return this;
        }
        if (regression == null) {
            regression = new SimpleRegression();
        }
        regression.addData(regression.getN(), errorRate);
        return this;
    }

    public boolean matchTrending(Trending trending) {
        if (trending == null) {
            return false;
        }
        if (regression == null) {
            return false;
        }
        double slope = regression.getSlope();
        return Trending.UP == trending ? slope > 0 : slope < 0;
    }
}
