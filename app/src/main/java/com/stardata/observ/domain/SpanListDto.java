package com.stardata.observ.domain;

import java.util.List;

import com.stardata.observ.model.ch.Span;

import lombok.Data;

@Data
public class SpanListDto {
    private List<Span> spans;
    private Long total;
}
