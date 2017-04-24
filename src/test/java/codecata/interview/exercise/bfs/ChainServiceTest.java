package codecata.interview.exercise.bfs;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ChainServiceTest {
    private ChainServiceBFS chainServiceBFS = new ChainServiceBFS();

    private String FILE_PATH = "src/main/resources/wordlist.txt";

    @Test
    public void shouldReturnCorrectChainLeadGold() {
        try {
            String wordOne = "lead";
            String wordTwo = "gold";
            ChainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
            LinkedList<Node> resultList = (LinkedList<Node>) chainServiceBFS.solve(FILE_PATH, wordOne, wordTwo);
            assertTrue(resultList.toString().equals("[lead, load, goad, gold]"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnCorrectChainGoldLead() {
        try {
            String wordOne = "gold";
            String wordTwo = "lead";
            ChainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
            LinkedList<Node> resultList = (LinkedList<Node>) chainServiceBFS.solve(FILE_PATH, wordOne, wordTwo);
            assertTrue(resultList.toString().equals("[gold, goad, load, lead]"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnValidPathBFS() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");
        Node z = new Node("z");

        prepareGraph(a, b, c, d, e, f, g, h);

        LinkedList<Node> resultList = (LinkedList<Node>) chainServiceBFS.getPathBFS(e, a);

        assertTrue(resultList.toString().equals("[e, d, c, a]"));
    }

    @Test(expected=RuntimeException.class)
    public void shouldReturnNoPath() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");
        Node z = new Node("z");

        prepareGraph(a, b, c, d, e, f, g, h);

        chainServiceBFS.getPathBFS(e, z);
    }

    private void prepareGraph(Node a, Node b, Node c, Node d, Node e, Node f, Node g, Node h) {
        a.addNeighbour(b);
        a.addNeighbour(c);
        b.addNeighbour(c);
        f.addNeighbour(b);
        f.addNeighbour(g);
        g.addNeighbour(h);
        h.addNeighbour(d);
        c.addNeighbour(d);
        d.addNeighbour(e);
    }
}
