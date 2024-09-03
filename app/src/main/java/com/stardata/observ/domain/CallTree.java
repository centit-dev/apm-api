package com.stardata.observ.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.stardata.observ.model.ch.Span;
import com.stardata.observ.utils.JSONUtil;
import com.stardata.observ.common.ApplicationDomainException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Samson Shu
 * @version 1.0
 * @email shush@stardata.top
 * @date 2024/1/12 21:11
 */
@NoArgsConstructor
public class CallTree {
    private final Map<String, CallNode> innerNodeMap = new HashMap<>();
    private final List<Edge> edges = new ArrayList<>();
    private boolean treeConstructed = false;
    private CallNode root = null;
    @Getter
    private Set<CallNode> causingNodes = null;

    /**
     * 将span作为节点加入调用树中
     *
     * @param span 指定的span记录
     */
    public void addSpanAsNode(@Nonnull Span span) {
        CallNode callNode = new CallNode(span);
        innerNodeMap.put(span.getSpanId(), callNode);
        edges.add(new Edge(span.getSpanId(), span.getParentSpanId()));
        treeConstructed = false;
    }

    /**
     * 获得调用树的根节点
     *
     * @return 根节点
     */
    public CallNode getRoot() {
        if (!treeConstructed) {
            constructCallTree();
        }
        return root;
    }

    /**
     * 构造调用树数据结构
     */
    private void constructCallTree() {
        this.root = null;
        if (innerNodeMap.isEmpty()) return;
        for (Edge edge : edges) {
            if (StringUtils.isNotEmpty(edge.getParentSpanId())) {
                CallNode node = innerNodeMap.get(edge.getSpanId());
                if (node == null) {
                    continue;
                }
                CallNode parentNode = innerNodeMap.get(edge.getParentSpanId());
                parentNode.addChild(node);
            }
        }
        for (CallNode node : innerNodeMap.values()) {
            if (node.getParent() == null) {
                if (root != null) {
                    throw new ApplicationDomainException("原始trace信息解析后发现多个调用树根节点：\n" +
                            JSONUtil.toJSONString(innerNodeMap.values()));
                }
                this.root = node;
            }
        }
        if (this.root == null)
            throw new ApplicationDomainException("原始trace信息解析后找不到调用树根节点：\n" +
                    JSONUtil.toJSONString(innerNodeMap.values()));


        treeConstructed = true;
    }

    /**
     * 识别服务调用节点、以及下级子节点的自身处理时延、对应的故障类型
     */
    public void identifyCausingNodes() {
        if (!treeConstructed) {
            constructCallTree();
        }
        if (root == null) return;
        this.causingNodes = root.findCausingNodes();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Edge {
        String spanId;
        String parentSpanId;
    }

}
