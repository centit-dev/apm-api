package com.stardata.observ.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stardata.observ.model.ch.Span;
import com.stardata.observ.utils.JSONUtil;
import com.stardata.observ.common.FaultKindEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2024/1/12 21:25
 */
@Data
public class CallNode implements Serializable {
    public static final long serialVersionUId = 1L;

    private final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private Span span;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private CallNode parent;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Map<String, CallNode> children = new HashMap<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private FaultKindEnum faultKind = FaultKindEnum.UNKNOWN;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private boolean faultHandled = false;

    public CallNode(Span span) {
        this.span = span;
    }

    @Override
    public String toString() {
        return JSONUtil.toJSONString(span);
    }

    /**
     * 判断节点是否调用报错
     *
     * @return 报错与否
     */
    public boolean failed() {
        if (this.span != null) {
            return this.getFaultKind() != FaultKindEnum.SUCCEED;
        }
        return true;
    }

    /**
     * 为节点增加子节点
     *
     * @param childNode 被插入的子节点
     */
    public void addChild(@Nonnull CallNode childNode) {
        children.put(childNode.getSpan().getSpanId(), childNode);
        childNode.setParent(this);
    }

    /**
     * 找到所有报错根因节点
     *
     * @return 报错根因节点集合
     */
    public Set<CallNode> findCausingNodes() {
        Set<CallNode> result = new HashSet<>();
        if (isCausingNode())
            result.add(this);
        if (children != null) {
            for (CallNode child : children.values()) {
                result.addAll(child.findCausingNodes());
            }
        }
        return result;
    }

    /**
     * 判断本节点是否报错根因节点
     *
     * @return 是否根因节点
     */
    private boolean isCausingNode() {
        if (this.failed()) {
            if (this.children == null || this.children.isEmpty()) return true;

            boolean hasChildrenFault = false;
            for (CallNode child : this.children.values()) {
                if (child.failed()) {
                    hasChildrenFault = true;
                    break;
                }
            }
            return !hasChildrenFault;
        }
        return false;
    }
}
