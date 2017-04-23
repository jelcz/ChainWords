package org.example.bfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NeighboursServiceTest {
    NeighboursService neighboursService = new NeighboursService();

    @Test
    public void shouldAddOutNodes() {
        List<Node> listNodes = new LinkedList<>();
        Node aaa = new Node("aaa");
        Node aab = new Node("aab");
        Node aac = new Node("aac");
        Node adb = new Node("adb");
        Node aec = new Node("aec");
        Node adf = new Node("adf");
        Node aef = new Node("aef");
        Node bec = new Node("bec");

        listNodes.add(aaa);
        listNodes.add(aab);
        listNodes.add(aac);
        listNodes.add(adb);
        listNodes.add(aec);
        listNodes.add(adf);
        listNodes.add(aef);
        listNodes.add(bec);

        neighboursService.fillNeighbourList(listNodes);
        assertThat(aaa.getOutNodes().contains(aab));
        assertThat(aab.getOutNodes().contains(aaa));
        assertThat(adb.getOutNodes().contains(adf));
        assertThat(bec.getOutNodes().contains(aec));
        assertThat(aac.getOutNodes().contains(aec));
        assertThat(aec.getOutNodes().contains(aac));
        assertThat(!aaa.getOutNodes().contains(bec));
        assertThat(!aaa.getOutNodes().contains(aef));
        assertThat(!aaa.getOutNodes().contains(adb));
    }
}

