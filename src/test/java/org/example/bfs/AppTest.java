package org.example.bfs;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class AppTest {
    ChainServiceBFS chainServiceBFS = new ChainServiceBFS();

    @Test
    public void shouldReturnCorrectChainLeadGold() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        try {
            String wordOne = "lead";
            String wordTwo = "gold";
            chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
            LinkedList<Node> resultList = (LinkedList<Node>) chainServiceBFS.solve(FILE_PATH, wordOne, wordTwo);
            assertTrue(resultList.toString().equals("[lead, load, goad, gold]"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnCorrectChainGoldLead() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        try {
            String wordOne = "gold";
            String wordTwo = "lead";
            chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
            LinkedList<Node> resultList = (LinkedList<Node>) chainServiceBFS.solve(FILE_PATH, wordOne, wordTwo);
            assertTrue(resultList.toString().equals("[gold, goad, load, lead]"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void approachBFSMapPerformance() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        long sum = 0;
        int counter = 5;
        for (int i = 0; i < counter; i++) {

            long start = System.currentTimeMillis();

            try {
                String wordOne = "lead";
                String wordTwo = "gold";
                chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
                chainServiceBFS.solve(FILE_PATH, wordOne, wordTwo);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            long finish = System.currentTimeMillis();
            System.out.println(("Time single: " + (finish - start)));
            sum += (finish - start);
        }
        System.out.println("Time average: " + (sum / counter));
    }

    @Test(expected=RuntimeException.class)
    public void shouldReturnNullFileNotFound() {
        String FILE_PATH = "src/main/resources/wordlisst.txt";
        String wordOne = "lead";
        String wordTwo = "gold";
        chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionDifferentLength() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        String wordOne = "leads";
        String wordTwo = "gold";
        chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionNullWord() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        String wordOne = null;
        String wordTwo = null;
        chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionWrongWord() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        String wordOne = "";
        String wordTwo = "";
        chainServiceBFS.checkValid(FILE_PATH, wordOne, wordTwo);
    }
    @Test
    public void shouldAddOutNodes() {
        Node a = new Node("a");
        Node b = new Node("b");
        a.addOutNode(b);
        assertThat(a.getOutNodes().contains(b) && b.getOutNodes().contains(a));
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
        a.addOutNode(b);
        a.addOutNode(c);
        b.addOutNode(c);
        f.addOutNode(b);
        f.addOutNode(g);
        g.addOutNode(h);
        h.addOutNode(d);
        c.addOutNode(d);
        d.addOutNode(e);
    }

}

