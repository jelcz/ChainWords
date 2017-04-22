package org.example.bfs;

import org.junit.Test;
import org.example.reccurent.ChainServiceRecurrent;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class AppTest {
    ChainServiceBFS chainServiceBFS = new ChainServiceBFS();
    ChainServiceRecurrent chainServiceRecurrent = new ChainServiceRecurrent();


    @Test
    public void shouldReturnValidRegex() {
        String str = "code";
        String expected = "[a-z]ode|c[a-z]de|co[a-z]e|cod[a-z]|";
        String result = chainServiceRecurrent.prepareRegexp(str);
        assertThat(expected.equals(result));
    }

    @Test
    public void approachBFSMap() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        long sum = 0;
        int ile = 1;
        for (int i = 0; i < ile; i++) {

            long start = System.currentTimeMillis();

            try {
                String wordOne = "lead";
                String wordTwo = "gold";
                chainServiceBFS.checkValidSolve(FILE_PATH, wordOne, wordTwo);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            long finish = System.currentTimeMillis();
            System.out.println((finish - start));
            sum += (finish - start);
        }
        System.out.println(sum / ile);
    }

    @Test
    public void approachRecurrent() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        long sum = 0;
        int ile = 1;
        for (int i = 0; i < ile; i++) {

            long start = System.currentTimeMillis();

            try {
                String wordOne = "lead";
                String wordTwo = "gold";
                chainServiceRecurrent.checkValidSolve(FILE_PATH, wordOne, wordTwo);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            long finish = System.currentTimeMillis();
            System.out.println((finish - start));
            sum += (finish - start);
        }
        System.out.println(sum / ile);
    }

    @Test
    public void approachReadFileSpeedTest() {
        String FILE_PATH = "src/main/resources/wordlist.txt";
        long sum = 0;
        int ile = 1;
        for (int i = 0; i < ile; i++) {
            long start = System.currentTimeMillis();
            try {
                chainServiceRecurrent.readFile(FILE_PATH, 4);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            long finish = System.currentTimeMillis();
            System.out.println((finish - start));
            sum += (finish - start);
        }
        System.out.println("readFile: " + sum / ile);
    }

    @Test
    public void shouldAddOutNodes() {
        Node a = new Node("a");
        Node b = new Node("b");
        a.addOutNode(b);
        assertThat(a.getOutNodes().contains(b) && b.getOutNodes().contains(a));
    }

    @Test
    public void shouldSolve() {
        List<String> entryList = new LinkedList<>();
        entryList.add("aaa");
        entryList.add("aab");
        entryList.add("aac");
        entryList.add("adb");
        entryList.add("aec");
        entryList.add("adf");
        entryList.add("aef");
        entryList.add("bec");
        List<Node> resultList = chainServiceBFS.solve(entryList, "aaa", "bec");

        assertTrue(resultList.toString().equals("[aaa, aac, aec, bec]"));
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

        a.addOutNode(b);
        a.addOutNode(c);
        b.addOutNode(c);
        f.addOutNode(b);
        f.addOutNode(g);
        g.addOutNode(h);
        h.addOutNode(d);
        c.addOutNode(d);
        d.addOutNode(e);

        LinkedList<Node> resultList = (LinkedList<Node>) chainServiceBFS.getPathBFS(e, a);

        assertTrue(resultList.toString().equals("[e, d, c, a]"));
    }
}

