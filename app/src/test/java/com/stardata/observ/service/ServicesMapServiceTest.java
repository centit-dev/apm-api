package com.stardata.observ.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.stardata.observ.domain.ServiceMapNodeDto;
import com.stardata.observ.model.ch.ServiceMapEdgeResult;
import com.stardata.observ.vo.NameDisplayPair;
import com.stardata.observ.vo.ServiceMapEdge;
import com.stardata.observ.vo.ServiceMapNode;
import com.stardata.observ.vo.ServiceMapRequest;
import com.stardata.observ.vo.ServiceMapResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicesMapServiceTest {
    @Mock
    private QueryDictionaryService dictionaryService;

    private ServicesMapService service;

    @BeforeEach
    void setUp() {
        service = new ServicesMapService(null, null, dictionaryService);
    }

    @Test
    void testCreateServiceMap() {
        when(dictionaryService.getDisplayPair(anyString())).thenAnswer(invocation -> {
            String name = invocation.getArgument(0, String.class);
            return new NameDisplayPair().name(name).displayName(name);
        });

        List<ServiceMapEdgeResult> edges = createEdges(
            "node+5", "node+4",
            "node+4", "node+3",
            "node+3", "node+2",
            "node+2", "node+1",
            "node+1", "node-0",
            "node-0", "node-1",
            "node-1", "node-2",
            "node-2", "node-3",
            "node-3", "node-4",
            "node-4", "node-5");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        ServiceMapResponse response = service.createServiceMap(createRequest(3), map);
        List<ServiceMapNode> nodes = response.getData().getNodes();
        assertEquals(7, nodes.size());
        assertThat(nodes, hasItems(
            hasProperty("cluster", equalTo("node+3")),
            hasProperty("cluster", equalTo("node-3"))));
        assertThat(nodes, not(hasItems(
            hasProperty("cluster", equalTo("node+4")),
            hasProperty("cluster", equalTo("node-4")))));

        List<ServiceMapEdge> edgeData = response.getData().getEdges();
        assertEquals(6, edgeData.size());
        assertThat(edgeData, not(hasItems(
            hasProperty("fromCluster", equalTo("node+4")),
            hasProperty("toCluster", equalTo("node-4"))
        )));
    }

    @Test
    void testBuildServiceTreeWhenTheFirstEdgeComesIn() {
        List<ServiceMapEdgeResult> edges = createEdges("parent", "child");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        assertEquals(2, map.size());

        assertTrue(map.containsKey("parent"));
        ServiceMapNodeDto parent = map.get("parent");
        assertEquals("parent", parent.getAppCluster());
        assertEquals(1, parent.getServers().size());

        assertTrue(map.containsKey("child"));
        ServiceMapNodeDto child = map.get("child");
        assertEquals("child", child.getAppCluster());
        assertSame(parent, child.getClients().get("parent"));
    }

    @Test
    void testBuildServiceTreeWhenUnrelatedEdgeComesIn() {
        List<ServiceMapEdgeResult> edges = createEdges(
            "parent1", "child1",
            "parent2", "child2");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        assertEquals(4, map.size());

        assertTrue(map.containsKey("parent1"));
        ServiceMapNodeDto parent1 = map.get("parent1");
        assertEquals("parent1", parent1.getAppCluster());
        assertEquals(1, parent1.getServers().size());

        assertTrue(map.containsKey("child1"));
        ServiceMapNodeDto child1 = map.get("child1");
        assertEquals("child1", child1.getAppCluster());
        assertSame(parent1, child1.getClients().get("parent1"));
    }

    @Test
    void testBuildServiceTreeWhenMultipleChildEdgesComeIn() {
        List<ServiceMapEdgeResult> edges = createEdges(
            "parent", "child1",
            "parent", "child2");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        assertEquals(3, map.size());

        assertTrue(map.containsKey("parent"));
        ServiceMapNodeDto parent = map.get("parent");
        assertEquals("parent", parent.getAppCluster());
        assertEquals(2, parent.getServers().size());

        assertTrue(map.containsKey("child1"));
        ServiceMapNodeDto child1 = map.get("child1");
        assertEquals("child1", child1.getAppCluster());
        assertSame(parent, child1.getClients().get("parent"));

        assertTrue(map.containsKey("child2"));
        ServiceMapNodeDto child2 = map.get("child2");
        assertEquals("child2", child2.getAppCluster());
        assertSame(parent, child2.getClients().get("parent"));
    }

    @Test
    void testBuildServiceTreeWhenTheChildEdgeComesInAfterTheParent() {
        List<ServiceMapEdgeResult> edges = createEdges(
            "parent", "child",
            "child", "grandchild");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        assertEquals(3, map.size());

        assertTrue(map.containsKey("parent"));
        ServiceMapNodeDto parent = map.get("parent");
        assertEquals("parent", parent.getAppCluster());
        assertEquals(1, parent.getServers().size());

        assertTrue(map.containsKey("child"));
        ServiceMapNodeDto child = map.get("child");
        assertEquals("child", child.getAppCluster());
        assertSame(parent, child.getClients().get("parent"));
        assertEquals(1, child.getServers().size());

        assertTrue(map.containsKey("grandchild"));
        ServiceMapNodeDto grandchild = map.get("grandchild");
        assertEquals("grandchild", grandchild.getAppCluster());
        assertSame(child, grandchild.getClients().get("child"));
    }

    @Test
    void testBuildServiceTreeWhenTheParentEdgeComesInAfterTheChild() {
        List<ServiceMapEdgeResult> edges = createEdges(
            "child", "grandchild",
            "parent", "child");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        assertEquals(3, map.size());

        assertTrue(map.containsKey("parent"));
        ServiceMapNodeDto parent = map.get("parent");
        assertEquals("parent", parent.getAppCluster());
        assertEquals(1, parent.getServers().size());

        assertTrue(map.containsKey("child"));
        ServiceMapNodeDto child = map.get("child");
        assertEquals("child", child.getAppCluster());
        assertSame(parent, child.getClients().get("parent"));
        assertEquals(1, child.getServers().size());

        assertTrue(map.containsKey("grandchild"));
        ServiceMapNodeDto grandchild = map.get("grandchild");
        assertEquals("grandchild", grandchild.getAppCluster());
        assertSame(child, grandchild.getClients().get("child"));
    }

    @Test
    void testBuildServiceTreeWhenTheGrandchildEdgeComesInBeforeTheParent() {
        List<ServiceMapEdgeResult> edges = createEdges(
            "grandchild", "grand-grandchild",
            "parent", "child",
            "child", "grandchild");
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        assertEquals(4, map.size());

        assertTrue(map.containsKey("parent"));
        ServiceMapNodeDto parent = map.get("parent");
        assertEquals("parent", parent.getAppCluster());
        assertEquals(1, parent.getServers().size());

        assertTrue(map.containsKey("child"));
        ServiceMapNodeDto child = map.get("child");
        assertEquals("child", child.getAppCluster());
        assertSame(parent, child.getClients().get("parent"));

        assertTrue(map.containsKey("grandchild"));
        ServiceMapNodeDto grandchild = map.get("grandchild");
        assertEquals("grandchild", grandchild.getAppCluster());
        assertSame(child, grandchild.getClients().get("child"));

        assertTrue(map.containsKey("grand-grandchild"));
        ServiceMapNodeDto grandGrandchild = map.get("grand-grandchild");
        assertEquals("grand-grandchild", grandGrandchild.getAppCluster());
        assertSame(grandchild, grandGrandchild.getClients().get("grandchild"));
    }

    @Test
    void testBuildServiceTreeBenchmark() {
        String[] nodes = IntStream.range(0, 10_000).boxed()
                .flatMap(index -> Stream.of(String.format("node-%d", index), String.format("node-%d", index + 1)))
                .toArray(String[]::new);
        StopWatch watch = new StopWatch();
        List<ServiceMapEdgeResult> edges = createEdges(nodes);
        watch.start();
        Map<String, ServiceMapNodeDto> map = service.buildServiceTree(edges, Collections.emptyMap());
        watch.stop();
        assertEquals(10_001, map.size());
        System.out.println(watch.getTime());
    }

    private ServiceMapRequest createRequest(int depth) {
        return new ServiceMapRequest()
                .cluster("node-0")
                .entranceDepth(depth)
                .exitDepth(depth);
    }

    private List<ServiceMapEdgeResult> createEdges(String... nodes) {
        return IntStream.range(0, nodes.length / 2)
                .boxed()
                .map(index -> {
                    ServiceMapEdgeResult edge = new ServiceMapEdgeResult();
                    edge.setFromService(nodes[index * 2]);
                    edge.setToService(nodes[index * 2 + 1]);
                    edge.setClientApplicationCluster(nodes[index * 2]);
                    edge.setServerApplicationCluster(nodes[index * 2 + 1]);
                    edge.setPlatformName("smart");
                    edge.setCallCount(1000);
                    edge.setBusinessFaultCount(100);
                    edge.setSystemFaultCount(100);
                    edge.setDuration(10_000L);
                    edge.setClientApplicationType("java");
                    edge.setServerSoftware("mysql");
                    return edge;
                })
                .collect(Collectors.toList());
    }
}
