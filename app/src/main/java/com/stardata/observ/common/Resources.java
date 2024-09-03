package com.stardata.observ.common;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import com.stardata.observ.vo.CommonResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class Resources {
    private static final Logger logger = LoggerFactory.getLogger(Resources.class.getName());
    private final String requestType;
    private HttpStatus successfulStatus;
    private HttpStatus errorStatus;
    private HttpStatus failedStatus;

    private Resources(String requestType) {
        this.requestType = requestType;
    }

    public static Resources with(String requestType) {
        return new Resources(requestType);
    }

    public Resources onSuccess(HttpStatus status) {
        this.successfulStatus = status;
        return this;
    }

    public Resources onError(HttpStatus status) {
        this.errorStatus = status;
        return this;
    }

    public Resources onFailed(HttpStatus status) {
        this.failedStatus = status;
        return this;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> ResponseEntity<T> execute(Supplier<T> supplier) {
        try {
            T entity = supplier.get();
            setSucceed(entity);
            return new ResponseEntity<>(entity, successfulStatus);
        } catch (ApplicationValidationException ex) {
            String msg = String.format("The request of %s is invalid. %s", requestType, ex.getErrMsg());
            logger.warn(msg);
            return new ResponseEntity(new CommonResponse(-1, msg), errorStatus);
        } catch (ApplicationDomainException ex) {
            String msg = String.format("Domain exception raised %s REST Call. %s", requestType, ex.getErrMsg());
            logger.warn(msg);
            return new ResponseEntity(new CommonResponse(-2, msg), failedStatus);
        } catch (ApplicationInfrastructureException ex) {
            String msg = String.format("Infrastructure exception raised %s REST Call. %s", requestType, ex.getErrMsg());
            return new ResponseEntity(new CommonResponse(-3, msg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CommonResponse> execute(Runnable runnable) {
        try {
            runnable.run();
            return new ResponseEntity<>(new CommonResponse(0, "OK"), successfulStatus);
        } catch (ApplicationValidationException ex) {
            String msg = String.format("The request of %s is invalid. %s", requestType, ex.getErrMsg());
            logger.warn(msg);
            return new ResponseEntity<>(new CommonResponse(-1, msg), errorStatus);
        } catch (ApplicationDomainException ex) {
            String msg = String.format("Domain exception raised %s REST Call. %s", requestType, ex.getErrMsg());
            logger.warn(msg);
            return new ResponseEntity<>(new CommonResponse(-2, msg), failedStatus);
        } catch (ApplicationInfrastructureException ex) {
            String msg = String.format("Infrastructure exception raised %s REST Call. %s", requestType, ex.getErrMsg());
            return new ResponseEntity<>(new CommonResponse(-3, msg), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private <T> void setSucceed(T entity) {
        try {
            Method setCodeMethod = entity.getClass().getMethod("setCode", Integer.class);
            setCodeMethod.invoke(entity, 0);
            Method setMessageMethod = entity.getClass().getMethod("setMessage", String.class);
            setMessageMethod.invoke(entity, "OK");
        } catch (Exception ignore) {
        }
    }

}
