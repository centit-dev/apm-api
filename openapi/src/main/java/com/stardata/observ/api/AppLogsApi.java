/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.2.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.stardata.observ.api;

import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.stardata.observ.vo.AppLogListResponse;
import com.stardata.observ.vo.QueryConditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "故障定位/日志异常定位", description = "the 故障定位/日志异常定位 API")
public interface AppLogsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /app-logs : 查询Log列表
     * 根据log快照ID查询出该log快照中的所有log信息列表，用分页表示  20240208，根据新产品原型，新增该接口  20240222，根据讨论结果，改为使用通用查询条件查询
     *
     * @param pageNo          当前页号 (optional)
     * @param pageSize        每页记录数 (optional)
     * @param queryConditions (optional)
     * @return 成功 (status code 200)
     */
    @Operation(
            operationId = "listAppLogs",
            summary = "查询Log列表",
            description = "根据log快照ID查询出该log快照中的所有log信息列表，用分页表示  20240208，根据新产品原型，新增该接口  20240222，根据讨论结果，改为使用通用查询条件查询",
            tags = {"故障定位/日志异常定位", "20240208"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AppLogListResponse.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/app-logs",
            produces = {"application/json"},
            consumes = {"application/json"}
    )

    default ResponseEntity<AppLogListResponse> listAppLogs(
            @Parameter(name = "pageNo", description = "当前页号", in = ParameterIn.QUERY) @Valid @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @Parameter(name = "pageSize", description = "每页记录数", in = ParameterIn.QUERY) @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @Parameter(name = "QueryConditions", description = "") @Valid @RequestBody(required = false) QueryConditions queryConditions
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"data\" : { \"pageCount\" : 5, \"pageNo\" : 6, \"pageSize\" : 1, \"totalCount\" : 5, \"logs\" : [ { \"traceId\" : \"traceId\", \"spanId\" : \"spanId\", \"cluster\" : \"cluster\", \"instance\" : \"instance\", \"severityText\" : \"severityText\", \"application\" : { \"displayName\" : \"displayName\", \"name\" : \"name\" }, \"service\" : \"service\", \"scopeName\" : \"scopeName\", \"severityNumber\" : 7, \"body\" : \"body\", \"platform\" : { \"displayName\" : \"displayName\", \"name\" : \"name\" }, \"timestamp\" : 2 }, { \"traceId\" : \"traceId\", \"spanId\" : \"spanId\", \"cluster\" : \"cluster\", \"instance\" : \"instance\", \"severityText\" : \"severityText\", \"application\" : { \"displayName\" : \"displayName\", \"name\" : \"name\" }, \"service\" : \"service\", \"scopeName\" : \"scopeName\", \"severityNumber\" : 7, \"body\" : \"body\", \"platform\" : { \"displayName\" : \"displayName\", \"name\" : \"name\" }, \"timestamp\" : 2 } ] }, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
