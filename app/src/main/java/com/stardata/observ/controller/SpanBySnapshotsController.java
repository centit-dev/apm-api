package com.stardata.observ.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.stardata.observ.vo.FieldCondition;
import com.stardata.observ.api.SpansBySnapshotsApi;
import com.stardata.observ.common.Resources;
import com.stardata.observ.service.SpanService;
import com.stardata.observ.vo.SpanListResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpanBySnapshotsController implements SpansBySnapshotsApi {
    private final SpanService service;
    private final Gson gson;
    private final Type type = new TypeToken<List<FieldCondition>>() {}.getType();

    /**
     * @deprecated prefer {@code SpansApi#getSpans(List, String, Integer, Integer, Integer, Integer, Integer, Integer)}
     */
    @Deprecated
    @Override
    public ResponseEntity<SpanListResponse> querySpanBySnapshots(
            @NotNull @Valid List<String> spanSnapshotIds,
            @Valid String conditionStrings,
            @Valid Integer pageNo,
            @Valid Integer pageSize) {
        List<FieldCondition> conditions;
        try {
            conditions = gson.fromJson(conditionStrings, type);
        } catch (JsonSyntaxException | JsonIOException e) {
            return Resources.with("按条件查询Span")
                    .onError(HttpStatus.BAD_REQUEST)
                    .execute(() -> new SpanListResponse().code(-1).message("条件格式错误"));
        }

        return Resources.with("按条件查询Span")
                .onSuccess(HttpStatus.OK)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.FORBIDDEN)
                .execute(() -> service.findSpans(spanSnapshotIds, conditions, pageNo, pageSize));
    }
}
