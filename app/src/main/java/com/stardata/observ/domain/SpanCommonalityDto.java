package com.stardata.observ.domain;

import com.stardata.observ.vo.SpanCommonality;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpanCommonalityDto extends SpanCommonality {
    private Double value;
}
