package codecata.interview.exercise.bfs;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class NodeTest {

    @Test
    public void shouldAddNeighbour() {
        Node a = new Node("a");
        Node b = new Node("b");
        a.addNeighbour(b);
        assertThat(a.getNeighbourhood().contains(b) && b.getNeighbourhood().contains(a));
    }
}
