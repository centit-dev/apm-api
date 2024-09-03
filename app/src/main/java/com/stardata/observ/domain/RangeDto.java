package com.stardata.observ.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RangeDto {
    public static final RangeDto EMPTY = new RangeDto(null, null);

    private Long min;
    private Long max;

    public RangeDto intersect(RangeDto other) {
        Long min = null;
        if (this.min == null) {
            min = other.min;
        } else if (other.min != null) {
            min = Math.max(this.min, other.min);
        }

        Long max = null;
        if (this.max == null) {
            max = other.max;
        } else if (other.max != null) {
            max = Math.min(this.max, other.max);
        }
        return new RangeDto(min, max);
    }
}
