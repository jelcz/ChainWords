package codecata.interview.exercise.bfs;

import codecata.interview.exercise.bfs.NeighboursService;
import codecata.interview.exercise.bfs.Node;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NeighboursServiceTest {
    NeighboursService neighboursService = new NeighboursService();

    @Test
    public void shouldAddNeighbours() {
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
        assertThat(aaa.getNeighbourhood().contains(aab));
        assertThat(aab.getNeighbourhood().contains(aaa));
        assertThat(adb.getNeighbourhood().contains(adf));
        assertThat(bec.getNeighbourhood().contains(aec));
        assertThat(aac.getNeighbourhood().contains(aec));
        assertThat(aec.getNeighbourhood().contains(aac));
        assertThat(!aaa.getNeighbourhood().contains(bec));
        assertThat(!aaa.getNeighbourhood().contains(aef));
        assertThat(!aaa.getNeighbourhood().contains(adb));
    }
}

